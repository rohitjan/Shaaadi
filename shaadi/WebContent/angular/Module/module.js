'use strict';
var myapp=angular.module('App',['ngRoute']);

myapp.config(['$routeProvider',function(rp){
	
	rp.when('/home',{
		templateUrl:'angular/View/home.html',
		controller:'HomeCtrl'	
	})
		rp.when('/login',{
		templateUrl:'angular/View/login.html',
		controller:'LoginCtrl'	
	})
	rp.when('/about',{
		templateUrl:'angular/View/about.html',
		controller:'AboutCtrl'	
	})	
	rp.when('/editProfile',{
		templateUrl:'angular/View/editProfile.html',
		controller:'EditProfileCtrl'	
	})
	rp.when('/register',{
		templateUrl:'angular/View/register.html',
		controller:'RegisterCtrl'	
	})
	rp.when('/viewProfile',{
		templateUrl:'angular/View/viewProfile.html',
		controller:'ViewProfileCtrl'	
	})
	rp.when('/searchResult',{
		templateUrl:'angular/View/searchResult.html',
		controller:'SearchResult'	
	})
	rp.when('/searchResultDetails',{
		templateUrl:'angular/View/searchResultDetails.html',
		controller:'SearchResultDetails'	
	})
	rp.when('/friendList',{
		templateUrl:'angular/View/friendList.html',
		controller:'FriendList'	
	})
	.otherwise(
			{
				redirectTo:'/home'				
			}
	);	
	
}]);