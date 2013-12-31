/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.*;
import entity.Employee;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.component.tabview.TabView;
import org.primefaces.component.tabview.Tab;

/**
 *
 * @author Riad
 */
@ManagedBean
@ViewScoped
public class AllObjBean implements java.io.Serializable {

    private Hashtable<String, Integer> employeesHTable;
    private Hashtable<String, Short> jobsHTable, departmentsHTable, personalIdsHTable, leaveTypesHTable, rootDepartHTable;
    private List<SelectItem> departmentAndRootsList, rootDepartmentList, employeeNameList, jobTitleList, departNameList, personalIDList, leaveTypeList;
    private List<Employee> employeeList, filteredEmployeeList;
    private TabView tabView;

    @EJB
    private EmployeeFacade empEjb;
    @EJB
    private JobFacade jobEjb;
    @EJB
    private DepartmentFacade depEjb;
    @EJB
    private PersonalIDFacade personIdEjb;
    @EJB
    private LeaveTypeFacade leaveTypeEjb;

    /**
     * Creates a new instance of AllObjBean
     */
    public AllObjBean() {
    }
    

    @PostActivate
    @PostConstruct
    private void populateObjectLists() {

        jobsHTable = jobEjb.getJobTitleIdHTable();
        jobTitleList = new ArrayList(jobsHTable.size());

        departmentsHTable = depEjb.getDepartmentNameIdHTable();
        departNameList = new ArrayList(departmentsHTable.size());
        
        rootDepartHTable = depEjb.getRootDepartments();
        departmentAndRootsList = new ArrayList(rootDepartHTable.size());
        rootDepartmentList = new ArrayList(rootDepartHTable.size());

        employeesHTable = empEjb.getEmployeeNamesId();
        employeeNameList = new ArrayList(employeesHTable.size());

        personalIdsHTable = personIdEjb.getPersonalIdHTable();
        personalIDList = new ArrayList(personalIdsHTable.size());
        
        leaveTypesHTable = leaveTypeEjb.getLeaveTypeHTable();
        leaveTypeList = new ArrayList(leaveTypesHTable.size());
        
        this.setEmployeeList(empEjb.findAll());
        
        tabView = new TabView();
        
        Iterator<String> hTableIter = jobsHTable.keySet().iterator();
        String n;
        while (hTableIter.hasNext()) {
            n = hTableIter.next();
            jobTitleList.add(new SelectItem(jobsHTable.get(n),n));
        }

        hTableIter = departmentsHTable.keySet().iterator();
        while (hTableIter.hasNext()) {
            n = hTableIter.next();
            departNameList.add(new SelectItem(departmentsHTable.get(n),n));
        }

        hTableIter = employeesHTable.keySet().iterator();
        while (hTableIter.hasNext()) {
            n = hTableIter.next();
            employeeNameList.add(new SelectItem(employeesHTable.get(n),n));
        }

        hTableIter = personalIdsHTable.keySet().iterator();
        while (hTableIter.hasNext()) {
            n = hTableIter.next();
            personalIDList.add(new SelectItem(personalIdsHTable.get(n),n));
        }
        
        hTableIter = leaveTypesHTable.keySet().iterator();
        while (hTableIter.hasNext()) {
            n = hTableIter.next();
            leaveTypeList.add(new SelectItem(leaveTypesHTable.get(n),n));
        }

        hTableIter = rootDepartHTable.keySet().iterator();
        SelectItemGroup depItemGroup;
        SelectItem[] childItems;
        Short departId;
        Tab rootTab;
        while (hTableIter.hasNext()) {
            n = hTableIter.next();
            departId = rootDepartHTable.get(n);
            depItemGroup = new SelectItemGroup(n);
            rootDepartmentList.add(new SelectItem(departId,n));
            rootTab = new Tab();
            rootTab.setTitle(n);
            
            Hashtable<String, Short> childDepTable = depEjb.getChildDepartments(departId);
            
            if (childDepTable.size() > 0) {
                //System.out.println(n+" has children");
                Set<String> departNameSet = childDepTable.keySet();
                int i = 0;
                childItems = new SelectItem[departNameSet.size()];
                Iterator<String> childDepIter = departNameSet.iterator();
                while(childDepIter.hasNext()) {
                    n = childDepIter.next();
                    //System.out.println("\t"+childDepTable.get(n)+" - "+n);
                    childItems[i++] = new SelectItem(childDepTable.get(n),n);
                }
                depItemGroup.setSelectItems(childItems);
                departmentAndRootsList.add(depItemGroup);
                tabView.getChildren().add(rootTab);
            }
        }
    }

    public List<SelectItem> getEmployeeNameList() {
        return employeeNameList;
    }

    public void setEmployeeNameList(List<SelectItem> employeeNameList) {
        this.employeeNameList = employeeNameList;
    }

    public List<SelectItem> getJobTitleList() {
        return jobTitleList;
    }

    public void setJobTitleList(List<SelectItem> jobTitleList) {
        this.jobTitleList = jobTitleList;
    }

    public List<SelectItem> getDepartNameList() {
        return departNameList;
    }

    public void setDepartNameList(List<SelectItem> departNameList) {
        this.departNameList = departNameList;
    }

    public List<SelectItem> getPersonalIDList() {
        return personalIDList;
    }

    public void setPersonalIDList(List<SelectItem> personalIDList) {
        this.personalIDList = personalIDList;
    }

    public List<SelectItem> getLeaveTypeList() {
        return leaveTypeList;
    }

    public void setLeaveTypeList(List<SelectItem> leaveTypeList) {
        this.leaveTypeList = leaveTypeList;
    }

    public List<SelectItem> getDepartmentAndRootsList() {
        return departmentAndRootsList;
    }

    public List<SelectItem> getRootDepartmentList() {
        return rootDepartmentList;
    }

    public void setRootDepartmentList(List<SelectItem> rootDepartmentList) {
        this.rootDepartmentList = rootDepartmentList;
    }
    
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Employee> getFilteredEmployeeList() {
        return filteredEmployeeList;
    }

    public void setFilteredEmployeeList(List<Employee> filteredEmployeeList) {
        this.filteredEmployeeList = filteredEmployeeList;
    }

    public TabView getTabView() {
        return tabView;
    }

    public void setTabView(TabView tabView) {
        this.tabView = tabView;
    }

}
