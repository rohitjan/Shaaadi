
angular
.module('App')
.controller('RegisterCtrl',['$scope','$rootScope','$window','userservice','adminservice',RegisterCtrl]);

function RegisterCtrl(scope,rootscope,window,userservice,adminservice){
	
	scope.register={};
	scope.submit=function()
	 {
		var response=adminservice.registerUser(scope.register);
		 response
		    .then(function(resp){
		    	
		    	if(resp.data.msg=="Successfully Registered")
		    		{
		    		window.location="#/login";
		    		}
		    },function(){
		    	alert("Error")
		    });
	 }


}	