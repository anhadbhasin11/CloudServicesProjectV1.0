package org.courseregistration.dbtests;

import com.google.common.collect.Sets;
import org.courseregistration.model.*;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Date;


public class DataGenerator {

    public static void main(String args[]){
        generateData();
    }

    public static void generateData(){
        EntityManager entityManager = org.courseregistration.dbtests.HibernateUtils.getEntityManager();

        //--------------------------------------------------------------------------
        Role student_role= new Role();
        student_role.setName("Student");
        student_role.setUpdatedAt(new Date());

        Role professor_role=new Role();
        professor_role.setName("Professor");
        professor_role.setUpdatedAt(new Date());

        Role admin_role = new Role();
        admin_role.setName("Administrator");
        admin_role.setUpdatedAt(new Date());
        //--------------------------------------------------------------------------

        Student student1 = new Student();
        student1.setRoles(Sets.newHashSet(student_role));
        student1.setUsername("123456789");
        student1.setFirstName("John");
        student1.setMiddleName("R.");
        student1.setLastName("Edward");

        Address student1Address = new Address();
        student1Address.setStreetName("201 W California Ave");
        student1Address.setAptNo(86);
        student1Address.setCity("Sunnyvale");
        student1Address.setState("CA");
        student1Address.setZipcode(94086);
        student1Address.setUpdatedAt(new Date());

        student1.setAddress(student1Address);
        student1.setAdmissionType("Accepted");
        student1.setUpdatedAt(new Date());

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(1988, Calendar.FEBRUARY, 5);
        student1.setDateOfBirth(calendar1.getTime());

        student1.setEmailId("John@gmail.com");
        student1.setPhoneNumber("+1-408-386-7260");
        student1.setPreviousDegree("Bsc Comp Science");

        Student student2 = new Student();
        student2.setRoles(Sets.newHashSet(student_role));
        student2.setFirstName("Alice");
        student2.setMiddleName("W.");
        student2.setUsername("123456788");
        student2.setLastName("Campbell");
        student2.setUpdatedAt(new Date());

        Address student2Address = new Address();
        student2Address.setStreetName("65 Rio Robles E");
        student2Address.setAptNo(1028);
        student2Address.setCity("San Jose");
        student2Address.setState("CA");
        student2Address.setZipcode(95134);
        student2.setAddress(student2Address);
        student2Address.setUpdatedAt(new Date());
        student2.setAdmissionType("Accepted - Conditionally");

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1986, Calendar.AUGUST, 7);
        student2.setDateOfBirth(calendar2.getTime());

        student2.setEmailId("alice@gmail.com");
        student2.setPhoneNumber("+1-408-376-7860");
        student2.setPreviousDegree("BE Comp Engineering");

        Student student3 = new Student();
        student3.setRoles(Sets.newHashSet(student_role));
        student3.setFirstName("Sarah");
        student3.setMiddleName("V.");
        student3.setLastName("Rossie");
        student3.setUsername("89078345345");

        Address student3Address = new Address();
        student3Address.setStreetName("209 E Indio Ave");
        student3Address.setAptNo(456);
        student3Address.setCity("Fremont");
        student3Address.setState("CA");
        student3Address.setZipcode(94536);
        student3Address.setUpdatedAt(new Date());

        student3.setAddress(student3Address);
        student3.setAdmissionType("Accepted");

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(1987, Calendar.JULY, 28);
        student3.setDateOfBirth(calendar3.getTime());

        student3.setEmailId("sarah@gmail.com");
        student3.setPhoneNumber("+1-508-664-7260");
        student3.setPreviousDegree("Bachelor of commerce");
        student3.setUpdatedAt(new Date());

        Student student4 = new Student();
        student4.setRoles(Sets.newHashSet(student_role));
        student4.setFirstName("Alan");
        student4.setMiddleName("V.");
        student4.setLastName("Simon");
        student4.setUsername("9459345L");
        student4.setUpdatedAt(new Date());

        Address student4Address = new Address();
        student4Address.setStreetName("4356 Waterford Ave");
        student4Address.setAptNo(15);
        student4Address.setCity("San Diego");
        student4Address.setState("CA");
        student4Address.setZipcode(45632);
        student4Address.setUpdatedAt(new Date());

