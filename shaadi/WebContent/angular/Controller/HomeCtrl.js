
angular
.module('App')
.controller('HomeCtrl',['$scope','$rootScope','$window','userservice',HomeCtrl]);

function HomeCtrl(scope,rootscope,window,userservice){
	
scope.search={};
scope.searchResults=[];
window.alert("Welcome HomeCtrl");
	scope.submit=function(){
		alert(JSON.stringify(scope.search));
		
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
	
	var response=userservice.getLocationandOccupation();
	response
	  .then(function(resp){
			alert(JSON.stringify(resp.data));
			scope.locations=resp.data.location;
		    scope.occupations=resp.data.occupation;
	  },function(){
		  alert("error");
	  });	
	

	
}


