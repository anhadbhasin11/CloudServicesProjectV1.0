(function () {
    
    var app = angular.module('school', []);
    
    /**
	 * @name :  CourseController
	 * @description : This method is used to display all the courses. 
	 * @since   2015-10-25
	 * @exception : Throws any exception it gets to the StudentRegistration main class.
	 */
    app.controller('CourseController', ['$scope', '$http', function ($scope, $http) {
    	$http.get('/ClientForStudentRegistration/webapi/course', {
            headers: {
                'Authorization': 'Basic MTAwOTpwYXNzdw=='
            }
        }).success(function(response){$scope.products = response;
        
        
        
        	//Concurrency Control.
        	if(response.status==304)
        		{
        		$scope.products = response;
        		}
        	
        	
        	
        })
    }]);

    /**
	 * @name :  StudentFormController
	 * @description : This method is used to submit a student into the database.
	 * @since   2015-10-25
	 * @exception : Throws any exception it gets to the StudentRegistration main class.
	 */
    app.controller('StudentFormController', ['$http', function ($http) {
        this.form = {};


        this.addStudent = function (firstName, lastName, emailAddress, contactNumber, country, highSchool, gpa, specialization, sop, lor) {
            //alert("Processing..");
            var dataObj = {
                "firstName": firstName,
                    "lastName": lastName,
                    "emailAddress": emailAddress,
                    "contactNumber": contactNumber,
                    "country": country,
                    "highSchool": highSchool,
                    "gpa": gpa,
                    "specialization": specialization,
                    "sop": sop,
                    "lor": lor
            }



            var res = $http.post('/ClientForStudentRegistration/webapi/student/', dataObj, {
                headers: {
                    'Authorization': 'Basic MTAwOTpwYXNzdw=='
                }
            });
            res.success(function () {
                alert("Student Registered Successfully. Please check your email for login details.");
            });
            res.error(function () {
                alert("Failed : Student Registration unsuccessful.");
            });
        }
    }]);
    
    /**
	 * @name :  LoginFormController
	 * @description : This helps user login into the system
	 * @since   2015-10-25
	 * @exception : Throws any exception it gets to the StudentRegistration main class.
	 */
    app.controller('LoginFormController', ['$http', function ($http) {
        this.form = {};

        this.validateLogin = function (username, password) {

            var res = $http.post('/ClientForStudentRegistration/webapi/student/login/', {
                headers: {
                    'Authorization': createHeader(username, password)
                }
            });

            return res;


        }
        /**
    	 * @name :  Create Header
    	 * @description :Create Header for the HTTP requests 
    	 * @since   2015-10-25
    	 * @exception : Throws any exception it gets to the StudentRegistration main class.
    	 */
        this.createHeader = function (username, password) {
            return this.setRequestHeader('Authorization', 'Basic ' + Base64.encode("username:password"));
        }
        
        
        
        
        
        /**
    	 * @name :  Register Course
    	 * @description :This method registers a course for the student.
    	 * @since   2015-10-25
    	 * @exception : Throws any exception it gets to the StudentRegistration main class.
    	 */
        
        this.registerCourse = function(courseID){
        	 var res = $http.post('/ClientForStudentRegistration/webapi/student/1018/course/' + courseID,null, {
                 headers: {
                     'Authorization': 'Basic MTAwOTpwYXNzdw=='
                 }
             });
             res.success(function () {
            	 alert("Registered for course : " + courseID)
             });
        	
        	
        }

        /**
    	 * @name :  Login
    	 * @description :Logs in a user into the system
    	 * @since   2015-10-25
    	 * @exception : Throws any exception it gets to the StudentRegistration main class.
    	 */
        
        this.login = function (username, pass) {
            if (username===pass) {
                alert("Login Successful");
                window.open('http://localhost:8080/ClientForStudentRegistration/studentClient/LoginCourseInformation.html', '_self', false)
            } else {
                alert("Login unsuccessful");
            }
        }


    }]);


    /**
	 * VARIABLE FOR TESTING PURPOSE
	 */
    
    var courses = [{
        courseCode: "CS-201",
        course: "",
        courseId: 121,
        courseInformation: "Programming in a high-level language such as Java for students with significant prior programming experience. ",
        courseName: "Computer Architecture",
        courseSchedule: "T,Th 6:00-8:45 p.m",
        departmentId: 123238,
        instructorName: "Ahmed Nouri",
    }, {
        courseCode: "AA-987",
        courseId: 190,
        courseInformation: "This course answers that question from start to finish. ",
        courseName: "Physics 131",
        courseSchedule: "M,W 6:00-8:45 p.m",
        departmentId: 119090,
        instructorName: "Dr. Saday",
    }, {
        courseCode: "AA-897",
        courseId: 191,
        courseInformation: "We will discuss the discovery of over 2000 exoplanets around stars other than the Sun. ",
        courseName: "Astrophysics",
        courseSchedule: "Tu,Th 6:00-8:45 p.m",
        departmentId: 119090,
        instructorName: "Rita Ora",
    }

    ];

})();
/**
 * @name :  CACHE CONTROLLER MODULE
 * @description :This is used to handle the cache in the browser.
 * @since   2015-10-25
 * @exception : Throws any exception it gets to the StudentRegistration main class.
 */

angular.module('cacheExampleApp', []).
controller('CacheController', ['$scope', '$cacheFactory', function ($scope, $cacheFactory) {
    $scope.keys = [];
    $scope.cache = $cacheFactory('cacheId');
    $scope.put = function (key, value) {
        if (angular.isUndefined($scope.cache.get(key))) {
            $scope.keys.push(key);
        }
        $scope.cache.put(key, angular.isUndefined(value) ? null : value);
    };
}]);


/**
 * @name :  SSL Injector
 * @description :Converts http requests into https requests.
 * @since   2015-10-25
 * @exception : Throws any exception it gets to the StudentRegistration main class.
 */

angular.module('sslInjection', []).controller('sslInjector', function () {

    var forceSSL = function () {
        if ($location.protocol() !== 'https') {
            $window.location.href = $location.absUrl().replace('http', 'https');
        }
    };
    forceSSL();


});