# CMPE-272-DB-TESTING
 -uuu test some
-test
- To run the project you should have maven, java and mysql on your machine
- To run it in eclipse
        - Clone git respository
        - Open the project as Maven Project
- To update all maven dependencies use following command
        -  mvn clean install
- To create Executable jar file use following command
        -  mvn clean compile assembly:single
- To connect to the database while running the Bootstrap class (main class) provide following arguments:
  -DIPADDRESS=localhost
  -DPORT=3306
  -DDBNAME=hibernate_test
  -DUSER=root
  -DPASS=root

- To set the Isolation Level for our projectfor MySQL, fire the query:
   SET @@GLOBAL.tx_isolation = 'REPEATABLE-READ';

- To check the Isolation Level on your MySQL DB, fire the query:
   SELECT @@GLOBAL.tx_isolation, @@tx_isolation;


- To build, deploy and run application on Tomcat
-- Download Apache Tomcat 7 and install it on your machine
-- Change the username and password for the role "manager-gui" and "manager-script" as mentioned here- http://www.mkyong.com/maven/how-to-deploy-maven-based-war-file-to-tomcat/
-- So username and password should be same at following location
        1. Maven's settings.xml
        2. Tomcat's tomcat-users.xml
-- To verify if the password has been set -
-- Try and Open http://localhost:8080/manager/
   It will ask for credentials
   Enter Credentials which you have set in above steps and see if you can access manager-gui
-- Pull latest changes from our git repository
-- To deploy application for the first time execute
    mvn tomcat7:deploy
-- For the consequent deployments use
    mvn tomcat7:redeploy

-- Once application is deployed successfully you can try -
   http://localhost:8080/courseregistration/students/

   Should return a list of students

-- modify tomcats server.xml to servers to add path of the file cmpe272.keystore

//TODO -
1.ApplicationContext.xml --> use https instead of any
2.Client side ---> Build httpConfig for https connection





