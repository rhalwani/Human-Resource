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
public class LeaveBalancePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Emp_ID")
    private int empID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Leave_Year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Leave_Type_ID")
    private short leaveTypeID;

    public LeaveBalancePK() {
    }

    public LeaveBalancePK(int empID, int year, short leaveTypeID) {
        this.empID = empID;
        this.year = year;
        this.leaveTypeID = leaveTypeID;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public short getLeaveTypeID() {
        return leaveTypeID;
    }

    public void setLeaveTypeID(short leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empID;
        hash += (int) year;
        hash += (int) leaveTypeID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveBalancePK)) {
            return false;
        }
        LeaveBalancePK other = (LeaveBalancePK) object;
        if (this.empID != other.empID) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (this.leaveTypeID != other.leaveTypeID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LeaveBalancePK[ empID=" + empID + ", year=" + year + ", leaveTypeID=" + leaveTypeID + " ]";
    }
    
}
