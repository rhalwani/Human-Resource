/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Department;
import entity.Employee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import javax.persistence.TypedQuery;
import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Riad
 */
@Stateless
public class DepartmentFacade extends AbstractFacade<Department> {

    @PersistenceContext(unitName = "HRPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartmentFacade() {
        super(Department.class);
    }
    
    public void addNewDepartment(String departmentName, Short rootDepId, Short parentDepID, Integer mgrID) throws EntityExistsException, ConstraintViolationException {
        
        Department dep = new Department();
        dep.setDepartmentName(departmentName);
        dep.setRootDepID(rootDepId == Short.valueOf((short)0) ? null:this.find(rootDepId));
        dep.setParentDepID(parentDepID == Short.valueOf((short)0) ? null:this.find(parentDepID));
        dep.setMgrID(mgrID == Integer.valueOf(0) ? null:em.find(Employee.class, mgrID));

        em.persist(dep);
    }

    public LinkedHashMap<String, Short> getDepartmentNameIdHTable() {

        List<Department> depList = this.findAll();
        LinkedHashMap<String, Short> departNameIdHTable = new LinkedHashMap(depList.size());
        for (Department dep : depList) {
            departNameIdHTable.put(dep.getDepartmentName(), dep.getDepID());
        }
        return departNameIdHTable;
    }

    public LinkedHashMap<String, Short> getRootDepartments() {

        TypedQuery<Department> query = em.createNamedQuery("Department.findRootDeps", Department.class);
        List<Department> rootDepList = query.getResultList();
        LinkedHashMap rootDepartNameIdHTable = new LinkedHashMap();
        for (Department dep : rootDepList) {
            rootDepartNameIdHTable.put(dep.getDepartmentName(), dep.getDepID());
        }
        return rootDepartNameIdHTable;
    }
    
    public LinkedHashMap<String, Short> getChildDepartments() {

        TypedQuery<Department> query = em.createNamedQuery("Department.findChildDepartments", Department.class);
        List<Department> childDepList = query.getResultList();
        LinkedHashMap childDepartNameIdHTable = new LinkedHashMap();
        for (Department dep : childDepList) {
            childDepartNameIdHTable.put(dep.getDepartmentName(), dep.getDepID());
        }
        return childDepartNameIdHTable;
    }
    
    public LinkedHashMap<String, Short> getChildDepartments(Short DepID) {

        TypedQuery<Department> query = em.createNamedQuery("Department.findAllChildDeps", Department.class);
        query.setParameter("rootDepID", this.find(DepID));
        List<Department> childDepList = query.getResultList();
        LinkedHashMap childDepartNameIdHTable = new LinkedHashMap();
        for (Department dep : childDepList) {
            childDepartNameIdHTable.put(dep.getDepartmentName(), dep.getDepID());
        }
        return childDepartNameIdHTable;
    }
}
