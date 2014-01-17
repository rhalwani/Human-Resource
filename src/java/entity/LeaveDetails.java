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
@Table(name = "Leave_Details")
@NamedQueries({
    @NamedQuery(name = "LeaveDetails.findAll", query = "SELECT l FROM LeaveDetails l"),
    @NamedQuery(name = "LeaveDetails.findByLeaveID", query = "SELECT l FROM LeaveDetails l WHERE l.leaveDetailsPK.leaveID = :leaveID"),
    @NamedQuery(name = "LeaveDetails.findByLeaveYear", query = "SELECT l FROM LeaveDetails l WHERE l.leaveDetailsPK.leaveYear = :leaveYear"),
    @NamedQuery(name = "LeaveDetails.findByDaysStartBal", query = "SELECT l FROM LeaveDetails l WHERE l.daysStartBal = :daysStartBal"),
    @NamedQuery(name = "LeaveDetails.findByDaysEndBal", query = "SELECT l FROM LeaveDetails l WHERE l.daysEndBal = :daysEndBal"),
    @NamedQuery(name = "LeaveDetails.findByTicketsStartBal", query = "SELECT l FROM LeaveDetails l WHERE l.ticketsStartBal = :ticketsStartBal"),
    @NamedQuery(name = "LeaveDetails.findByTicketsEndBal", query = "SELECT l FROM LeaveDetails l WHERE l.ticketsEndBal = :ticketsEndBal")})
public class LeaveDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LeaveDetailsPK leaveDetailsPK;
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
    @JoinColumn(name = "Leave_ID", referencedColumnName = "Leave_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Leave leave;

    public LeaveDetails() {
    }

    public LeaveDetails(LeaveDetailsPK leaveDetailsPK) {
        this.leaveDetailsPK = leaveDetailsPK;
    }

    public LeaveDetails(LeaveDetailsPK leaveDetailsPK, int daysStartBal, int daysEndBal) {
        this.leaveDetailsPK = leaveDetailsPK;
        this.daysStartBal = daysStartBal;
        this.daysEndBal = daysEndBal;
    }

    public LeaveDetails(int leaveID, int leaveYear) {
        this.leaveDetailsPK = new LeaveDetailsPK(leaveID, leaveYear);
    }

    public LeaveDetailsPK getLeaveDetailsPK() {
        return leaveDetailsPK;
    }

    public void setLeaveDetailsPK(LeaveDetailsPK leaveDetailsPK) {
        this.leaveDetailsPK = leaveDetailsPK;
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

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leaveDetailsPK != null ? leaveDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveDetails)) {
            return false;
        }
        LeaveDetails other = (LeaveDetails) object;
        if ((this.leaveDetailsPK == null && other.leaveDetailsPK != null) || (this.leaveDetailsPK != null && !this.leaveDetailsPK.equals(other.leaveDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LeaveDetails[ leaveDetailsPK=" + leaveDetailsPK + " ]";
    }
    
}
