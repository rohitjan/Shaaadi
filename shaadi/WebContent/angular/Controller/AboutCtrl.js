
angular
.module('App')
.controller('AboutCtrl',['$scope','$rootScope','$window',AboutCtrl]);

function AboutCtrl(scope,rootscope,window){

window.alert("Welcome AboutCtrl");
}	