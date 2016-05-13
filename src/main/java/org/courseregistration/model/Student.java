package org.courseregistration.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student_details")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    @Temporal(value = TemporalType.DATE)
    private Date dateOfBirth;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "FK_student_address"))
    private Address address;

    @Column(name = "admissionType")
    private String admissionType;

    @Column(name = "previous_degree")
    private String previousDegree;


    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "enrolled_student", joinColumns = {@JoinColumn(name = "student_id")}, inverseJoinColumns = {@JoinColumn(name = "section_id")}, foreignKey = @ForeignKey(name = "FK_student_section"), uniqueConstraints = {@UniqueConstraint(columnNames = {
        "student_id", "section_id"})})
    private List<Section> sections = Lists.newArrayList();

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getPreviousDegree() {
        return previousDegree;
    }

    public void setPreviousDegree(String previousDegree) {
        this.previousDegree = previousDegree;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        if(!this.sections.contains(section)){
            this.sections.add(section);
        }
    }

    public void dropSection(Section section) {
        if (this.sections.contains(section)) {
            this.sections.remove(section);
        }
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub

        String userDetails = super.toString();
        StringBuilder builder = new StringBuilder();
        builder.append("\n\tStudent:__________________________________");
        builder.append(userDetails);
        builder.append("\n\tName :\t" + firstName + " " + middleName + " "
            + lastName);
        builder.append("\n\t[ Email: " + emailId);
        builder.append(", Phone: " + phoneNumber);
        builder.append(", Date of birth :" + dateOfBirth + "] ");
        builder.append(address.toString());
        builder.append("\n\t[ Admission Type: " + admissionType);
        builder.append(", Previous Degree: " + previousDegree + "]");

        return builder.toString();
    }
}
