/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
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
@Table(name = "Leave_Balance")
@NamedQueries({
    @NamedQuery(name = "LeaveBalance.findAll", query = "SELECT l FROM LeaveBalance l"),
    @NamedQuery(name = "LeaveBalance.findByEmpID", query = "SELECT l FROM LeaveBalance l WHERE l.leaveBalancePK.empID = :empID"),
    @NamedQuery(name = "LeaveBalance.findByYear", query = "SELECT l FROM LeaveBalance l WHERE l.leaveBalancePK.year = :year"),
    @NamedQuery(name = "LeaveBalance.findByLeaveTypeID", query = "SELECT l FROM LeaveBalance l WHERE l.leaveBalancePK.leaveTypeID = :leaveTypeID"),
    @NamedQuery(name = "LeaveBalance.findByDaysBal", query = "SELECT l FROM LeaveBalance l WHERE l.daysBal = :daysBal"),
    @NamedQuery(name = "LeaveBalance.findByTicketsBal", query = "SELECT l FROM LeaveBalance l WHERE l.ticketsBal = :ticketsBal"),
    @NamedQuery(name = "LeaveBalance.findAvailableByEmpID", query = "SELECT l FROM LeaveBalance l WHERE l.leaveBalancePK.empID = :empID and l.expired = false"),
    @NamedQuery(name = "LeaveBalance.findExpiredByEmpID", query = "SELECT l FROM LeaveBalance l WHERE l.leaveBalancePK.empID = :empID and l.expired = true"),
    @NamedQuery(name = "LeaveBalance.findAvailableByYear", query = "SELECT l FROM LeaveBalance l WHERE l.leaveBalancePK.year = :year and l.expired = false"),
    @NamedQuery(name = "LeaveBalance.findExpiredByYear", query = "SELECT l FROM LeaveBalance l WHERE l.leaveBalancePK.year = :year and l.expired = true"),
    @NamedQuery(name = "LeaveBalance.findAvailableByLeaveTypeID", query = "SELECT l FROM LeaveBalance l WHERE l.leaveBalancePK.leaveTypeID = :leaveTypeID and l.expired = false"),
    @NamedQuery(name = "LeaveBalance.findExpiredByLeaveTypeID", query = "SELECT l FROM LeaveBalance l WHERE l.leaveBalancePK.leaveTypeID = :leaveTypeID and l.expired = true"),
    @NamedQuery(name = "LeaveBalance.findAvailableByDaysBal", query = "SELECT l FROM LeaveBalance l WHERE l.daysBal = :daysBal and l.expired = false"),
    @NamedQuery(name = "LeaveBalance.findExpiredByDaysBal", query = "SELECT l FROM LeaveBalance l WHERE l.daysBal = :daysBal and l.expired = true"),
    @NamedQuery(name = "LeaveBalance.findAvailableByTicketsBal", query = "SELECT l FROM LeaveBalance l WHERE l.ticketsBal = :ticketsBal and l.expired = false"),
    @NamedQuery(name = "LeaveBalance.findExpiredByTicketsBal", query = "SELECT l FROM LeaveBalance l WHERE l.ticketsBal = :ticketsBal and l.expired = true"),
    @NamedQuery(name = "LeaveBalance.findAvailByEmpIDandLeaveType", query = "SELECT l FROM LeaveBalance l WHERE l.leaveBalancePK.empID = :empID and l.leaveBalancePK.leaveTypeID = :leaveTypeID and l.expired = false and l.daysBal > 0 order by l.leaveBalancePK.year"),
    @NamedQuery(name = "LeaveBalance.findExpiredByEmpIDandLeaveType", query = "SELECT l FROM LeaveBalance l WHERE l.leaveBalancePK.empID = :empID and l.leaveBalancePK.leaveTypeID = :leaveTypeID and l.expired = true")
})
public class LeaveBalance implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Expired")
    private boolean expired;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LeaveBalancePK leaveBalancePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Days_Bal")
    private int daysBal;
    @Column(name = "Tickets_Bal")
    private Integer ticketsBal;
    @JoinColumn(name = "Leave_Type_ID", referencedColumnName = "Leave_Type_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LeaveType leaveType;
    @JoinColumn(name = "Emp_ID", referencedColumnName = "Emp_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employee employee;

    public LeaveBalance() {
    }

    public LeaveBalance(LeaveBalancePK leaveBalancePK) {
        this.leaveBalancePK = leaveBalancePK;
    }

    public LeaveBalance(LeaveBalancePK leaveBalancePK, int daysBal) {
        this.leaveBalancePK = leaveBalancePK;
        this.daysBal = daysBal;
    }

    public LeaveBalance(int empID, int year, short leaveTypeID) {
        this.leaveBalancePK = new LeaveBalancePK(empID, year, leaveTypeID);
    }

    public LeaveBalancePK getLeaveBalancePK() {
        return leaveBalancePK;
    }

    public void setLeaveBalancePK(LeaveBalancePK leaveBalancePK) {
        this.leaveBalancePK = leaveBalancePK;
    }

    public int getDaysBal() {
        return daysBal;
    }

    public void setDaysBal(int daysBal) {
        this.daysBal = daysBal;
    }

    public Integer getTicketsBal() {
        return ticketsBal;
    }

    public void setTicketsBal(Integer ticketsBal) {
        this.ticketsBal = ticketsBal;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
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
        hash += (leaveBalancePK != null ? leaveBalancePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveBalance)) {
            return false;
        }
        LeaveBalance other = (LeaveBalance) object;
        if ((this.leaveBalancePK == null && other.leaveBalancePK != null) || (this.leaveBalancePK != null && !this.leaveBalancePK.equals(other.leaveBalancePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LeaveBalance[ leaveBalancePK=" + leaveBalancePK + " ]";
    }

    public boolean getExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
    
}
