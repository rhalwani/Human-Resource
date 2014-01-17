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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Riad
 */
@Entity
@Table(name = "Leave")
@NamedQueries({
    @NamedQuery(name = "Leave.findAll", query = "SELECT l FROM Leave l"),
    @NamedQuery(name = "Leave.findByLeaveID", query = "SELECT l FROM Leave l WHERE l.leaveID = :leaveID"),
    @NamedQuery(name = "Leave.findByStartDate", query = "SELECT l FROM Leave l WHERE l.startDate = :startDate"),
    @NamedQuery(name = "Leave.findByEndDate", query = "SELECT l FROM Leave l WHERE l.endDate = :endDate")})
public class Leave implements Serializable {
    @JoinColumn(name = "Leave_Type_ID", referencedColumnName = "Leave_Type_ID")
    @ManyToOne(optional = false)
    private LeaveType leaveTypeID;
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "Leave_ID")
    private Integer leaveID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Start_Date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "End_Date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "Emp_ID", referencedColumnName = "Emp_ID")
    @ManyToOne(optional = false)
    private Employee empID;
    @JoinColumn(name = "Approved_By", referencedColumnName = "Emp_ID")
    @ManyToOne
    private Employee approvedBy;

    public Leave() {
    }

    public Leave(Integer leaveID) {
        this.leaveID = leaveID;
    }

    public Leave(Integer leaveID, Date startDate, Date endDate) {
        this.leaveID = leaveID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(Integer leaveID) {
        this.leaveID = leaveID;
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

    public Employee getEmpID() {
        return empID;
    }

    public void setEmpID(Employee empID) {
        this.empID = empID;
    }

    public Employee getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Employee approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leaveID != null ? leaveID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leave)) {
            return false;
        }
        Leave other = (Leave) object;
        if ((this.leaveID == null && other.leaveID != null) || (this.leaveID != null && !this.leaveID.equals(other.leaveID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Leave[ leaveID=" + leaveID + " ]";
    }

    public LeaveType getLeaveTypeID() {
        return leaveTypeID;
    }

    public void setLeaveTypeID(LeaveType leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }
    
}
