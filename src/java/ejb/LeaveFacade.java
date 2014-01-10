/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Leave;
import entity.LeavePK;
import entity.LeaveType;
import entity.LeaveBalance;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Riad
 */
@Stateless
public class LeaveFacade extends AbstractFacade<Leave> {
    @PersistenceContext(unitName = "HRPU")
    private EntityManager em;

    @EJB
    private LeaveBalanceFacade balEjb;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LeaveFacade() {
        super(Leave.class);
    }
    
    public void addLeave(Integer employeeId, Short leaveTypeId, Date startDate, Date endDate) {
        Leave leave = new Leave();
        leave.setLeavePK(new LeavePK(employeeId, startDate, endDate));
        leave.setLeaveTypeID(em.find(LeaveType.class, leaveTypeId));
        Calendar calendarStart = new java.util.GregorianCalendar();
        Calendar calendarEnd = new java.util.GregorianCalendar();
        calendarStart.setTime(startDate);
        calendarEnd.setTime(endDate);
        
        int leaveDaysCount = (int)(calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis())/(24*60*60*1000);
        int remaining = leaveDaysCount;
        int leaveYear = calendarStart.get(Calendar.YEAR);
        HashMap<Integer, Integer> daysBal = balEjb.getDaysBalance(employeeId, leaveTypeId);
        
        Iterator<Integer> mapKeyIter = daysBal.keySet().iterator();
        while (mapKeyIter.hasNext()) { //Maximum entries is three: previous year, current year, and next year
            int year = mapKeyIter.next();
            int startBal = daysBal.get(year), endBal;
            
            if (year < leaveYear) {//Previous year
                if (startBal >=  leaveDaysCount) {
                    endBal = startBal - leaveDaysCount;
                    remaining = 0;
                } else {
                    remaining = leaveDaysCount - startBal;
                    endBal = 0;
                }
            } else if (year == leaveYear) { //Current Year
                if (startBal >=  remaining) {
                    endBal = startBal - remaining;
                    remaining = 0;
                } else {
                    remaining = remaining - startBal;
                    endBal = 0;
                }
            } else { //Following Year
                
            }
        }
    }
}