        student4.setAddress(student4Address);
        student4.setAdmissionType("Rejected");

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(1983, Calendar.SEPTEMBER, 5);
        student4.setDateOfBirth(calendar4.getTime());

        student4.setEmailId("V.Alan@gmail.com");
        student4.setPhoneNumber("+1-407-654-8760");
        student4.setPreviousDegree("Bachelor of Arts");


        Student student5 = new Student();
        student5.setRoles(Sets.newHashSet(student_role));
        student5.setFirstName("Olivia");
        student5.setMiddleName("s.");
        student5.setLastName("Alexander");
        student5.setUsername("64574L");

        Address student5Address = new Address();
        student5Address.setStreetName("101 San Fernando");
        student5Address.setAptNo(653);
        student5Address.setCity("San Jose");
        student5Address.setState("CA");
        student5Address.setZipcode(95112);
        student5Address.setUpdatedAt(new Date());

        student5.setAddress(student5Address);
        student5.setAdmissionType("Waiting");

        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(1990, Calendar.DECEMBER, 23);
        student5.setDateOfBirth(calendar5.getTime());

        student5.setEmailId("Alexander.olivia1986@gmail.com");
        student5.setPhoneNumber("+1-567-654-9812");
        student5.setPreviousDegree("BS in Computer Science");
        student5.setUpdatedAt(new Date());

        Student student6 = new Student();

        student6.setRoles(Sets.newHashSet(student_role));
        student6.setFirstName("Edward");
        student6.setMiddleName("Cinacma");
        student6.setLastName("Simpson");
        student6.setUsername("4534L");
        student6.setUpdatedAt(new Date());

        Address student6Address = new Address();
        student6Address.setStreetName("0442 Melrose Street");
        student6Address.setAptNo(71);
        student6Address.setCity("Richmond");
        student6Address.setState("CA");
        student6Address.setZipcode(23225);
        student6Address.setUpdatedAt(new Date());

        student6.setAddress(student6Address);
        student6.setAdmissionType("Classified-Graduate");

        Calendar calendar6 = Calendar.getInstance();
        calendar6.set(1993, Calendar.AUGUST, 10);
        student6.setDateOfBirth(calendar6.getTime());

        student6.setEmailId("esimpson8@youku.com");
        student6.setPhoneNumber("+1-187-063-6666");
        student6.setPreviousDegree("BSc, Computer Engineering");

        Student student7 = new Student();

        student7.setRoles(Sets.newHashSet(student_role));
        student7.setFirstName("Fred");
        student7.setMiddleName("H.");
        student7.setLastName("Ross");
        student7.setUsername("5457L");

        Address student7Address = new Address();
        student7Address.setStreetName("86 Sauthoff Junction");
        student7Address.setAptNo(724);
        student7Address.setCity("Los Angels");
        student7Address.setState("CA");
        student7Address.setZipcode(90055);

        student7.setAddress(student7Address);
        student7.setAdmissionType("Classified-Graduate");

        Calendar calendar7 = Calendar.getInstance();
        calendar7.set(1991,Calendar.JUNE,12);
        student7.setDateOfBirth(calendar7.getTime());

        student7.setEmailId("fross2@imgur.com");
        student7.setPhoneNumber("+1-830-195-5010");
        student7.setPreviousDegree("BSc, Mechanical Engineering");
        //==========================================

        Professor professor1 = new Professor();

        professor1.setRoles(Sets.newHashSet(professor_role));
        professor1.setFirstName("Alice");
        professor1.setMiddleName("W.");
        professor1.setUsername("123456779");
        professor1.setLastName("Campbell");
        professor1.setBio("Professor at SJSU University with teaching experience of 10 years and Industry experience of 10 years");

        Address professor1Address = new Address();
        professor1Address.setStreetName("65 Rio Robles E");
        professor1Address.setAptNo(90);
        professor1Address.setCity("San Jose");
        professor1Address.setState("CA");
        professor1Address.setZipcode(95134);

        professor1.setAddress(professor1Address);
        professor1.setFacultyType("Permanent");
        professor1.setYearsOfExperience(12);

        Calendar prof_calendar1 = Calendar.getInstance();
        prof_calendar1.set(1970, Calendar.AUGUST, 7);
        professor1.setDateOfBirth(prof_calendar1.getTime());

