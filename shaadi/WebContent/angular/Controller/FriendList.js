
angular
.module('App')
.controller('FriendList',['$scope','$rootScope','$window','userservice',FriendList]);

function FriendList(scope,rootscope,window,userservice){
	
   window.alert("Welcome FriendList");
    alert("loggerId"+rootscope.userid);
   
	var response=userservice.getFriendList(rootscope.userid);
	response
	  .then(function(resp){
			alert(JSON.stringify(resp.data));
			scope.friendlist=resp.data;
		 
	  },function(){
		  alert("error");
	  });
    
}	