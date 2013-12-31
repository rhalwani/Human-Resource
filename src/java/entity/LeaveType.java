/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Riad
 */
@Entity
@Table(name = "Leave_Type")
@NamedQueries({
    @NamedQuery(name = "LeaveType.findAll", query = "SELECT l FROM LeaveType l"),
    @NamedQuery(name = "LeaveType.findByLeaveTypeID", query = "SELECT l FROM LeaveType l WHERE l.leaveTypeID = :leaveTypeID"),
    @NamedQuery(name = "LeaveType.findByLeaveDescription", query = "SELECT l FROM LeaveType l WHERE l.leaveDescription = :leaveDescription")})
public class LeaveType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Leave_Type_ID")
    private Short leaveTypeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Leave_Description")
    private String leaveDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leaveTypeID")
    private Collection<Leave> leaveCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leaveType")
    private Collection<LeaveBalance> leaveBalanceCollection;

    public LeaveType() {
    }

    public LeaveType(Short leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public LeaveType(Short leaveTypeID, String leaveDescription) {
        this.leaveTypeID = leaveTypeID;
        this.leaveDescription = leaveDescription;
    }

    public Short getLeaveTypeID() {
        return leaveTypeID;
    }

    public void setLeaveTypeID(Short leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public String getLeaveDescription() {
        return leaveDescription;
    }

    public void setLeaveDescription(String leaveDescription) {
        this.leaveDescription = leaveDescription;
    }

    public Collection<Leave> getLeaveCollection() {
        return leaveCollection;
    }

    public void setLeaveCollection(Collection<Leave> leaveCollection) {
        this.leaveCollection = leaveCollection;
    }

    public Collection<LeaveBalance> getLeaveBalanceCollection() {
        return leaveBalanceCollection;
    }

    public void setLeaveBalanceCollection(Collection<LeaveBalance> leaveBalanceCollection) {
        this.leaveBalanceCollection = leaveBalanceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leaveTypeID != null ? leaveTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveType)) {
            return false;
        }
        LeaveType other = (LeaveType) object;
        if ((this.leaveTypeID == null && other.leaveTypeID != null) || (this.leaveTypeID != null && !this.leaveTypeID.equals(other.leaveTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LeaveType[ leaveTypeID=" + leaveTypeID + " ]";
    }
    
}
