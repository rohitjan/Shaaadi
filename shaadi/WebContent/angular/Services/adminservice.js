 
  angular
    .module('App')
    .service('adminservice', ['$http', function(http) {
      var baseUrl = 'http://localhost:8089/ShaadiWebsite/adminController';
   
      this.checklogin=function(userName,userPassword)
      {
    	  alert("inside Service layer" +userName +"Password"+userPassword);
    	  return http.post(baseUrl+"/checklogin/"+userName+"/"+userPassword);
      }
     
      this.registerUser=function(registerObj){    
    	  alert("Inside service"+registerObj.username);
    	  return http.post(baseUrl+"/registerUser",registerObj);  
      }
      
    }]);
  