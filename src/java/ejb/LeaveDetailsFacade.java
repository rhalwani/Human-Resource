/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.LeaveDetails;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Riad
 */
@Stateless
public class LeaveDetailsFacade extends AbstractFacade<LeaveDetails> {
    @PersistenceContext(unitName = "HRPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LeaveDetailsFacade() {
        super(LeaveDetails.class);
    }
    
    public void addLeaveDetail(int leaveID, int leaveYear, int startDaysBal, int endDaysBal, int startTicketBal, int endTicketBal) {
        
        LeaveDetails ld = new LeaveDetails(leaveID, leaveYear);
        ld.setDaysStartBal(startDaysBal);
        ld.setDaysEndBal(endDaysBal);
        ld.setTicketsStartBal(startTicketBal);
        ld.setTicketsEndBal(endTicketBal);
        
        em.persist(ld);
    }
}