        professor1.setEmailId("alice@gmail.com");
        professor1.setPhoneNumber("+1-408-376-7860");

        Calendar prof1_calendar1 = Calendar.getInstance();
        prof1_calendar1.set(0, 0, 0, 10, 0,0);
        professor1.setOfficeHoursFromTime(prof1_calendar1.getTime());

        Calendar prof1_calendar2 = Calendar.getInstance();
        prof1_calendar2.set(0, 0, 0, 12, 30,0);
        professor1.setOfficeHoursToTime(prof1_calendar2.getTime());

        Professor professor2 = new Professor();

        professor2.setRoles(Sets.newHashSet(professor_role));
        professor2.setFirstName("Mike");
        professor2.setUsername("1234L");
        professor2.setMiddleName("W.");
        professor2.setLastName("Larkin");
        professor2.setBio("Professor at SJSU University with teaching experience of 5 years and Industry experience of 5 years");

        Address professor2Address = new Address();
        professor2Address.setStreetName("7642 Scott Blvd");
        professor2Address.setAptNo(66);
        professor2Address.setCity("Santa Clara");
        professor2Address.setState("CA");
        professor2Address.setZipcode(95054);

        professor2.setAddress(professor2Address);
        professor2.setFacultyType("Permanent");
        professor2.setYearsOfExperience(17);

        Calendar prof_calendar2 = Calendar.getInstance();
        prof_calendar2.set(1962, Calendar.AUGUST, 7);
        professor2.setDateOfBirth(prof_calendar2.getTime());

        professor2.setEmailId("larkin_mike@gmail.com");
        professor2.setPhoneNumber("+1-503-634-07630");

        Calendar prof2_calendar1= Calendar.getInstance();
        prof2_calendar1.set(0, 0, 0, 8, 45,0);
        professor2.setOfficeHoursFromTime(prof2_calendar1.getTime());

        Calendar prof2_calendar2 = Calendar.getInstance();
        prof2_calendar2.set(0, 0, 0, 13, 30,0);
        professor2.setOfficeHoursToTime(prof2_calendar2.getTime());

        Professor professor3 = new Professor();

        professor3.setRoles(Sets.newHashSet(professor_role));
        professor3.setFirstName("Rakesh");
        professor3.setMiddleName("C.");
        professor3.setLastName("Ranjan");
        professor3.setUsername("453L");
        professor3.setBio("Professor at SDSU University with teaching experience of 10 years and Industry experience of 10 years");

        Address professor3Address = new Address();
        professor3Address.setStreetName("70 S Market St");
        professor3Address.setAptNo(167);
        professor3Address.setCity("San Francisco");
        professor3Address.setState("CA");
        professor3Address.setZipcode(78534);

        professor3.setAddress(professor3Address);
        professor3.setFacultyType("Permanent");
        professor3.setYearsOfExperience(25);

        Calendar prof_calendar3 = Calendar.getInstance();
        prof_calendar3.set(1955, Calendar.AUGUST, 7);
        professor3.setDateOfBirth(prof_calendar3.getTime());

        professor3.setEmailId("rakesh_ranjan@gmail.com");
        professor3.setPhoneNumber("+1-408-774-7860");

        Calendar prof3_calendar1 = Calendar.getInstance();
        prof3_calendar1.set(0, 0, 0, 10, 0,0);
        professor3.setOfficeHoursFromTime(prof3_calendar1.getTime());

        Calendar prof3_calendar2 = Calendar.getInstance();
        prof3_calendar2.set(0, 0, 0, 15, 0,0);
        professor3.setOfficeHoursToTime(prof3_calendar2.getTime());

        Professor professor4 = new Professor();

        professor4.setRoles(Sets.newHashSet(professor_role));
        professor4.setFirstName("Erica");
        professor4.setMiddleName("A.");
        professor4.setLastName("Bing");
        professor4.setUsername("676L");
        professor4.setBio("Professor at SFSU University with teaching experience of 6 years and Industry experience of 10 years");

        Address professor4Address = new Address();
        professor4Address.setStreetName("901 Stevenson Blvd");
        professor4Address.setAptNo(48);
        professor4Address.setCity("Fremont");
        professor4Address.setState("CA");
        professor4Address.setZipcode(94536);

