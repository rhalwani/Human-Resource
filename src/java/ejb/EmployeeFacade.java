/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Employee;
import entity.Job;
import entity.Department;
import entity.PersonalID;
import java.util.LinkedHashMap;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Riad
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> {
    @PersistenceContext(unitName = "HRPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeFacade() {
        super(Employee.class);
    }
    
    public java.util.LinkedHashMap<String, Integer> getEmployeeNamesId() {
        
        java.util.List<Employee> allEmployeeList = this.findAll();
        java.util.LinkedHashMap<String, Integer> employeeNameIdHTable = new LinkedHashMap(allEmployeeList.size());

        for(Employee emp: allEmployeeList) {
            employeeNameIdHTable.put(emp.getFirstName()+" "+emp.getLastName()+" ("+emp.getEmpID()+") ", emp.getEmpID());
        }
        return employeeNameIdHTable;
        
    }
    
    public void addEmployee(String firstName, String middleName, String surname, String email, String mobile1, String mobile2,
            String landline, Short personalIdType, String personalIdNum, Short departmentId, Short jobId) throws EntityExistsException {
        /*
        midName.isEmpty()?null:midName, surname, email.isEmpty()?null:email, mobnum1.isEmpty()?null:mobnum1, mobnum2.isEmpty()?null:mobnum2,
                    landline.isEmpty()?null:landline, personalIdType==Short.valueOf((short)0)?null:personalIdType,personalIdNum.isEmpty()?null:personalIdNum, department, job);
        */
        Employee emp = new Employee();
        emp.setFirstName(firstName);
        emp.setMiddleName(middleName.isEmpty() ? null: middleName);
        emp.setLastName(surname);
        emp.setEmail(email.isEmpty() ? null:email);
        emp.setMobile1(mobile1.isEmpty() ? null:mobile1);
        emp.setMobile2(mobile2.isEmpty() ? null:mobile2);
        emp.setLandline(landline.isEmpty() ? null:landline);
        emp.setPersonalIDType(personalIdType==Short.valueOf((short)0) ? null:em.find(PersonalID.class, personalIdType));
        emp.setPersonalIDNum(personalIdNum.isEmpty() ? null:personalIdNum);
        emp.setJobID(jobId==Short.valueOf((short)0) ? null:em.find(Job.class, jobId));
        emp.setDepID(departmentId==Short.valueOf((short)0) ? null:em.find(Department.class, departmentId));

        em.persist(emp);
    }
}
