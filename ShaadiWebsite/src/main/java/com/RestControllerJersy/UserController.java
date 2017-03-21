package com.RestControllerJersy;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.pojo.ImageFile;
import com.pojo.LocationOccupationPojo;
import com.pojo.Register;
import com.pojo.SearchResponsePojo;
import com.pojo.SearchResultPojo;
import com.pojo.UserDetails;
import com.service.AdminService;
import com.service.UserService;

@RestController
@RequestMapping("/userController")
public class UserController {
   
	@Autowired
	UserService userservice;
 
	@RequestMapping(value="/updateDetails",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public String updateUserDetails(@RequestBody UserDetails userDetails){
		String response="{\"msg\":\"True\"}";
		System.out.println(userDetails.getName());		
		return userservice.updateUserDetails(userDetails);
	}
	
	@RequestMapping(value = "/updateprofilePic/{id}/{isProfle}/{imageDescription}", method=RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadimage(@PathVariable("id") int id,@PathVariable("isProfle") String isProfle, @PathVariable("imageDescription") String imageDescription,
			@RequestParam("file") CommonsMultipartFile[] imagefile){
		System.out.println("inside image controller");
		ImageFile imageObj=null;
		for (CommonsMultipartFile commonsMultipartFile : imagefile) {
			imageObj=new ImageFile();
			imageObj.setImage(commonsMultipartFile.getBytes());
			imageObj.setContenttype(commonsMultipartFile.getContentType());
			imageObj.setLength(commonsMultipartFile.getSize());
			imageObj.setName(commonsMultipartFile.getName());
			imageObj.setUid(id);	
			imageObj.setIsProfile(isProfle);
			imageObj.setImageDescription(imageDescription);
		}
		return userservice.updateProfilePic(imageObj);
	}
	
	
	@RequestMapping(value="/getDetailsUsingId/{id}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public UserDetails getDetailsUsingId(@PathVariable("id") int id){	
			
		return userservice.getDetailsUsingId(id);
	}
	
	@RequestMapping(value="/searchMatch",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<SearchResponsePojo> searchMatch(@RequestBody SearchResultPojo searchResultPojo){	
			
		return userservice.searchMatch(searchResultPojo);
		
	}
	
	@RequestMapping(value="/downloadimage/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ImageFile>> downloadimage(@PathVariable("id") int id) throws IOException{	
	
		System.out.println("Inside download Image "+id);
		List<ImageFile> list=userservice.downloadimage(id);
		HttpHeaders headers=new HttpHeaders();		
		headers.setContentType( MediaType.parseMediaType("application/octet-stream"));			
		return new ResponseEntity<List<ImageFile>>(list, headers, HttpStatus.CREATED);  
	}
	
	@RequestMapping(value = "/updateProfilePhoto/{imageId}/{userId}/{isProfle}", method=RequestMethod.POST)
	public String updateProfilePhoto(@PathVariable("imageId") String imageId,@PathVariable("userId") String userId,@PathVariable("isProfle") String isProfle){
		System.out.println("inside image controller");
		
		return userservice.updateProfilePhoto(imageId,userId,isProfle);
	}
	
	@RequestMapping(value="/removeImage/{id}",method=RequestMethod.GET)
	public String removeImage(@PathVariable("id") int id){	
		String response="{\"msg\":\"True\"}";
		return userservice.removeImage(id);
	}
	
	@RequestMapping(value="/getLocationandOccupation",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public LocationOccupationPojo getLocationandOccupation() throws IOException{	
	
		System.out.println("Inside getLocationandOccupation  ");
	    return userservice.getLocationandOccupation();
		
	}
	
	@RequestMapping(value = "/sendFriendRequest/{senderId}/{recieverId}/{status}", method=RequestMethod.POST)
	public String sendFriendRequest(@PathVariable("senderId") int senderId,@PathVariable("recieverId") int recieverId,@PathVariable("status") String status){
		System.out.println("inside sendFriendRequest ");
		
		return userservice.sendFriendRequest(senderId,recieverId,status);
	}
	
	@RequestMapping(value = "/getFriendRequest/{userId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SearchResponsePojo> getFriendRequest(@PathVariable("userId") int userId){
		System.out.println("inside getFriendRequest ");
		
		return userservice.getFriendRequest(userId);
	}
	
	@RequestMapping(value = "/approveFriendRequest/{senderId}/{recieverId}", method=RequestMethod.POST)
	public String approveFriendRequest(@PathVariable("senderId") int senderId,@PathVariable("recieverId") int recieverId){
		System.out.println("inside approveFriendRequest ");
		
		return userservice.approveFriendRequest(senderId,recieverId);
	}
	
	@RequestMapping(value = "/getFriendList/{logggerId}", method=RequestMethod.POST ,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SearchResponsePojo> getFriendList(@PathVariable("logggerId") int logggerId){
		System.out.println("inside getFriendList ");
		
		return userservice.getFriendList(logggerId);
	}
	
	
}
