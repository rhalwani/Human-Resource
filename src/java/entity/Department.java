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
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Department")
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findByDepID", query = "SELECT d FROM Department d WHERE d.depID = :depID"),
    @NamedQuery(name = "Department.findByDepartmentName", query = "SELECT d FROM Department d WHERE d.departmentName = :departmentName"),
    @NamedQuery(name = "Department.findRootDeps", query = "SELECT d FROM Department d WHERE d.rootDepID is null"),
    @NamedQuery(name = "Department.findChildrenDeps", query = "SELECT d FROM Department d WHERE d.rootDepID is not null and d.parentDepID is not null"),
    @NamedQuery(name = "Department.findAllChildDeps", query = "SELECT d FROM Department d WHERE d.rootDepID = :rootDepID"),
    @NamedQuery(name = "Department.findChildrenDepsById", query = "SELECT d FROM Department d WHERE d.rootDepID = :rootDepID and d.parentDepID = :parentDepID"),
    @NamedQuery(name = "Department.findAncestorDeps", query = "SELECT d FROM Department d WHERE d.parentDepID is null"),
    @NamedQuery(name = "Department.findAncestorDepsById", query = "SELECT d FROM Department d WHERE d.parentDepID is null and d.rootDepID = :rootDepID")
})
public class Department implements Serializable {
    @JoinColumn(name = "Mgr_ID", referencedColumnName = "Emp_ID")
    @ManyToOne
    private Employee mgrID;
    @OneToMany(mappedBy = "depID")
    private Collection<Employee> employeeCollection;
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "Dep_ID")
    private Short depID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Department_Name")
    private String departmentName;
    @OneToMany(mappedBy = "parentDepID")
    private Collection<Department> departmentCollection;
    @JoinColumn(name = "Parent_Dep_ID", referencedColumnName = "Dep_ID")
    @ManyToOne
    private Department parentDepID;
    @OneToMany(mappedBy = "rootDepID")
    private Collection<Department> departmentCollection1;
    @JoinColumn(name = "Root_Dep_ID", referencedColumnName = "Dep_ID")
    @ManyToOne
    private Department rootDepID;

    public Department() {
    }

    public Department(Short depID) {
        this.depID = depID;
    }

    public Department(Short depID, String departmentName) {
        this.depID = depID;
        this.departmentName = departmentName;
    }

    public Short getDepID() {
        return depID;
    }

    public void setDepID(Short depID) {
        this.depID = depID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Collection<Department> getDepartmentCollection() {
        return departmentCollection;
    }

    public void setDepartmentCollection(Collection<Department> departmentCollection) {
        this.departmentCollection = departmentCollection;
    }

    public Department getParentDepID() {
        return parentDepID;
    }

    public void setParentDepID(Department parentDepID) {
        this.parentDepID = parentDepID;
    }

    public Collection<Department> getDepartmentCollection1() {
        return departmentCollection1;
    }

    public void setDepartmentCollection1(Collection<Department> departmentCollection1) {
        this.departmentCollection1 = departmentCollection1;
    }

    public Department getRootDepID() {
        return rootDepID;
    }

    public void setRootDepID(Department rootDepID) {
        this.rootDepID = rootDepID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depID != null ? depID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.depID == null && other.depID != null) || (this.depID != null && !this.depID.equals(other.depID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Department[ depID=" + depID + " ]";
    }

    public Employee getMgrID() {
        return mgrID;
    }

    public void setMgrID(Employee mgrID) {
        this.mgrID = mgrID;
    }

    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }
    
}
