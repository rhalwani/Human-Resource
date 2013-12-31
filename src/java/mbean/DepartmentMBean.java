/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ejb.EJB;
import ejb.DepartmentFacade;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Riad
 */
@ManagedBean(name = "departmentMBean")
@RequestScoped
public class DepartmentMBean {

    @EJB
    private DepartmentFacade depEjb;
    private String departName;
    private Short rootDepID, parentDepID;
    private Integer departMgr;

    /**
     * Creates a new instance of EmployeeMBean
     */
    public DepartmentMBean() {
    }

    public void addNewDepartment() {
        //System.out.println("Department "+departName+", "+rootDepID.toString()+", "+parentDepID.toString());
        try {
            depEjb.addNewDepartment(departName, rootDepID, parentDepID, departMgr);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", departName + " added!"));   
        } catch(EntityExistsException ee) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", departName + " already exists.\n"+ee.getMessage()));   
        } catch (ConstraintViolationException cve) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", departName + " should be unique.\n"+cve.getMessage()));   
        }
    }
    
    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Short getRootDepID() {
        return rootDepID;
    }

    public void setRootDepID(Short rootDepID) {
        this.rootDepID = rootDepID;
    }

    public Short getParentDepID() {
        return parentDepID;
    }

    public void setParentDepID(Short parentDepID) {
        this.parentDepID = parentDepID;
    }

    public Integer getDepartMgr() {
        return departMgr;
    }

    public void setDepartMgr(Integer departMgr) {
        this.departMgr = departMgr;
    }

}
