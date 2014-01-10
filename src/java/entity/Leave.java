/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Riad
 */
@Entity
@Table(name = "Leave")
@NamedQueries({
    @NamedQuery(name = "Leave.findAll", query = "SELECT l FROM Leave l"),
    @NamedQuery(name = "Leave.findByEmpID", query = "SELECT l FROM Leave l WHERE l.leavePK.empID = :empID"),
    @NamedQuery(name = "Leave.findByStartDate", query = "SELECT l FROM Leave l WHERE l.leavePK.startDate = :startDate"),
    @NamedQuery(name = "Leave.findByEndDate", query = "SELECT l FROM Leave l WHERE l.leavePK.endDate = :endDate"),
    @NamedQuery(name = "Leave.findByDaysStartBal", query = "SELECT l FROM Leave l WHERE l.daysStartBal = :daysStartBal"),
    @NamedQuery(name = "Leave.findByDaysEndBal", query = "SELECT l FROM Leave l WHERE l.daysEndBal = :daysEndBal"),
    @NamedQuery(name = "Leave.findByTicketsStartBal", query = "SELECT l FROM Leave l WHERE l.ticketsStartBal = :ticketsStartBal"),
    @NamedQuery(name = "Leave.findByTicketsEndBal", query = "SELECT l FROM Leave l WHERE l.ticketsEndBal = :ticketsEndBal")})

