/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.LeaveDetails;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

    public ArrayList<Integer[]> searchLeaveDetail(int leaveID) {

        TypedQuery query = em.createNamedQuery("LeaveDetails.findByLeaveID", LeaveDetails.class);
        List<LeaveDetails> leaveDetList = query.setParameter("leaveID", leaveID).getResultList();
        if (!leaveDetList.isEmpty()) {
            ArrayList<Integer[]> leaveDetailMatrixList = new ArrayList(leaveDetList.size());
            Integer[] leaveDetailMatrix;
            for (LeaveDetails leaveDet : leaveDetList) {
                leaveDetailMatrix = new Integer[6];
                leaveDetailMatrix[0] = leaveDet.getLeaveDetailsPK().getLeaveID();
                leaveDetailMatrix[1] = leaveDet.getLeaveDetailsPK().getLeaveYear();
                leaveDetailMatrix[2] = leaveDet.getDaysStartBal();
                leaveDetailMatrix[3] = leaveDet.getDaysEndBal();
                leaveDetailMatrix[4] = leaveDet.getTicketsStartBal();
                leaveDetailMatrix[5] = leaveDet.getTicketsEndBal();
                leaveDetailMatrixList.add(leaveDetailMatrix);
            }
            return leaveDetailMatrixList;
        } else
            return null;
    }
}
