/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Leave;
import entity.LeaveType;
import entity.Employee;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    
    @EJB
    private LeaveDetailsFacade leaveDetEjb;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LeaveFacade() {
        super(Leave.class);
    }
        
    public void addLeave(Integer employeeId, Short leaveTypeId, Date startDate, Date endDate, int ticketCount, Integer approvedBy) {
        Leave leave = new Leave();
        //leave.setLeavePK(new LeavePK(employeeId, startDate, endDate));
        leave.setLeaveTypeID(em.find(LeaveType.class, leaveTypeId));
        leave.setEmpID(em.find(Employee.class, employeeId));
        leave.setStartDate(startDate);
        leave.setEndDate(endDate);
        leave.setApprovedBy(approvedBy == 0?null:em.find(Employee.class, approvedBy));
        
        em.persist(leave);
        
        
        Calendar calendarStart = new java.util.GregorianCalendar();
        Calendar calendarEnd = new java.util.GregorianCalendar();
        calendarStart.setTime(startDate);
        calendarEnd.setTime(endDate);
        
        int leaveDaysCount = (int)(calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis())/(24*60*60*1000);
        //int remaining = leaveDaysCount;
        //int leaveYear = calendarStart.get(Calendar.YEAR);
        LinkedHashMap<Integer, int[]> daysAndTicketsBal = balEjb.getDaysAndTicketsBalance(employeeId, leaveTypeId);
        
        Iterator<Integer> balMapKeyIter = daysAndTicketsBal.keySet().iterator();
        
        while (balMapKeyIter.hasNext() && leaveDaysCount > 0) { //Maximum entries is three: previous year, current year, and next year
            int year = balMapKeyIter.next();
            System.out.println("Year = "+year);
            int[] startBal = daysAndTicketsBal.get(year);
            int daysStartBal = startBal[0], daysEndBal = startBal[0];
            int ticketsStartBal = startBal[1], ticketsEndBal = startBal[1];

            if (daysStartBal >=  leaveDaysCount) {
                daysEndBal = daysStartBal - leaveDaysCount;
                leaveDaysCount = 0;
            } else {
                leaveDaysCount -= daysStartBal;
                daysEndBal = 0;
            }
            
            if (ticketCount > 0) {
                if(ticketsStartBal >= ticketCount) {
                    ticketsEndBal = ticketsStartBal - ticketCount;
                    ticketCount = 0;
                } else {
                    ticketCount -= ticketsStartBal;
                    ticketsEndBal = 0;
                }   
            }
            
            leaveDetEjb.addLeaveDetail(leave.getLeaveID(), year, daysStartBal, daysEndBal, ticketsStartBal, ticketsEndBal);
            balEjb.updateBalance(employeeId, year, leaveTypeId, daysEndBal, ticketsEndBal);
        }
    }
}
