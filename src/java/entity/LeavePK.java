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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Riad
 */
@Embeddable
public class LeavePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Emp_ID")
    private int empID;
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

    public LeavePK() {
    }

    public LeavePK(int empID, Date startDate, Date endDate) {
        this.empID = empID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empID;
        hash += (startDate != null ? startDate.hashCode() : 0);
        hash += (endDate != null ? endDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeavePK)) {
            return false;
        }
        LeavePK other = (LeavePK) object;
        if (this.empID != other.empID) {
            return false;
        }
        if ((this.startDate == null && other.startDate != null) || (this.startDate != null && !this.startDate.equals(other.startDate))) {
            return false;
        }
        if ((this.endDate == null && other.endDate != null) || (this.endDate != null && !this.endDate.equals(other.endDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LeavePK[ empID=" + empID + ", startDate=" + startDate + ", endDate=" + endDate + " ]";
    }
    
}