        professor4.setAddress(professor4Address);
        professor4.setFacultyType("Visiting");
        professor4.setYearsOfExperience(5);

        Calendar prof_calendar4 = Calendar.getInstance();
        prof_calendar4.set(1983, Calendar.AUGUST, 4);
        professor4.setDateOfBirth(prof_calendar4.getTime());

        professor4.setEmailId("erica.bing@gmail.com");
        professor4.setPhoneNumber("+1-564-376-9160");

        Calendar prof4_calendar1 = Calendar.getInstance();
        prof4_calendar1.set(0, 0, 0, 14, 0,0);
        professor4.setOfficeHoursFromTime(prof4_calendar1.getTime());

        Calendar prof4_calendar2 = Calendar.getInstance();
        prof4_calendar2.set(0, 0, 0, 17, 0,0);
        professor4.setOfficeHoursToTime(prof4_calendar2.getTime());

        Professor professor5 = new Professor();

        professor5.setRoles(Sets.newHashSet(professor_role));
        professor5.setFirstName("Emily");
        professor5.setMiddleName("U.");
        professor5.setLastName("Davis");
        professor5.setUsername("444L");
        professor5.setBio("Professor at SFSU University with teaching experience of 6 years.");

        Address professor5Address = new Address();
        professor5Address.setStreetName("876 Lawrence St");
        professor5Address.setAptNo(10);
        professor5Address.setCity("Santa Clara");
        professor5Address.setState("CA");
        professor5Address.setZipcode(95134);

        professor5.setAddress(professor5Address);
        professor5.setFacultyType("Permanent");
        professor5.setYearsOfExperience(9);

        Calendar prof_calendar5 = Calendar.getInstance();
        prof_calendar5.set(1980, Calendar.MAY, 7);
        professor5.setDateOfBirth(prof_calendar5.getTime());

        professor5.setEmailId("Davis_emily@gmail.com");
        professor5.setPhoneNumber("+1-712-354-0062");

        Calendar prof5_calendar1 = Calendar.getInstance();
        prof5_calendar1.set(0, 0, 0, 11, 30,0);
        professor5.setOfficeHoursFromTime(prof5_calendar1.getTime());

        Calendar prof5_calendar2 = Calendar.getInstance();
        prof5_calendar2.set(0, 0,0,14,30,0);
        professor5.setOfficeHoursToTime(prof5_calendar2.getTime());

        Professor professor6= new Professor();

        professor6.setRoles(Sets.newHashSet(professor_role));
        professor6.setFirstName("Sandra");
        professor6.setMiddleName("AA");
        professor6.setLastName("Hill");
        professor6.setUsername("666L");
        professor6.setBio("Professor at SFSU University with teaching experience of 6 years.");

        Address professor6Address = new Address();
        professor6Address.setStreetName("48455 Pierstorff Avenue");
        professor6Address.setAptNo(18);
        professor6Address.setCity("Anchorage");
        professor6Address.setState("Alaska");
        professor6Address.setZipcode(99522);

        professor6.setAddress(professor6Address);
        professor6.setFacultyType("Full-Time");
        professor6.setYearsOfExperience(20);

        Calendar prof_calendar6 = Calendar.getInstance();
        prof_calendar6.set(1957,Calendar.JANUARY,13);
        professor6.setDateOfBirth(prof_calendar6.getTime());

        professor6.setEmailId("shill4@google.fr");
        professor6.setPhoneNumber("1-723-327-1856");

        Calendar prof6_calendar1= Calendar.getInstance();
        prof6_calendar1.set(0,0,0,11,15,0);
        professor6.setOfficeHoursFromTime(prof6_calendar1.getTime());

        Calendar prof6_calendar2= Calendar.getInstance();
        prof6_calendar2.set(0,0,0,15,30,0);
        professor6.setOfficeHoursToTime(prof6_calendar2.getTime());
        //===================================================================
        Course  course1=new Course();

        course1.setCode("CHEM-055");
        course1.setName("Quantitative Analysis");
        course1.setDescription("Introduction to theories and techniques of chemical analysis.");
        course1.setDepartment("Chemistry");
        course1.setNumOfCredits(9);
        course1.setPrerequisiteCourse("CHEM 001A-General Chemistry");
        course1.setProgram("BS");

