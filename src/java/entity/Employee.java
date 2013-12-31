/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Riad
 */
@Entity
@Table(name = "Employee")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmpID", query = "SELECT e FROM Employee e WHERE e.empID = :empID"),
    @NamedQuery(name = "Employee.findByFirstName", query = "SELECT e FROM Employee e WHERE e.firstName = :firstName"),
    @NamedQuery(name = "Employee.findByMiddleName", query = "SELECT e FROM Employee e WHERE e.middleName = :middleName"),
    @NamedQuery(name = "Employee.findByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName"),
    @NamedQuery(name = "Employee.findByPersonalIDNum", query = "SELECT e FROM Employee e WHERE e.personalIDNum = :personalIDNum"),
    @NamedQuery(name = "Employee.findByMobile1", query = "SELECT e FROM Employee e WHERE e.mobile1 = :mobile1"),
    @NamedQuery(name = "Employee.findByMobile2", query = "SELECT e FROM Employee e WHERE e.mobile2 = :mobile2"),
    @NamedQuery(name = "Employee.findByLandline", query = "SELECT e FROM Employee e WHERE e.landline = :landline"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")})
public class Employee implements Serializable {
    @JoinColumn(name = "Personal_ID_Type", referencedColumnName = "Personal_ID_Type")
    @ManyToOne
    private PersonalID personalIDType;
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "Emp_ID")
    private Integer empID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "First_Name")
    private String firstName;
    @Size(max = 50)
    @Column(name = "Middle_Name")
    private String middleName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Last_Name")
    private String lastName;
    @Size(max = 30)
    @Column(name = "Personal_ID_Num")
    private String personalIDNum;
    @Size(max = 20)
    @Column(name = "Mobile_1")
    private String mobile1;
    @Size(max = 20)
    @Column(name = "Mobile_2")
    private String mobile2;
    @Size(max = 20)
    @Column(name = "Landline")
    private String landline;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Email")
    private String email;
    @OneToMany(mappedBy = "approvedBy")
    private Collection<Leave> leaveCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Collection<Leave> leaveCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Collection<LeaveBalance> leaveBalanceCollection;
    @OneToMany(mappedBy = "mgrID")
    private Collection<Department> departmentCollection;
    @JoinColumn(name = "Job_ID", referencedColumnName = "Job_ID")
    @ManyToOne
    private Job jobID;
    @JoinColumn(name = "Dep_ID", referencedColumnName = "Dep_ID")
    @ManyToOne
    private Department depID;

    public Employee() {
    }

    public Employee(Integer empID) {
        this.empID = empID;
    }

    public Employee(Integer empID, String firstName, String lastName) {
        this.empID = empID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalIDNum() {
        return personalIDNum;
    }

    public void setPersonalIDNum(String personalIDNum) {
        this.personalIDNum = personalIDNum;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Leave> getLeaveCollection() {
        return leaveCollection;
    }

    public void setLeaveCollection(Collection<Leave> leaveCollection) {
        this.leaveCollection = leaveCollection;
    }

    public Collection<Leave> getLeaveCollection1() {
        return leaveCollection1;
    }

    public void setLeaveCollection1(Collection<Leave> leaveCollection1) {
        this.leaveCollection1 = leaveCollection1;
    }

    public Collection<LeaveBalance> getLeaveBalanceCollection() {
        return leaveBalanceCollection;
    }

    public void setLeaveBalanceCollection(Collection<LeaveBalance> leaveBalanceCollection) {
        this.leaveBalanceCollection = leaveBalanceCollection;
    }

    public Collection<Department> getDepartmentCollection() {
        return departmentCollection;
    }

    public void setDepartmentCollection(Collection<Department> departmentCollection) {
        this.departmentCollection = departmentCollection;
    }

    public Job getJobID() {
        return jobID;
    }

    public void setJobID(Job jobID) {
        this.jobID = jobID;
    }

    public Department getDepID() {
        return depID;
    }

    public void setDepID(Department depID) {
        this.depID = depID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empID != null ? empID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.empID == null && other.empID != null) || (this.empID != null && !this.empID.equals(other.empID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Employee[ empID=" + empID + " ]";
    }

    public PersonalID getPersonalIDType() {
        return personalIDType;
    }

    public void setPersonalIDType(PersonalID personalIDType) {
        this.personalIDType = personalIDType;
    }
    
}
