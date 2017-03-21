
angular
.module('App')
.controller('SearchResultDetails',['$scope','$rootScope','$window',SearchResultDetails]);

function SearchResultDetails(scope,rootscope,window){
	scope.profileImage=rootscope.profileImage;
   	scope.coverImage=rootscope.coverImage;
   window.alert("Welcome SearchResultDetails");
    
  
}	