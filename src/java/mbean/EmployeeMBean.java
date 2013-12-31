/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ejb.EJB;
import ejb.EmployeeFacade;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityExistsException;

/**
 *
 * @author Riad
 */
@ManagedBean(name = "employeeMBean")
@RequestScoped
public class EmployeeMBean {

    @EJB
    private EmployeeFacade empEjb;

    /**
     * Creates a new instance of EmployeeMBean
     */
    public EmployeeMBean() {
    }

    public void addNewEmployee() {

        try {
            empEjb.addEmployee(firstName, midName, surname, email, mobnum1, mobnum2, landline, personalIdType, personalIdNum, department, job);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", firstName + " " + surname + " added!"));
        } catch (EntityExistsException eExc) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", firstName + " " + surname + " already exists."));
        }
    }
    
    private String firstName, midName, surname, mobnum1, mobnum2, landline,
            personalIdNum, email;

    private Short personalIdType, department, job;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobnum1() {
        return mobnum1;
    }

    public void setMobnum1(String mobnum1) {
        this.mobnum1 = mobnum1;
    }

    public String getMobnum2() {
        return mobnum2;
    }

    public void setMobnum2(String mobnum2) {
        this.mobnum2 = mobnum2;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public Short getPersonalIdType() {
        return personalIdType;
    }

    public void setPersonalIdType(Short personalIdType) {
        this.personalIdType = personalIdType;
    }

    public String getPersonalIdNum() {
        return personalIdNum;
    }

    public void setPersonalIdNum(String personalIdNum) {
        this.personalIdNum = personalIdNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getDepartment() {
        return department;
    }

    public void setDepartment(Short department) {
        this.department = department;
    }

    public Short getJob() {
        return job;
    }

    public void setJob(Short job) {
        this.job = job;
    }
}
