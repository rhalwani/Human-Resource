/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Job;
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
public class JobFacade extends AbstractFacade<Job> {
    @PersistenceContext(unitName = "HRPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobFacade() {
        super(Job.class);
    }
    
    public Hashtable<String, Short> getJobTitleIdHTable() {
        
        List<Job> jobList = this.findAll();
        Hashtable jobTitleIdHTable = new Hashtable(jobList.size());
        for (Job job: jobList) {
            jobTitleIdHTable.put(job.getJobTitle(), job.getJobID());
        }
        return jobTitleIdHTable;
    }
    
}
