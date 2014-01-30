/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.PersonalID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Riad
 */
@Stateless
public class PersonalIDFacade extends AbstractFacade<PersonalID> {
    @PersistenceContext(unitName = "HRPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonalIDFacade() {
        super(PersonalID.class);
    }
    
    public LinkedHashMap<String, Short> getPersonalIdHTable() {
        List<PersonalID> personIdList = this.findAll();
        LinkedHashMap<String, Short> personalIdHTable = new LinkedHashMap(personIdList.size());
        for (PersonalID persId: personIdList) {
            personalIdHTable.put(persId.getPersonalIDDescription(), persId.getPersonalIDType());
        }
        return personalIdHTable;
    }
}
