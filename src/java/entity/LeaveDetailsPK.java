/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Riad
 */
@Embeddable
public class LeaveDetailsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Leave_ID")
    private int leaveID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Leave_Year")
    private int leaveYear;

    public LeaveDetailsPK() {
    }

    public LeaveDetailsPK(int leaveID, int leaveYear) {
        this.leaveID = leaveID;
        this.leaveYear = leaveYear;
    }

    public int getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(int leaveID) {
        this.leaveID = leaveID;
    }

    public int getLeaveYear() {
        return leaveYear;
    }

    public void setLeaveYear(int leaveYear) {
        this.leaveYear = leaveYear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) leaveID;
        hash += (int) leaveYear;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveDetailsPK)) {
            return false;
        }
        LeaveDetailsPK other = (LeaveDetailsPK) object;
        if (this.leaveID != other.leaveID) {
            return false;
        }
        if (this.leaveYear != other.leaveYear) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LeaveDetailsPK[ leaveID=" + leaveID + ", leaveYear=" + leaveYear + " ]";
    }
    
}
