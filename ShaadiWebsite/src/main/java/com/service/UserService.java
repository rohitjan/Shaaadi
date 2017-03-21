package com.service;

import java.util.List;

import com.pojo.ImageFile;
import com.pojo.LocationOccupationPojo;
import com.pojo.SearchResponsePojo;
import com.pojo.SearchResultPojo;
import com.pojo.UserDetails;

public interface UserService {

	public String updateUserDetails(UserDetails userdetails);
	public String updateProfilePic(ImageFile imageFile);
	public UserDetails getDetailsUsingId(int id);
	public List<SearchResponsePojo> searchMatch(SearchResultPojo searchMatch);
	public List<ImageFile> downloadimage(int id);
	public String updateProfilePhoto(String imageId,String userId,String isProfle);
	public String removeImage(int id);
	public LocationOccupationPojo getLocationandOccupation();
	public String sendFriendRequest(int senderId,int recieverId,String status);
	public List<SearchResponsePojo> getFriendRequest(int userId);
	public String approveFriendRequest(int senderId,int recieverId);
	public List<SearchResponsePojo> getFriendList(int loggerId);
}
