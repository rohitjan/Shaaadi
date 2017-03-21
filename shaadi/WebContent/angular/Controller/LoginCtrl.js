
angular
.module('App')
.controller('LoginCtrl',['$scope','$rootScope','$window','userservice','adminservice',LoginCtrl]);

function LoginCtrl(scope,rootscope,window,userservice,adminservice){


 scope.submit=function()
 {
	 
	 var userName=scope.userName;
	 var userPassword=scope.userPassword;	 
	 var response=adminservice.checklogin(userName,userPassword);
	 response
	    .then(function(resp){
	    	
	    	
	    	rootscope.loginpojo=resp.data
	    	rootscope.userid=resp.data.id;
	    	
	    	if(resp.data.id!="" ||resp.data.id!=null)
    		{
    		 window.location="#/viewProfile";
    		}
	    	
	    },function(){
	    	alert("Invalid Credential");
	    	 window.location="#/login";
	    });
 }
}	