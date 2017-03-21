
angular
.module('App')
.controller('EditProfileCtrl',['$scope','$rootScope','$window','userservice',EditProfileCtrl]);

function EditProfileCtrl(scope,rootscope,window,userservice){

window.alert("Welcome EditProfileCtrl");
scope.isProfle="default";
scope.details={};
scope.myfile={};
scope.details=rootscope.userDetails;
scope.images=rootscope.images;
var userid=rootscope.userid;
scope.submit=function()
{
	var userid=rootscope.userid;
	scope.details.registerId=rootscope.userid;
	 var response=userservice.updateDetails(scope.details);
	 response
	    .then(function(resp){
	    	
	    	alert(JSON.stringify(resp.data));
	    	if(resp.data.msg=="True")
   		   {
	    		 window.location="#/viewProfile";
   		   }
	    	
	    },function(){
	    	alert("Error")
	    });
}

scope.submit1=function(){
	
	var id=rootscope.userid;
	 var file = scope.myFile;
	 var isProfle=scope.isProfle;
	 var imageDescription=scope.imageDescription;
	  userservice.updateprofilePic(id,isProfle,imageDescription,file);
}
scope.setProfile=function(imageId){
	scope.isProfile="profile";
	 var response=userservice.updateProfilePhoto(imageId,userid,scope.isProfile);
	 response
	    .then(function(resp){
	    	
	    	
	    	if(resp.data.msg=="inserted")
   		    {
	    		window.location="#/viewProfile";
   		    }
	    	
	    },function(){
	    	alert("Error")
	    });
}
scope.setCover=function(imageId){
	scope.isProfile="cover";
	 var response=userservice.updateProfilePhoto(imageId,userid,scope.isProfile);
	 response
	    .then(function(resp){
	    	
	    	
	    	if(resp.data.msg=="inserted")
  		    {
	    		window.location="#/viewProfile";
  	     	}
	    	
	    },function(){
	    	alert("Error")
	    });
}

scope.removeImage=function(imageId){
	alert("remove image Clicked"+imageId);
	var r = confirm("Are You Sure You want To remove this Image!");
	if (r == true) {
		 var response=userservice.removeImage(imageId);
		 response
		    .then(function(resp){
		    	
		    	
		    	if(resp.data.msg=="removed")
	  		    {
		    	alert("Image Successfully Removed");
		    	window.location="#/viewProfile";
	  	     	}
		    	
		    },function(){
		    	alert("Error")
		    });
	} 
	
	else {
		window.location.reload();
	}
	
}

}	