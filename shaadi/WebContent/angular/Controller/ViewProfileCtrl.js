
angular
.module('App')
.controller('ViewProfileCtrl',['$scope','$rootScope','$window','userservice','adminservice',ViewProfileCtrl]);

function ViewProfileCtrl(scope,rootscope,window,userservice,adminservice){
	
	var id=rootscope.userid;
	 rootscope.images=[]; 
	 scope.search={};
	 var response=userservice.getDetailsUsingId(id);
	 response
	    .then(function(resp){
	    	rootscope.userDetails=resp.data;
	    	         scope.search.userId=rootscope.userid;
	    			 scope.search.gender=rootscope.userDetails.gender;
	    			 scope.search.interest=rootscope.userDetails.ocupation;
	    			 scope.search.location=rootscope.userDetails.location;
	    			 scope.search.caste=rootscope.userDetails.caste;
	    			 scope.search.from=rootscope.userDetails.age;
	    			 scope.search.to=rootscope.userDetails.age;		 
	    		   if(scope.search.gender!=undefined && scope.search.caste!=undefined)
	    			   {
								var response=userservice.searchMatch(scope.search);
								response
								  .then(function(resp1){
										alert(JSON.stringify(resp1.data));
										if(resp1.data.id!="" ||resp1.data.id!=null)
										  {
											rootscope.userDetailssMatch=resp1.data;
										 
										  }
									
								  },function(){
									  alert("error");
								  });
	    
	    			   }
	    	if(resp.data=="")
	    		{
	    		alert("Feel The Form ");
	    		window.location="#/editProfile";
	    		}
	    	
	    },function(){
	    	alert("Error")
	    });
	 
	 var response=userservice.getImageById(id);
	 response
	    .then(function(resp){
	   	   rootscope.images=resp.data;
	   	   scope.profileImage={};
	    	 scope.coverImage={};
	   	   for(var i=0;i<rootscope.images.length;i++)
	   		   {
	   		    if(rootscope.images[i].isProfile=="profile")
	   			   {	   		
	   			scope.profileImage=rootscope.images[i].image;
	   			   }
	   		   else  if(rootscope.images[i].isProfile=="cover")
 			   {	   			
 		     	scope.coverImage=rootscope.images[i].image;
 			      $("#head").css('background','url("'+'data:image/jpeg;base64,'+scope.coverImage+'")');
 			      $("#head").css('background-repeat','no-repeat');
 			      $("#head").css('background-size','cover'); 	
 			       $("#head").css('overflow','hidden'); 	
 			     
 			   }
	   		   }
	    	
	    },function(){
	    	alert("Error")
	    });
	 
	 
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
	 
	
	 scope.submitMatch=function(){
			alert("scope.search waala"+JSON.stringify(scope.search));
		
			var response=userservice.searchMatch(scope.search);
			response
			  .then(function(resp){
					alert(JSON.stringify(resp.data));
					if(resp.data.id!="" ||resp.data.id!=null)
					  {
						rootscope.searchResults=resp.data;
					    window.location="#/searchResult";
					  }
				  alert(resp.data);
			  },function(){
				  alert("error");
			  });
		};
	
	var response=userservice.getFriendRequest(rootscope.userid);
		response
		  .then(function(resp){
				alert(JSON.stringify(resp.data));
				if(resp.data.id!="" ||resp.data.id!=null)
				  {
					scope.friendrequests=resp.data;
				  }
			 
		  },function(){
			  alert("error");
		  });
		
		
	scope.approve=function(senderId)
	{
		alert("senderId"+senderId+"Logger id"+rootscope.userid);
		var response=userservice.approveFriendRequest(senderId,rootscope.userid);
		response
		  .then(function(resp){
				alert(JSON.stringify(resp.data));
				
			 
		  },function(){
			  alert("error");
		  });
	}
		
	 
}