/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.LeaveBalance;
import entity.LeaveBalancePK;
import java.util.LinkedHashMap;
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
public class LeaveBalanceFacade extends AbstractFacade<LeaveBalance> {
    @PersistenceContext(unitName = "HRPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LeaveBalanceFacade() {
        super(LeaveBalance.class);
    }
    
    public LinkedHashMap<Integer, Integer> getDaysBalance(Integer employeeId, Short leaveTypeId) {
        TypedQuery query = em.createNamedQuery("LeaveBalance.findAvailByEmpIDandLeaveType", LeaveBalanceFacade.class);
        query.setParameter("empID", employeeId);
        query.setParameter("leaveTypeID", leaveTypeId);
        List<LeaveBalance> leaveBalList = query.getResultList();
        System.out.println("List Size = "+leaveBalList.size());
        LinkedHashMap<Integer, Integer> yearsBalance = new LinkedHashMap(leaveBalList.size());
        
        for(LeaveBalance leaveBal: leaveBalList) {
            yearsBalance.put(leaveBal.getLeaveBalancePK().getYear(), leaveBal.getDaysBal());
        }
        
        return yearsBalance;
    }
    
    public LinkedHashMap<Integer, Integer> getTicketsBalance(Integer employeeId, Short leaveTypeId) {
        TypedQuery query = em.createNamedQuery("LeaveBalance.findAvailByEmpIDandLeaveType", LeaveBalanceFacade.class);
        query.setParameter("empID", employeeId);
        query.setParameter("leaveTypeID", leaveTypeId);
        List<LeaveBalance> leaveBalList = query.getResultList();
        
        LinkedHashMap<Integer, Integer> yearsBalance = new LinkedHashMap(leaveBalList.size());
        
        for(LeaveBalance leaveBal: leaveBalList) {
            yearsBalance.put(leaveBal.getLeaveBalancePK().getYear(), leaveBal.getTicketsBal());
        }
        
        return yearsBalance;
    }
    
    public LinkedHashMap<Integer, int[]> getDaysAndTicketsBalance (Integer employeeId, Short leaveTypeId) {
        TypedQuery query = em.createNamedQuery("LeaveBalance.findAvailByEmpIDandLeaveType", LeaveBalanceFacade.class);
        query.setParameter("empID", employeeId);
        query.setParameter("leaveTypeID", leaveTypeId);
        List<LeaveBalance> leaveBalList = query.getResultList();
        
        LinkedHashMap<Integer, int[]> resBalance = new LinkedHashMap(leaveBalList.size());
        
        for(LeaveBalance leaveBal: leaveBalList) {
            int[] yearAndTicket = new int[2];
            yearAndTicket[0] = leaveBal.getDaysBal(); //index 0 contains the days
            yearAndTicket[1] = leaveBal.getTicketsBal().intValue(); //index 1 contains the tickets
            resBalance.put(leaveBal.getLeaveBalancePK().getYear(), yearAndTicket);
        }
        
        return resBalance;
    }
    
    public void updateBalance(int employeeId, int leaveYear, short leaveType, int newDaysBal, int newTicketsBal) {
        LeaveBalance lb = this.find(new LeaveBalancePK(employeeId, leaveYear, leaveType));
        lb.setDaysBal(newDaysBal);
        lb.setTicketsBal(newTicketsBal);
        em.merge(lb);
    }
}
