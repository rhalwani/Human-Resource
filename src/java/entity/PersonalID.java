/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Riad
 */
@Entity
@Table(name = "Personal_ID")
@NamedQueries({
    @NamedQuery(name = "PersonalID.findAll", query = "SELECT p FROM PersonalID p"),
    @NamedQuery(name = "PersonalID.findByPersonalIDType", query = "SELECT p FROM PersonalID p WHERE p.personalIDType = :personalIDType"),
    @NamedQuery(name = "PersonalID.findByPersonalIDDescription", query = "SELECT p FROM PersonalID p WHERE p.personalIDDescription = :personalIDDescription")})
public class PersonalID implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "Personal_ID_Type")
    private Short personalIDType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Personal_ID_Description")
    private String personalIDDescription;
    @OneToMany(mappedBy = "personalIDType")
    private Collection<Employee> employeeCollection;

    public PersonalID() {
    }

    public PersonalID(Short personalIDType) {
        this.personalIDType = personalIDType;
    }

    public PersonalID(Short personalIDType, String personalIDDescription) {
        this.personalIDType = personalIDType;
        this.personalIDDescription = personalIDDescription;
    }

    public Short getPersonalIDType() {
        return personalIDType;
    }

    public void setPersonalIDType(Short personalIDType) {
        this.personalIDType = personalIDType;
    }

    public String getPersonalIDDescription() {
        return personalIDDescription;
    }

    public void setPersonalIDDescription(String personalIDDescription) {
        this.personalIDDescription = personalIDDescription;
    }

    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personalIDType != null ? personalIDType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalID)) {
            return false;
        }
        PersonalID other = (PersonalID) object;
        if ((this.personalIDType == null && other.personalIDType != null) || (this.personalIDType != null && !this.personalIDType.equals(other.personalIDType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PersonalID[ personalIDType=" + personalIDType + " ]";
    }
    
}
