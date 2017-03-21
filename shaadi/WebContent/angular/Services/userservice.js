 
  angular
    .module('App')
    .service('userservice', ['$http', function(http) {
    	var baseUrl = 'http://localhost:8089/ShaadiWebsite/userController';
    	
    	this.updateDetails=function(details)
    	{
    		 alert(JSON.stringify(details));            
    		return http.post(baseUrl+"/updateDetails",details);
    	}
    	
      this.getDetailsUsingId=function(id)
      {
    	  
 		return http.post(baseUrl+"/getDetailsUsingId/"+id);
 	    }
      
      
      this.getImageById=function(id){
    	
    	  alert("Id of Image"+id);
    	  return http.get(baseUrl+'/'+'downloadimage/'+id); 
      }
     this.updateprofilePic=function(id,isProfle,imageDescription,file){
    		  var fd = new FormData();
              fd.append('file', file);
              

             
              http.post(baseUrl+'/updateprofilePic/'+id+'/'+isProfle+'/'+imageDescription, fd, {
                  transformRequest: angular.identity,
                  headers: {'Content-Type': undefined}
              })
              .success(function(resp){
            	  alert("Image Sucessfully Saved");
            	 
              })
              .error(function(resp){
              });
        }
   this.searchMatch=function(searchMatch){
	   
	   return http.post(baseUrl+"/searchMatch/",searchMatch);
   };
    	
   this.updateProfilePhoto=function(imageId,userid,isProfle){
	   alert("in Front End Service"+imageId+userid+isProfle);
	   return http.post(baseUrl+'/'+'updateProfilePhoto/'+imageId+'/'+userid+'/'+isProfle); 
   };
   
   this.removeImage=function(id){ 	
 	 
 	  return http.get(baseUrl+'/'+'removeImage/'+id); 
   }
    
   this.getLocationandOccupation=function(){
	   
	   return http.get(baseUrl+'/'+'getLocationandOccupation');
   }
   
   this.sendFriendRequest=function(senderId,reciverId,status){
	   
	   return http.post(baseUrl+"/sendFriendRequest/"+senderId+"/"+reciverId+"/"+status);
   }
  
   this.getFriendRequest=function(userId){
	   return http.post(baseUrl+"/getFriendRequest/"+userId);
   }
   
   this.approveFriendRequest=function(senderId,recieverId)
   {
	   return http.post(baseUrl+"/approveFriendRequest/"+senderId+"/"+recieverId);
   }
   
   this.getFriendList=function(userId)
   {
		return http.post(baseUrl+"/getFriendList/"+userId); 
   }
/*      this.login=function(user)
      {
    	 
    	  return http.post(baseUrl+"/checkuser",user);
      }
      
      this.newuser=function(user)
      {
    	 
    	  return http.post(baseUrl+"/newuser",user);
      }
      this.getUserByName=function(name)
      {
    	  
    	  return http.get(baseUrl+"/getuserbyname/"+name);
      }
      
      this.getimage=function(id) {
    	 
              return http.get(baseUrl+'/'+'downloadimage/'+id);          
              };
                      
      this.uploadFileToUrl = function(file,id){

              var fd = new FormData();
              fd.append('file', file);
              

             
              http.post(baseUrl+'/uploadimage/'+id, fd, {
                  transformRequest: angular.identity,
                  headers: {'Content-Type': undefined}
              })
              .success(function(resp){
              })
              .error(function(resp){
              });
          }
      this.addrequest=function(user,name,firstname,lastname){
    	 
    	  return http.post(baseUrl+"/addrequest/"+user+"/"+name+"/"+firstname+"/"+lastname);
      }
      
     //Get List Of All FRiends From Controller
      this.allfriends=function(loginuser){
    	 
    	  return http.get(baseUrl+"/allfriends/"+loginuser);
      }
      
      this.seeallpending=function(name){
    	
    	  return http.post(baseUrl+"/seeallpending/"+name);
      }
      this.getAllFriendRequest=function(){
    	  
    	  return http.get(baseUrl+"/getallfriendrequest");
      }
      
      this.AcceptPending=function(id,username){
    	  
    	  
    	  return http.get(baseUrl+"/acceptpending/"+id+"/"+username);
      }
    this.profileimage=function(username,imageobject){
    	  
    	 
    	  return http.post(baseUrl+"/profilepic/"+username,imageobject);
      }
      
    this.getLoginPic=function(name){
  	  
  	 
  	  return http.post(baseUrl+"/getloginpojo/"+name);
    }
    this.RemovePending=function(id){
    	  
    	 
    	  return http.post(baseUrl+"/removepending/"+id);
      }
    this.logout=function(loginuser){
  	  
  	
  	  return http.post(baseUrl+"/logout/"+loginuser);
    }
    this.getalllogin=function(){
    	  
    	 
    	  return http.get(baseUrl+"/getalllogin/");
      }
    this.savechat=function(sender,reciver,sendermsg){
  	  
   	 
  	  return http.post(baseUrl+"/savechat/"+sender+"/"+reciver+"/"+sendermsg);
    }
   
    this.getOfflineChat=function(sender,reciver){
    	  
      	 alert('in get offline chat');
      	 alert(sender+' '+reciver);
    	  return http.post(baseUrl+"/getofflinechat/"+sender+"/"+reciver);
      }
    this.checkpassword=function(username,oldpassword){
    	  
      	 alert("in checkpassword")
    	  return http.get(baseUrl+"/checkoldpassword/"+username+"/"+oldpassword);
      }
    this.updatepassword=function(username,newpassword,oldpassword){
  	  
     	 alert("in checkpassword")
   	  return http.get(baseUrl+"/updatepassword/"+username+"/"+newpassword+"/"+oldpassword);
     }
    
    this.getbyusername=function(username){
    	  
    	 alert("in getbyusername")
  	  return http.get(baseUrl+"/getbyusername/"+username);
    }
    */

    }]);
