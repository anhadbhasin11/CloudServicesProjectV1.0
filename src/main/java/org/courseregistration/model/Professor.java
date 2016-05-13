package org.courseregistration.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "professor_details")
@PrimaryKeyJoinColumn(name = "user_id")
public class Professor extends User {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name="bio", columnDefinition = "Text", length = 65535, nullable = false)
    private String bio;


    @Column(name = "date_of_birth")
    @Temporal(value = TemporalType.DATE)
    private Date dateOfBirth;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "FK_professor_address"))
    private Address address;
    @Column(name = "faculty_type")
    private String facultyType;

    @Column(name = "years_of_experience", nullable = false)
    private Integer yearsOfExperience;

    @Column(name = "office_hours_from_time")
    @Temporal(value = TemporalType.TIME)
    private Date officeHoursFromTime;

    @Column(name = "office_hours_to_time")
    @Temporal(value = TemporalType.TIME)
    private Date officeHoursToTime;

    public String getFacultyType() {
        return facultyType;
    }

    public void setFacultyType(String facultyType) {
        this.facultyType = facultyType;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Date getOfficeHoursFromTime() {
        return officeHoursFromTime;
    }

    public void setOfficeHoursFromTime(Date officeHoursFromTime) {
        this.officeHoursFromTime = officeHoursFromTime;
    }

    public Date getOfficeHoursToTime() {
        return officeHoursToTime;
    }

    public void setOfficeHoursToTime(Date officeHoursToTime) {
        this.officeHoursToTime = officeHoursToTime;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor)) return false;
        if (!super.equals(o)) return false;

        Professor professor = (Professor) o;

        if (!firstName.equals(professor.firstName)) return false;
        if (!middleName.equals(professor.middleName)) return false;
        if (!lastName.equals(professor.lastName)) return false;
        if (!emailId.equals(professor.emailId)) return false;
        if (!phoneNumber.equals(professor.phoneNumber)) return false;
        if (!dateOfBirth.equals(professor.dateOfBirth)) return false;
        if (!facultyType.equals(professor.facultyType)) return false;
        if (!yearsOfExperience.equals(professor.yearsOfExperience)) return false;
        if (!officeHoursFromTime.equals(professor.officeHoursFromTime)) return false;
        return officeHoursToTime.equals(professor.officeHoursToTime);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + emailId.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + facultyType.hashCode();
        result = 31 * result + yearsOfExperience.hashCode();
        result = 31 * result + officeHoursFromTime.hashCode();
        result = 31 * result + officeHoursToTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub

        String userDetails = super.toString();
        StringBuilder builder = new StringBuilder();
        builder.append("\n\tProfessor:__________________________________");
        builder.append(userDetails);
        builder.append("\n\tName :\t" + firstName + " " + middleName + " "
            + lastName);
        builder.append("\n\t[ Email: " + emailId);
        builder.append(", Phone: " + phoneNumber);
        builder.append(", Date of birth :" + dateOfBirth + "] ");
        builder.append(address.toString());
        builder.append("\n\t[ Faculty type: " + facultyType);
        builder.append(", Years of Experience: " + yearsOfExperience);
        builder.append(", Office hours: from " + officeHoursFromTime + " to "
            + officeHoursToTime + "] ");

        return builder.toString();
    }

}