        Course  course2=new Course();

        course2.setCode("CS-218");
        course2.setName(" Topics in Cloud Computing");
        course2.setDescription("Topics in cloud computing, including distributed system models," +
            " virtual machines, virtualization, cloud platform architectures (IaaS, PaaS, SaaS)," +
            " service-oriented architectures, cloud programming and software environments, peer-to-peer computing, " +
            "ubiquitous cloud, cloud security and trust ");
        course2.setDepartment("Computer Science");
        course2.setNumOfCredits(9);
        course2.setPrerequisiteCourse("CS-149");
        course2.setProgram("MS");

        Course  course3=new Course();

        course3.setCode("ENGR-202");
        course3.setName("Systems Engineering");
        course3.setDescription("Large scale system design and development." +
            " Integrated approach including mission statement, synthesis of design concepts, " +
            "tradeoff studies, risk assessment and interactions encountered in the optimal design, " +
            "development, manufacture and test of systems. ");
        course3.setDepartment("General Engineering");
        course3.setNumOfCredits(9);
        course3.setPrerequisiteCourse("Graduate standing or instructor consent");
        course3.setProgram("MS-Engineering");

        Course  course4=new Course();

        course4.setCode("CMPE-120");
        course4.setName("Computer Organization and Architecture");
        course4.setDescription("Introduction to computer organization and architecture, " +
            "system buses, internal memory and external memory, input/output, central " +
            "processing unit CPU, instruction sets, CPU structure and function, RISC, control unit");
        course4.setDepartment("Computer Engineering");
        course4.setNumOfCredits(9);
        course4.setPrerequisiteCourse("CMPE-050 or CS-046B");
        course4.setProgram("BS");

        Course  course5=new Course();

        course5.setCode("EE-262");
        course5.setName("Acquisition and Analysis of Biosignals");
        course5.setDescription("Study of various biomedical signals and their physiological origin. " +
            "Study of analog instrumentation design to extract such signals with extensive biomedical signal " +
            "analysis in the context of disease management, pathology and treatment with numerous case studies.");
        course5.setDepartment("Electrical Engineering");
        course5.setNumOfCredits(9);
        course5.setPrerequisiteCourse("EE 210 or equivalent, or instructor consent");
        course5.setProgram("MS Electrical Engineering");

        //===================================================================
        Section section1 = new Section();

        Calendar classStartDateTimeSection1 = Calendar.getInstance();
        classStartDateTimeSection1.set(2015, Calendar.AUGUST, 24,18,0,0);
        section1.setClassStartTime(classStartDateTimeSection1.getTime());

        Calendar classEndDateTimeSection1 = Calendar.getInstance();
        classEndDateTimeSection1.set(2015, Calendar.DECEMBER, 12, 20, 45,0);
        section1.setClassEndTime(classEndDateTimeSection1.getTime());

        section1.setDayOfWeek("Thursday");
        section1.setStartDate(classStartDateTimeSection1.getTime());
        section1.setEndDate(classEndDateTimeSection1.getTime());

        section1.setModeOfInstruction("In Class");
        section1.setRoomNumber("EM-501");
        section1.setSemester("Fall-2015");
        section1.setTotalCapacity(40);
        section1.setWaitListCapacity(20);
        section1.setCourse(course1);
        section1.setProfessor(professor1);
        section1.setPrice(200);

        Section section2 = new Section();

        Calendar classStartDateTime2 = Calendar.getInstance();
        classStartDateTime2.set(2016, Calendar.JANUARY, 26, 18, 30,0);
        section2.setClassStartTime(classStartDateTime2.getTime());

        Calendar classEndDateTimeSection2 = Calendar.getInstance();
        classEndDateTimeSection2.set(2016, Calendar.MAY, 5, 20, 30,0);
        section2.setClassEndTime(classEndDateTimeSection2.getTime());

        section2.setDayOfWeek("Friday");
        section2.setStartDate(classStartDateTime2.getTime());
        section2.setEndDate(classEndDateTimeSection2.getTime());

