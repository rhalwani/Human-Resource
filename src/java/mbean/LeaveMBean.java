/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.LeaveFacade;
import ejb.LeaveBalanceFacade;
import ejb.LeaveDetailsFacade;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
    @EJB
    private LeaveDetailsFacade leaveDetEjb;
    
    private List<LeaveView> leaveModel;
    //private List <LeaveDetailView> leaveDetModel;

    private Date startDate, endDate;
    private int reqTickets, availDays, availTickets;
    private long reqDays;
    private Integer employeeId;
    private Short LeaveType, departmentId;

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

    public Short getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Short departmentId) {
        this.departmentId = departmentId;
    }

    public void addLeave() {
        leaveEjb.addLeave(this.getEmployeeId(), this.getLeaveType(), getStartDate(), getEndDate(), this.getReqTickets(), 1);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Leave added!"));
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

    public void searchLeave() {
        ArrayList<Object[]> leaveList = leaveEjb.searchForLeave(this.getEmployeeId());

        if (leaveList != null) {
            leaveModel = new ArrayList(leaveList.size());
            for (Object[] empLeave : leaveList) {
                LeaveView lView = new LeaveView((Integer) empLeave[0], (String) empLeave[1], (String) empLeave[2], (String) empLeave[3], (Date) empLeave[4], (Date) empLeave[5]);
                leaveModel.add(lView);
                ArrayList<Integer[]> leaveDetailList = leaveDetEjb.searchLeaveDetail((Integer)empLeave[0]);
                if(leaveDetailList != null) {
                    List<LeaveDetailView> lvDtVList = new ArrayList(leaveDetailList.size());
                    for (Integer[] empLeaveDetail : leaveDetailList) {
                        lvDtVList.add(new LeaveDetailView((Integer) empLeave[0], (Integer) empLeaveDetail[1], (Integer) empLeaveDetail[2], (Integer) empLeaveDetail[3], (Integer) empLeaveDetail[4], (Integer) empLeaveDetail[5]));
                    }
                    lView.setLeaveDetailsList(lvDtVList);
                }
            }
        }
    }

    public void startDateChanged(SelectEvent event) {
        startDate = (Date) event.getObject();
        if (startDate != null && endDate != null) {
            Calendar calendarStart = new java.util.GregorianCalendar();
            Calendar calendarEnd = new java.util.GregorianCalendar();
            calendarStart.setTime(startDate);
            calendarEnd.setTime(endDate);
            reqDays = (calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis()) / (24 * 60 * 60 * 1000);
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
        }
    }

    public void selectedLeaveTypeChanged(ValueChangeEvent vce) {
        LeaveType = Short.valueOf(vce.getNewValue().toString());
        if (LeaveType != null && employeeId != null) {
            Iterator<Integer> resourceIter = leaveBalEjb.getDaysBalance(employeeId, LeaveType).values().iterator();
            availDays = 0;
            int ir = 0;
            while (resourceIter.hasNext()) {
                availDays += resourceIter.next();
            }

            resourceIter = leaveBalEjb.getTicketsBalance(employeeId, LeaveType).values().iterator();
            availTickets = 0;
            ir = 0;
            while (resourceIter.hasNext()) {
                availTickets += resourceIter.next();
            }
        }
    }

    public void selectedEmployeeChanged(ValueChangeEvent vce) {
        employeeId = Integer.valueOf(vce.getNewValue().toString());
        if (LeaveType != null && employeeId != null) {
            Iterator<Integer> resourceIter = leaveBalEjb.getDaysBalance(employeeId, LeaveType).values().iterator();
            availDays = 0;
            int ir = 0;
            while (resourceIter.hasNext()) {
                availDays += resourceIter.next();
            }

            resourceIter = leaveBalEjb.getTicketsBalance(employeeId, LeaveType).values().iterator();
            availTickets = 0;
            ir = 0;
            while (resourceIter.hasNext()) {
                availTickets += resourceIter.next();
            }
        }
    }

    public List<LeaveView> getLeaveModel() {
        return leaveModel;
    }

    public void setLeaveModel(List<LeaveView> leaveModel) {
        this.leaveModel = leaveModel;
    }

    public class LeaveView {

        private String leaveType, employee, approve;
        private Date stdate, edate;
        private int leaveId;
        private List<LeaveDetailView> leaveDetailsList;

        public LeaveView() {

        }

        public LeaveView(int leaveId, String leaveType, String employee, String approve, Date stdate, Date edate) {
            this.leaveType = leaveType;
            this.employee = employee;
            this.approve = approve;
            this.stdate = stdate;
            this.edate = edate;
            this.leaveId = leaveId;
        }

        public int getLeaveId() {
            return leaveId;
        }

        public void setLeaveId(int leaveId) {
            this.leaveId = leaveId;
        }

        public String getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(String leaveType) {
            this.leaveType = leaveType;
        }

        public String getEmployee() {
            return employee;
        }

        public void setEmployee(String employee) {
            this.employee = employee;
        }

        public String getApprove() {
            return approve;
        }

        public void setApprove(String approve) {
            this.approve = approve;
        }

        public Date getStdate() {
            return stdate;
        }

        public void setStdate(Date stdate) {
            this.stdate = stdate;
        }

        public Date getEdate() {
            return edate;
        }

        public void setEdate(Date edate) {
            this.edate = edate;
        }

        public List<LeaveDetailView> getLeaveDetailsList() {
            return leaveDetailsList;
        }

        public void setLeaveDetailsList(List<LeaveDetailView> leaveDetailsList) {
            this.leaveDetailsList = leaveDetailsList;
        }

    }

    public class LeaveDetailView {

        private int leaveId, leaveYear, daysStartBal, daysEndBal, ticketsStartBal, ticketsEndBal;

        public LeaveDetailView() {
        }

        public LeaveDetailView(int leaveId, int leaveYear, int daysStartBal, int daysEndBal, int ticketsStartBal, int ticketsEndBal) {
            this.leaveId = leaveId;
            this.leaveYear = leaveYear;
            this.daysStartBal = daysStartBal;
            this.daysEndBal = daysEndBal;
            this.ticketsStartBal = ticketsStartBal;
            this.ticketsEndBal = ticketsEndBal;
        }

        public int getLeaveId() {
            return leaveId;
        }

        public void setLeaveId(int leaveId) {
            this.leaveId = leaveId;
        }

        public int getLeaveYear() {
            return leaveYear;
        }

        public void setLeaveYear(int leaveYear) {
            this.leaveYear = leaveYear;
        }

        public int getDaysStartBal() {
            return daysStartBal;
        }

        public void setDaysStartBal(int daysStartBal) {
            this.daysStartBal = daysStartBal;
        }

        public int getDaysEndBal() {
            return daysEndBal;
        }

        public void setDaysEndBal(int daysEndBal) {
            this.daysEndBal = daysEndBal;
        }

        public int getTicketsStartBal() {
            return ticketsStartBal;
        }

        public void setTicketsStartBal(int ticketsStartBal) {
            this.ticketsStartBal = ticketsStartBal;
        }

        public int getTicketsEndBal() {
            return ticketsEndBal;
        }

        public void setTicketsEndBal(int ticketsEndBal) {
            this.ticketsEndBal = ticketsEndBal;
        }
    }
}
