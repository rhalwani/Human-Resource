/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.LeaveType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Hashtable;

/**
 *
 * @author Riad
 */
@Stateless
public class LeaveTypeFacade extends AbstractFacade<LeaveType> {
    @PersistenceContext(unitName = "HRPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LeaveTypeFacade() {
        super(LeaveType.class);
    }
    
    public Hashtable<String, Short> getLeaveTypeHTable() {
        
        List<LeaveType> leaveTypesList = this.findAll();
        Hashtable<String, Short> leaveTypeHashtable = new Hashtable(leaveTypesList.size());
        for(LeaveType leaveType: leaveTypesList) {
            leaveTypeHashtable.put(leaveType.getLeaveDescription(), leaveType.getLeaveTypeID());
        }
        return leaveTypeHashtable;
    }
    
}