        section2.setModeOfInstruction("In Class");
        section2.setRoomNumber("COE-850");
        section2.setSemester("Spring-2016");
        section2.setTotalCapacity(35);
        section2.setWaitListCapacity(10);
        section2.setProfessor(professor2);
        section2.setCourse(course2);
        section2.setPrice(250);

        Section section3 = new Section();

        Calendar classStartDateTimeSection3 = Calendar.getInstance();
        classStartDateTimeSection3.set(2016, Calendar.AUGUST, 21,18,0,0);
        section3.setClassStartTime(classStartDateTimeSection1.getTime());

        Calendar classEndDateTimeSection3 = Calendar.getInstance();
        classEndDateTimeSection3.set(2016, Calendar.DECEMBER, 8, 20, 45,0);
        section3.setClassEndTime(classEndDateTimeSection3.getTime());

        section3.setDayOfWeek("Monday");
        section3.setStartDate(classStartDateTimeSection3.getTime());
        section3.setEndDate(classEndDateTimeSection3.getTime());

        section3.setModeOfInstruction("In Class");
        section3.setRoomNumber("T-506");
        section3.setSemester("Fall-2016");
        section3.setTotalCapacity(45);
        section3.setWaitListCapacity(20);
        section3.setCourse(course2);
        section3.setProfessor(professor3);
        section3.setPrice(185);

        Section section4 = new Section();

        Calendar classStartDateTimeSection4 = Calendar.getInstance();
        classStartDateTimeSection4.set(2016, Calendar.JUNE, 4,9,0,0);
        section4.setClassStartTime(classStartDateTimeSection4.getTime());

        Calendar classEndDateTimeSection4 = Calendar.getInstance();
        classEndDateTimeSection4.set(2016, Calendar.AUGUST, 20, 20,45,0);
        section4.setClassEndTime(classEndDateTimeSection4.getTime());

        section4.setDayOfWeek("Saturday");
        section4.setStartDate(classStartDateTimeSection1.getTime());
        section4.setEndDate(classEndDateTimeSection1.getTime());

        section4.setModeOfInstruction("In Class");
        section4.setRoomNumber("C-15");
        section4.setSemester("Summer-2015");
        section4.setTotalCapacity(42);
        section4.setWaitListCapacity(15);
        section4.setCourse(course4);
        section4.setProfessor(professor4);
        section4.setPrice(215);

        Section section5 = new Section();

        Calendar classStartDateTimeSection5 = Calendar.getInstance();
        classStartDateTimeSection5.set(2016, Calendar.JANUARY, 10,17,30,0);
        section5.setClassStartTime(classStartDateTimeSection5.getTime());

        Calendar classEndDateTimeSection5 = Calendar.getInstance();
        classEndDateTimeSection5.set(2016, Calendar.MAY, 26, 20, 15,0);
        section5.setClassEndTime(classEndDateTimeSection5.getTime());

        section5.setDayOfWeek("Wednesday");
        section5.setStartDate(classStartDateTimeSection5.getTime());
        section5.setEndDate(classEndDateTimeSection5.getTime());

        section5.setModeOfInstruction("In Class");
        section5.setRoomNumber("MT-33");
        section5.setSemester("Spring-2016");
        section5.setTotalCapacity(40);
        section5.setWaitListCapacity(10);
        section5.setCourse(course4);
        section5.setProfessor(professor5);
        section5.setPrice(230);


        entityManager.getTransaction().begin();
        entityManager.persist(student_role);
        entityManager.persist(professor_role);
        entityManager.persist(admin_role);

        entityManager.persist(course1);
        entityManager.persist(course2);
        entityManager.persist(course3);
        entityManager.persist(course4);
        entityManager.persist(course5);

        entityManager.persist(professor1);
        entityManager.persist(professor2);
        entityManager.persist(professor3);
        entityManager.persist(professor4);
        entityManager.persist(professor5);
        entityManager.persist(professor6);

        entityManager.persist(section1);
        entityManager.persist(section2);
        entityManager.persist(section3);
        entityManager.persist(section4);
        entityManager.persist(section5);

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(student4);
        entityManager.persist(student5);
        entityManager.persist(student6);
        entityManager.persist(student7);

        student1.addSection(section1);

        entityManager.getTransaction().commit();

        System.out.println("Dala load complete");

       }
}
