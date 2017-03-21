package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daoLayer.UserDao;
import com.pojo.ImageFile;
import com.pojo.LocationOccupationPojo;
import com.pojo.SearchResponsePojo;
import com.pojo.SearchResultPojo;
import com.pojo.UserDetails;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userdao;
	
	
	public String updateUserDetails(UserDetails userdetails) {
		// TODO Auto-generated method stub
		return userdao.updateUserDetails(userdetails);
	}

	public String updateProfilePic(ImageFile imageFile){
		
		return userdao.updateProfilePic(imageFile);
	}

	public UserDetails getDetailsUsingId(int id) {
		// TODO Auto-generated method stub
		return userdao.getDetailsUsingId(id);
	}

	public List<SearchResponsePojo> searchMatch(SearchResultPojo searchMatch) {
		// TODO Auto-generated method stub
		return userdao.searchMatch(searchMatch);
	}

	public List<ImageFile> downloadimage(int id) {
		// TODO Auto-generated method stub
		return userdao.downloadimage(id);
	}

	public String updateProfilePhoto(String imageId, String userId, String isProfle) {
		// TODO Auto-generated method stub
		return userdao.updateProfilePhoto(imageId,userId,isProfle);
	}

	public String removeImage(int id) {
		// TODO Auto-generated method stub
		return userdao.removeImage(id);
	}

	public LocationOccupationPojo getLocationandOccupation() {
		// TODO Auto-generated method stub
		return userdao.getLocationandOccupation();
	}

	public String sendFriendRequest(int senderId, int recieverId, String status) {
		// TODO Auto-generated method stub
		return userdao.sendFriendRequest(senderId,recieverId,status);
	}

	public List<SearchResponsePojo> getFriendRequest(int userId) {
		// TODO Auto-generated method stub
		return userdao.getFriendRequest(userId);
	}

	public String approveFriendRequest(int senderId, int recieverId) {
		// TODO Auto-generated method stub
		return userdao.approveFriendRequest(senderId,recieverId);
	}

	public List<SearchResponsePojo> getFriendList(int loggerId) {
		// TODO Auto-generated method stub
		return userdao.getFriendList(loggerId);
	}
}
