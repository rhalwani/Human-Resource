/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.LeaveFacade;
import ejb.LeaveBalanceFacade;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
//import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Riad
 */
@ManagedBean
@ViewScoped
public class LeaveMBean implements java.io.Serializable {

    @EJB
    private LeaveFacade leaveEjb;
    @EJB
    private LeaveBalanceFacade leaveBalEjb;

    private Date startDate, endDate;
    private int reqTickets, availDays, availTickets;
    private long reqDays;
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
        System.out.println(getLeaveType() + " Leave for " + getEmployeeId() + " starts on " + getStartDate() + ", ends on " + getEndDate());
        leaveEjb.addLeave(this.getEmployeeId(), this.getLeaveType(), getStartDate(), getEndDate(), this.getReqTickets(), 1);
    }

    public int getReqTickets() {
        return reqTickets;
    }

    public void setReqTickets(int reqTickets) {
        this.reqTickets = reqTickets;
    }

    public int getAvailDays() {
        return availDays;
    }

    public void setAvailDays(int availDays) {
        this.availDays = availDays;
    }

    public int getAvailTickets() {
        return availTickets;
    }

    public long getReqDays() {
        return reqDays;
    }

    public void setReqDays(long reqDays) {
        this.reqDays = reqDays;
    }

    public void setAvailTickets(int availTickets) {
        this.availTickets = availTickets;
    }

    public void startDateChanged(SelectEvent event) {
        startDate = (Date) event.getObject();
        if (startDate != null && endDate != null) {
            Calendar calendarStart = new java.util.GregorianCalendar();
            Calendar calendarEnd = new java.util.GregorianCalendar();
            calendarStart.setTime(startDate);
            calendarEnd.setTime(endDate);
            reqDays = (calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis()) / (24 * 60 * 60 * 1000);
            System.out.println("Requested Days = "+reqDays);
        }
    }

    public void endDateChanged(SelectEvent event) {
        endDate = (Date) event.getObject();
        if (startDate != null && endDate != null) {
            Calendar calendarStart = new java.util.GregorianCalendar();
            Calendar calendarEnd = new java.util.GregorianCalendar();
            calendarStart.setTime(startDate);
            calendarEnd.setTime(endDate);
            reqDays = (calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis()) / (24 * 60 * 60 * 1000);
            System.out.println("Requested Days = "+reqDays);
        }
    }

    public void selectedLeaveTypeChanged(ValueChangeEvent vce) {
        LeaveType = Short.valueOf(vce.getNewValue().toString());
        if (LeaveType != null && employeeId != null) {
            System.out.println("Employee Selected: " + employeeId.toString() + " for leave type " + LeaveType.toString());
            Iterator<Integer> resourceIter = leaveBalEjb.getDaysBalance(employeeId, LeaveType).values().iterator();
            availDays = 0;
            int ir = 0;
            while (resourceIter.hasNext()) {
                System.out.println("Days Iteration " + (++ir));
                availDays += resourceIter.next();
            }

            resourceIter = leaveBalEjb.getTicketsBalance(employeeId, LeaveType).values().iterator();
            availTickets = 0;
            ir = 0;
            while (resourceIter.hasNext()) {
                System.out.println("Tickets Iteration " + (++ir));
                availTickets += resourceIter.next();
            }

            System.out.println("Available Days: " + availDays + ", available Tickets: " + availTickets);
        } else {
            System.out.println("Employee or Leave type is not selected.\nEmployee = " + employeeId + "\nLeave = " + LeaveType);
        }
    }

    public void selectedEmployeeChanged(ValueChangeEvent vce) {
        employeeId = Integer.valueOf(vce.getNewValue().toString());
        if (LeaveType != null && employeeId != null) {
            System.out.println("Employee Selected: " + employeeId.toString() + " for leave type " + LeaveType.toString());
            Iterator<Integer> resourceIter = leaveBalEjb.getDaysBalance(employeeId, LeaveType).values().iterator();
            availDays = 0;
            int ir = 0;
            while (resourceIter.hasNext()) {
                System.out.println("Days Iteration " + (++ir));
                availDays += resourceIter.next();
            }

            resourceIter = leaveBalEjb.getTicketsBalance(employeeId, LeaveType).values().iterator();
            availTickets = 0;
            ir = 0;
            while (resourceIter.hasNext()) {
                System.out.println("Tickets Iteration " + (++ir));
                availTickets += resourceIter.next();
            }

            System.out.println("Available Days: " + availDays + ", available Tickets: " + availTickets);
        } else {
            System.out.println("Employee or Leave type is not selected.\nEmployee = " + employeeId + "\nLeave = " + LeaveType);
        }
    }

}
