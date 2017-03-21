
angular
.module('App')
.controller('SearchResult',['$scope','$rootScope','$window','userservice','adminservice',SearchResult]);

function SearchResult(scope,rootscope,window,userservice,adminservice){

	 alert("Welcome SearchResult");
	 scope.userDetailss=rootscope.searchResults;
	 scope.showError=false;
	  var userid1=0;     
 scope.viewUserDetails=function(userid)
 {	   	
	 if(rootscope.userid==""||rootscope.userid==null||rootscope.userid==undefined){
	 	  
		scope.showError=scope.showError===false?true:false;
	   }
	
	 else{
	
	 alert("aagya id"+userid);
	 userid1=userid;
	 var response=userservice.getDetailsUsingId(userid);
	 response
	    .then(function(resp){
	    	rootscope.userDetails=resp.data;
	    	
	    	if(resp.data=="")
	    		{
	    		alert("Feel The Form ");
	    		window.location="#/editProfile";
	    		}
	    	
	    },function(){
	    	alert("Error")
	    });
	 
	 var response=userservice.getImageById(userid1);
	 response
	    .then(function(resp){
	   	   rootscope.images=resp.data;
	   	rootscope.profileImage={};
	   	rootscope.coverImage={};
	   	   for(var i=0;i<rootscope.images.length;i++)
	   		   {
	   		    if(rootscope.images[i].isProfile=="profile")
	   			   {	   		
	   			rootscope.profileImage=rootscope.images[i].image;
	   		  
	   			   }
	   		   else  if(rootscope.images[i].isProfile=="cover")
 			   {	   			
	   			rootscope.coverImage=rootscope.images[i].image;
 			      $("#head").css('background','url("'+'data:image/jpeg;base64,'+scope.coverImage+'")');
 			      $("#head").css('background-repeat','no-repeat');
 			      $("#head").css('background-size','cover'); 	
 			       $("#head").css('overflow','hidden'); 	
 			    
 			   }
	   		  
	   		   }
	   	  window.location="#/searchResultDetails";
	    },function(){
	    	alert("Error")
	    });
	 
	 
   }
 }
 
 
 //Friend Request Implementation
	 scope.sendRequest=function(reciverId){
		var status="Request Sent";
		 alert("senderId"+rootscope.userid);
		 alert("personClicked Id"+reciverId);
		
		   $("#"+reciverId).text("Request Sent");
		   $("#"+reciverId).off('click');
		 var response=userservice.sendFriendRequest(rootscope.userid,reciverId,status);
		 response
		    .then(function(resp){
		    	 alert(JSON.stringify(resp.data));
		    	 if(resp.data.msg=="sent")
		    		 {
		    		 
		    		 }
		    	
		    },function(){
		    	alert("Error")
		    });
	 }
 
}	