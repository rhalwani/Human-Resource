/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.LeaveBalance;
import entity.LeaveBalancePK;
import java.util.HashMap;
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
    
    public HashMap<Integer, Integer> getDaysBalance(Integer employeeId, Short leaveTypeId) {
        TypedQuery query = em.createNamedQuery("LeaveBalance.findAvailByEmpIDandLeaveType", LeaveBalanceFacade.class);
        query.setParameter("empID", employeeId);
        query.setParameter("leaveTypeID", leaveTypeId);
        List<LeaveBalance> leaveBalList = query.getResultList();
        
        HashMap<Integer, Integer> yearsBalance = new HashMap(leaveBalList.size());
        
        for(LeaveBalance leaveBal: leaveBalList) {
            yearsBalance.put(leaveBal.getLeaveBalancePK().getYear(), leaveBal.getDaysBal());
        }
        
        return yearsBalance;
    }
    
    public HashMap<Integer, Integer> getTicketsBalance(Integer employeeId, Short leaveTypeId) {
        TypedQuery query = em.createNamedQuery("LeaveBalance.findAvailByEmpIDandLeaveType", LeaveBalanceFacade.class);
        query.setParameter("empID", employeeId);
        query.setParameter("leaveTypeID", leaveTypeId);
        List<LeaveBalance> leaveBalList = query.getResultList();
        
        HashMap<Integer, Integer> yearsBalance = new HashMap(leaveBalList.size());
        
        for(LeaveBalance leaveBal: leaveBalList) {
            yearsBalance.put(leaveBal.getLeaveBalancePK().getYear(), leaveBal.getTicketsBal());
        }
        
        return yearsBalance;
    }
    /*
    public void updateBalance(Integer employeeId, Integer leaveYear, Short leaveType) {
        LeaveBalance lb = this.find(new LeaveBalancePK())
    }*/
}
