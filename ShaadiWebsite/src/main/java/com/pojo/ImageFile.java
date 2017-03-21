package com.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ImageFile")
public class ImageFile {

	@Id
	private int id;	
	private int uid;	
	private byte[] image;
	private String contenttype;
	private String name;
	private double length;
	private String isProfile;
	private String imageDescription;
	
	
	
	
	
	public String getImageDescription() {
		return imageDescription;
	}
	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}
	public String getIsProfile() {
		return isProfile;
	}
	public void setIsProfile(String isProfile) {
		this.isProfile = isProfile;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	public int getUid() {
		return uid;
	}

	
	public void setUid(int uid) {
		this.uid = uid;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	
	
	
}


