package com.pojo;

import java.util.List;

public class SearchResponsePojo {

	
	private UserDetails userObject;
    private ImageFile imagePojo;
	private FriendRequest frndReq;
	
	
    public UserDetails getUserObject() {
		return userObject;
	}
	public void setUserObject(UserDetails userObject) {
		this.userObject = userObject;
	}
	public ImageFile getImagePojo() {
		return imagePojo;
	}
	public void setImagePojo(ImageFile imagePojo) {
		this.imagePojo = imagePojo;
	}
	public FriendRequest getFrndReq() {
		return frndReq;
	}
	public void setFrndReq(FriendRequest frndReq) {
		this.frndReq = frndReq;
	}

	
    
    
}
