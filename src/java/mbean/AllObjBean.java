/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.*;
import entity.*;
import entity.Employee;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
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
    private List<SelectItem> rootAndBranchDepartList, rootDepartNameList, employeeNameList, jobTitleList, departNameList, personalIDList, leaveTypeList;
    private List<EmployeeModel> employeeList, filteredEmployeeList;
    private TabView tabView;
    
    private int empId;

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
    

    @PostConstruct
    private void populateObjectLists() {

        jobsHTable = jobEjb.getJobTitleIdHTable();
        jobTitleList = new ArrayList(jobsHTable.size());

        departmentsHTable = depEjb.getDepartmentNameIdHTable();
        departNameList = new ArrayList(departmentsHTable.size());
        
        rootDepartHTable = depEjb.getRootDepartments();
        rootAndBranchDepartList = new ArrayList(rootDepartHTable.size());
        rootDepartNameList = new ArrayList(rootDepartHTable.size());

        employeesHTable = empEjb.getEmployeeNamesId();
        employeeNameList = new ArrayList(employeesHTable.size());

        personalIdsHTable = personIdEjb.getPersonalIdHTable();
        personalIDList = new ArrayList(personalIdsHTable.size());
        
        leaveTypesHTable = leaveTypeEjb.getLeaveTypeHTable();
        leaveTypeList = new ArrayList(leaveTypesHTable.size());
        
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
        
        List<Employee> empList = empEjb.findAll();
        employeeList = new ArrayList(empList.size());
        for (Employee emp: empList) {
            PersonalID perId = emp.getPersonalIDType();
            employeeList.add(new EmployeeModel(
                    emp.getEmpID(), emp.getFirstName()+" "+emp.getLastName(),
                    emp.getJobID().getJobTitle(), emp.getDepID().getDepartmentName(),
                    perId == null?null:perId.getPersonalIDDescription(),
                    emp.getPersonalIDNum(),
                    emp.getMobile1(),
                    emp.getMobile2(),
                    emp.getLandline(),
                    emp.getEmail()
                )
            );
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
            rootDepartNameList.add(new SelectItem(departId,n));
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
                rootAndBranchDepartList.add(depItemGroup);
                tabView.getChildren().add(rootTab);
            }
        }
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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

    public List<SelectItem> getRootAndBranchDepartList() {
        return rootAndBranchDepartList;
    }

    public List<SelectItem> getRootDepartNameList() {
        return rootDepartNameList;
    }

    public void setRootDepartNameList(List<SelectItem> rootDepartNameList) {
        this.rootDepartNameList = rootDepartNameList;
    }
    
    public List<EmployeeModel> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeModel> employeeList) {
        this.employeeList = employeeList;
    }

    public List<EmployeeModel> getFilteredEmployeeList() {
        return filteredEmployeeList;
    }

    public void setFilteredEmployeeList(List<EmployeeModel> filteredEmployeeList) {
        this.filteredEmployeeList = filteredEmployeeList;
    }

    public TabView getTabView() {
        return tabView;
    }

    public void setTabView(TabView tabView) {
        this.tabView = tabView;
    }
    
    public void selectedEmployeeChanged(ValueChangeEvent vce) {
        System.out.println("Employee Selected: "+vce.getNewValue().toString());
                //.getNewValue().toString());
    }
    
    public class EmployeeModel {
     
        private Integer Id;
        private String employeeName, jobTitle, department, personalID, personIDNum,
                mobile1, mobile2, landline, email;
        
        public EmployeeModel() {            
        }

        public EmployeeModel(Integer Id, String employeeName, String jobTitle, String department, String personalID, String personIDNum, String mobile1, String mobile2, String landline, String email) {
            this.Id = Id;
            this.employeeName = employeeName;
            this.jobTitle = (jobTitle ==  null?"":jobTitle);
            this.department = (department == null?"":department);
            this.personalID = (personalID == null?"":personalID);
            this.personIDNum = (personIDNum == null?"":personIDNum);
            this.mobile1 = (mobile1 == null?"":mobile1);
            this.mobile2 = (mobile2 == null?"":mobile2);
            this.landline = (landline == null?"":landline);
            this.email = (email == null?"":email);
        }

        public Integer getId() {
            return Id;
        }

        public void setId(Integer Id) {
            this.Id = Id;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getPersonalID() {
            return personalID;
        }

        public void setPersonalID(String personalID) {
            this.personalID = personalID;
        }

        public String getPersonIDNum() {
            return personIDNum;
        }

        public void setPersonIDNum(String personIDNum) {
            this.personIDNum = personIDNum;
        }

        public String getMobile1() {
            return mobile1;
        }

        public void setMobile1(String mobile1) {
            this.mobile1 = mobile1;
        }

        public String getMobile2() {
            return mobile2;
        }

        public void setMobile2(String mobile2) {
            this.mobile2 = mobile2;
        }

        public String getLandline() {
            return landline;
        }

        public void setLandline(String landline) {
            this.landline = landline;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }        
    }
}