public class Leave implements Serializable {
    @Column(name = "Prev_Year_Days_Start_Bal")
    private Integer prevYearDaysStartBal;
    @Column(name = "Prev_Year_Days_End_Bal")
    private Integer prevYearDaysEndBal;
    @Column(name = "Curr_Year_Days_Start_Bal")
    private Integer currYearDaysStartBal;
    @Column(name = "Curr_Year_Days_End_Bal")
    private Integer currYearDaysEndBal;
    @Column(name = "Next_Year_Days_Start_Bal")
    private Integer nextYearDaysStartBal;
    @Column(name = "Next_Year_Days_End_Bal")
    private Integer nextYearDaysEndBal;
    @Column(name = "Prev_Year_Tickets_Start_Bal")
    private Integer prevYearTicketsStartBal;
    @Column(name = "Prev_Year_Tickets_End_Bal")
    private Integer prevYearTicketsEndBal;
    @Column(name = "Curr_Year_Tickets_Start_Bal")
    private Integer currYearTicketsStartBal;
    @Column(name = "Curr_Year_Tickets_End_Bal")
    private Integer currYearTicketsEndBal;
    @Column(name = "Next_Year_Tickets_Start_Bal")
    private Integer nextYearTicketsStartBal;
    @Column(name = "Next_Year_Tickets_End_Bal")
    private Integer nextYearTicketsEndBal;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LeavePK leavePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Days_Start_Bal")
    private int daysStartBal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Days_End_Bal")
    private int daysEndBal;
    @Column(name = "Tickets_Start_Bal")
    private Integer ticketsStartBal;
    @Column(name = "Tickets_End_Bal")
    private Integer ticketsEndBal;
    @JoinColumn(name = "Leave_Type_ID", referencedColumnName = "Leave_Type_ID")
    @ManyToOne(optional = false)
    private LeaveType leaveTypeID;
    @JoinColumn(name = "Approved_By", referencedColumnName = "Emp_ID")
    @ManyToOne
    private Employee approvedBy;
    @JoinColumn(name = "Emp_ID", referencedColumnName = "Emp_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employee employee;

    public Leave() {
    }

    public Leave(LeavePK leavePK) {
        this.leavePK = leavePK;
    }

    public Leave(LeavePK leavePK, int daysStartBal, int daysEndBal) {
        this.leavePK = leavePK;
        this.daysStartBal = daysStartBal;
        this.daysEndBal = daysEndBal;
    }

    public Leave(int empID, Date startDate, Date endDate) {
        this.leavePK = new LeavePK(empID, startDate, endDate);
    }

    public LeavePK getLeavePK() {
        return leavePK;
    }

    public void setLeavePK(LeavePK leavePK) {
        this.leavePK = leavePK;
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

    public Integer getTicketsStartBal() {
        return ticketsStartBal;
    }

    public void setTicketsStartBal(Integer ticketsStartBal) {
        this.ticketsStartBal = ticketsStartBal;
    }

    public Integer getTicketsEndBal() {
        return ticketsEndBal;
    }

    public void setTicketsEndBal(Integer ticketsEndBal) {
        this.ticketsEndBal = ticketsEndBal;
    }

    public LeaveType getLeaveTypeID() {
        return leaveTypeID;
    }

    public void setLeaveTypeID(LeaveType leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public Employee getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Employee approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leavePK != null ? leavePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leave)) {
            return false;
        }
        Leave other = (Leave) object;
        if ((this.leavePK == null && other.leavePK != null) || (this.leavePK != null && !this.leavePK.equals(other.leavePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Leave[ leavePK=" + leavePK + " ]";
    }

    public Integer getPrevYearDaysStartBal() {
        return prevYearDaysStartBal;
    }

    public void setPrevYearDaysStartBal(Integer prevYearDaysStartBal) {
        this.prevYearDaysStartBal = prevYearDaysStartBal;
    }

    public Integer getPrevYearDaysEndBal() {
        return prevYearDaysEndBal;
    }

    public void setPrevYearDaysEndBal(Integer prevYearDaysEndBal) {
        this.prevYearDaysEndBal = prevYearDaysEndBal;
    }

    public Integer getCurrYearDaysStartBal() {
        return currYearDaysStartBal;
    }

    public void setCurrYearDaysStartBal(Integer currYearDaysStartBal) {
        this.currYearDaysStartBal = currYearDaysStartBal;
    }

    public Integer getCurrYearDaysEndBal() {
        return currYearDaysEndBal;
    }

    public void setCurrYearDaysEndBal(Integer currYearDaysEndBal) {
        this.currYearDaysEndBal = currYearDaysEndBal;
    }

    public Integer getNextYearDaysStartBal() {
        return nextYearDaysStartBal;
    }

    public void setNextYearDaysStartBal(Integer nextYearDaysStartBal) {
        this.nextYearDaysStartBal = nextYearDaysStartBal;
    }

    public Integer getNextYearDaysEndBal() {
        return nextYearDaysEndBal;
    }

    public void setNextYearDaysEndBal(Integer nextYearDaysEndBal) {
        this.nextYearDaysEndBal = nextYearDaysEndBal;
    }

    public Integer getPrevYearTicketsStartBal() {
        return prevYearTicketsStartBal;
    }

    public void setPrevYearTicketsStartBal(Integer prevYearTicketsStartBal) {
        this.prevYearTicketsStartBal = prevYearTicketsStartBal;
    }

    public Integer getPrevYearTicketsEndBal() {
        return prevYearTicketsEndBal;
    }

    public void setPrevYearTicketsEndBal(Integer prevYearTicketsEndBal) {
        this.prevYearTicketsEndBal = prevYearTicketsEndBal;
    }

    public Integer getCurrYearTicketsStartBal() {
        return currYearTicketsStartBal;
    }

    public void setCurrYearTicketsStartBal(Integer currYearTicketsStartBal) {
        this.currYearTicketsStartBal = currYearTicketsStartBal;
    }

    public Integer getCurrYearTicketsEndBal() {
        return currYearTicketsEndBal;
    }

    public void setCurrYearTicketsEndBal(Integer currYearTicketsEndBal) {
        this.currYearTicketsEndBal = currYearTicketsEndBal;
    }

    public Integer getNextYearTicketsStartBal() {
        return nextYearTicketsStartBal;
    }

    public void setNextYearTicketsStartBal(Integer nextYearTicketsStartBal) {
        this.nextYearTicketsStartBal = nextYearTicketsStartBal;
    }

    public Integer getNextYearTicketsEndBal() {
        return nextYearTicketsEndBal;
    }

    public void setNextYearTicketsEndBal(Integer nextYearTicketsEndBal) {
        this.nextYearTicketsEndBal = nextYearTicketsEndBal;
    }
    
}
