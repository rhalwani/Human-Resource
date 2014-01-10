/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbean;

import ejb.LeaveFacade;
import ejb.LeaveBalanceFacade;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Riad
 */
@ManagedBean
@RequestScoped
public class LeaveMBean {

    @EJB
    private LeaveFacade leaveEjb;
    
    private Date startDate, endDate;
    private Integer employeeId;
    private Short LeaveType;
    /**
     * Creates a new instance of LeaveMBean
     */
    public LeaveMBean() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Short getLeaveType() {
        return LeaveType;
    }

    public void setLeaveType(Short LeaveType) {
        this.LeaveType = LeaveType;
    }
    
    public void addLeave() {
        System.out.println(getLeaveType()+" Leave for "+getEmployeeId()+" starts on "+getStartDate()+", ends on "+getEndDate());
        
    }
    
}
