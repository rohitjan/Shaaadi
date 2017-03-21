package com.daoLayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.criteria.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pojo.FriendRequest;
import com.pojo.ImageFile;
import com.pojo.LocationOccupationPojo;
import com.pojo.SearchResponsePojo;
import com.pojo.SearchResultPojo;
import com.pojo.UserDetails;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public String updateUserDetails(UserDetails userdetails) {
		 String response="{\"msg\":\"updated\"}";
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(userdetails);
		return response;
	}

	@Transactional
	public String updateProfilePic(ImageFile imageFile) {
		
		 String response="{\"msg\":\"inserted\"}";
		Session session=sessionFactory.getCurrentSession();
		session.persist(imageFile);		
		return response;
		
		
	}

	@Transactional
	public UserDetails getDetailsUsingId(int id) {
		System.out.println("in Dao impl getDetailsUsingId"+id);
		Session session=sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(UserDetails.class);
		crit.add(Restrictions.eq("registerId",id));
		List<UserDetails> results = crit.list();
		if(results!=null && !results.isEmpty())
		{
			return results.get(0);
		}
		return null;

	}

	@Transactional
	public List<SearchResponsePojo> searchMatch(SearchResultPojo searchMatch) {
		
		Session session=sessionFactory.getCurrentSession();
		Query query1=null;
	
		if(searchMatch.getGender()!=null && searchMatch.getFrom()!=null && searchMatch.getCaste()!=null)
		{
			String caste=searchMatch.getCaste().substring(0, 2);
			query1=session.createQuery("from UserDetails where gender=:gender and age>=:age  and caste like :caste");
			query1.setParameter("gender",searchMatch.getGender());
			query1.setParameter("age",searchMatch.getFrom());
			query1.setParameter("caste",caste+"%");
		
		}
		else
		{	
		
			 if(searchMatch.getGender()==null)
			{
				 String caste=searchMatch.getCaste().substring(0, 2);
				query1=session.createQuery("from UserDetails where age>:age  and caste like :caste");
				query1.setParameter("age",searchMatch.getFrom());
				query1.setParameter("caste",caste);
			}
		
			 if(searchMatch.getFrom()==null)
			{		
				String caste=searchMatch.getCaste().substring(0, 2);
				query1=session.createQuery("from UserDetails where gender=:gender  and caste like :caste");
				query1.setParameter("gender",searchMatch.getGender());		
				query1.setParameter("caste",caste);
			}
		
			if(searchMatch.getCaste()==null)
			{			
				query1=session.createQuery("from UserDetails where gender=:gender  and  age>:age");
				query1.setParameter("gender",searchMatch.getGender());
				query1.setParameter("age",searchMatch.getFrom());
			}
		
		}
	
		//Criterion crit4=Restrictions.or(crit2,crit3);
		/*crit.add(Restrictions.and(crit1,crit2,crit3));*/
		
		/*crit.add(Restrictions.eq("gender", searchMatch.getGender()) );
		crit.add(Restrictions.between("age", searchMatch.getTo(), searchMatch.getFrom()));		
		crit.add(Restrictions.ilike("caste", searchMatch.getCaste(),MatchMode.START));*/
		List<UserDetails> list=query1.list();
		SearchResponsePojo searchResponsePojo=null;
		List<SearchResponsePojo> searchResponsePojoList=new ArrayList<SearchResponsePojo>();
		if(!list.isEmpty())
		{
		  for (UserDetails userDetails : list) {
			  
			   searchResponsePojo=new SearchResponsePojo();
				Query query=session.createQuery("from ImageFile where uid=:id");
				query.setParameter("id",userDetails.getRegisterId());
				List<ImageFile> listOfImages=query.list();	
				for (ImageFile imageFile : listOfImages) {
					if(imageFile.getIsProfile().equalsIgnoreCase("profile"))
					{
						searchResponsePojo.setImagePojo(imageFile);
					}
				}
				if(searchMatch.getUserId()!=null)
				{
				Query frndQuery=session.createQuery("from FriendRequest where senderId=:senderId and recieverId=:recieverId");
				frndQuery.setParameter("senderId",Integer.parseInt(searchMatch.getUserId()));
				frndQuery.setParameter("recieverId",userDetails.getRegisterId());
				List<Object> frndList=frndQuery.list();
				if(!frndList.isEmpty())
				{
					FriendRequest frndReq=(FriendRequest)frndList.get(0);
					searchResponsePojo.setFrndReq(frndReq);
				}
				else{
					FriendRequest frndReq =new FriendRequest();
					frndReq.setStatus("Add as Friend");
					frndReq.setRecieverId(userDetails.getRegisterId());
					frndReq.setSenderId(Integer.parseInt(searchMatch.getUserId()));
					searchResponsePojo.setFrndReq(frndReq);
				}
				searchResponsePojo.setUserObject(userDetails);				
				searchResponsePojoList.add(searchResponsePojo);
			}
				
		  }
		}
		return searchResponsePojoList;
 	}

	@Transactional
	public List<ImageFile> downloadimage(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		org.hibernate.Query query=session.createQuery("from ImageFile where uid=:id");
		query.setParameter("id",id);
		List<ImageFile> list=query.list();		
		for (ImageFile imageFile : list) {
			System.out.println("mera wala "+imageFile.getName());
		}
		return list;
	}

	@Transactional
	public String updateProfilePhoto(String imageId, String userId, String isProfle) {
		Session session = sessionFactory.getCurrentSession();
		org.hibernate.Query query=session.createQuery("from ImageFile where uid=:id");
		query.setParameter("id",Integer.parseInt(userId));
		List<ImageFile> list=query.list();	
		
		for (ImageFile imageFile : list) {	
			if(imageFile.getId()==Integer.parseInt(imageId))
			{
				imageFile.setIsProfile(isProfle);			
			}
			else{
				if(imageFile.getIsProfile().equalsIgnoreCase(isProfle))
				   imageFile.setIsProfile("default");				
			}
			session.update(imageFile);
		}
		String response="{\"msg\":\"inserted\"}";
		return response;
	}

	@Transactional
	public String removeImage(int id) {
		 String response="{\"msg\":\"removed\"}";		
		 Session session = sessionFactory.getCurrentSession();
		 org.hibernate.Query query=session.createQuery("delete from ImageFile where id=:id");
		 query.setParameter("id",id);
		 query.executeUpdate();
		 return response;
	}

	@Transactional
	public LocationOccupationPojo getLocationandOccupation() {
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("from UserDetails");
		List<UserDetails> userDetailsList=query.list();
		LocationOccupationPojo locationOccupationPojo=new LocationOccupationPojo();
		Set<String> setOfLocation=new HashSet<String>();
		Set<String> setOfOccupation=new HashSet<String>();
		for (UserDetails userDetails : userDetailsList) {
			setOfLocation.add(userDetails.getLocation());
			setOfOccupation.add(userDetails.getOcupation());			
		}
		locationOccupationPojo.setLocation(setOfLocation);
		locationOccupationPojo.setOccupation(setOfOccupation);
		return locationOccupationPojo;
	}

	@Transactional
	public String sendFriendRequest(int senderId, int recieverId, String status) {
		 String response="{\"msg\":\"sent\"}";	
		 Session session = sessionFactory.getCurrentSession();
		 FriendRequest fr=new FriendRequest();
		 fr.setSenderId(senderId);
		 fr.setRecieverId(recieverId);
		 fr.setStatus(status);
		 session.save(fr);
		 return response;
		 
	}

	@Transactional
	public List<SearchResponsePojo> getFriendRequest(int userId) {		
		 Session session = sessionFactory.getCurrentSession();
		 SearchResponsePojo searchResponsePojo=null;
		 List<SearchResponsePojo> listOfSearchResponse=new ArrayList<SearchResponsePojo>();
		 Query frndQuery=session.createQuery("from FriendRequest where recieverId=:recieverId and status=:status");
		 frndQuery.setParameter("recieverId",userId);
		 frndQuery.setParameter("status","Request Sent");
			List<FriendRequest> frndList=frndQuery.list();
			if(!frndList.isEmpty())
			{
				for (FriendRequest friendRequest : frndList) {
					searchResponsePojo=new SearchResponsePojo();
					
					Query query=session.createQuery("from ImageFile where uid=:id");
					query.setParameter("id",friendRequest.getSenderId());
					List<ImageFile> listOfImages=query.list();	
					for (ImageFile imageFile : listOfImages) {
						if(imageFile.getIsProfile().equalsIgnoreCase("profile"))
						{
							searchResponsePojo.setImagePojo(imageFile);
						}
					}
					
					Query query1=session.createQuery("from UserDetails where registerId=:id");
					query1.setParameter("id",friendRequest.getSenderId());	
					List<UserDetails> listOfUserDetails=query1.list();
					searchResponsePojo.setUserObject(listOfUserDetails.get(0));
					listOfSearchResponse.add(searchResponsePojo);
				}
			 	
			}
		
			return listOfSearchResponse;
	}

	@Transactional
	public String approveFriendRequest(int senderId, int recieverId) {
    
		 String response="{\"msg\":\"sent\"}";	
		 Session session = sessionFactory.getCurrentSession();
		 Query frndQuery=session.createQuery("update FriendRequest set status=:status where recieverId=:recieverId and senderId=:senderId");
		 frndQuery.setParameter("recieverId",recieverId);
		 frndQuery.setParameter("senderId",senderId);
		 frndQuery.setParameter("status","Friend");
		 frndQuery.executeUpdate();
		 return response;
	}

	@Transactional
	public List<SearchResponsePojo> getFriendList(int loggerId) {
		 Session session = sessionFactory.getCurrentSession();
		 SearchResponsePojo searchResponsePojo=null;
		 List<SearchResponsePojo> listOfSearchResponse=new ArrayList<SearchResponsePojo>();
		 Query query=session.createQuery("from FriendRequest where (recieverId=:recieverId or senderId=:senderId) and status=:status");
		 query.setParameter("recieverId",loggerId);
		 query.setParameter("senderId",loggerId);
		 query.setParameter("status","Friend");
		 List<FriendRequest> listOfFrindeRequest=query.list();
		 for (FriendRequest friendRequest : listOfFrindeRequest) {
			if(friendRequest.getSenderId()!=loggerId)
			{
				searchResponsePojo=new SearchResponsePojo();
				Query ImageFileObj=session.createQuery("from ImageFile where uid=:id");
				ImageFileObj.setParameter("id",friendRequest.getSenderId());
				List<ImageFile> listOfImages=ImageFileObj.list();	
				for (ImageFile imageFile : listOfImages) {
					if(imageFile.getIsProfile().equalsIgnoreCase("profile"))
					{
						searchResponsePojo.setImagePojo(imageFile);
					}
				}
				
				Query query1=session.createQuery("from UserDetails where registerId=:id");
				query1.setParameter("id",friendRequest.getSenderId());	
				List<UserDetails> listOfUserDetails=query1.list();
				searchResponsePojo.setUserObject(listOfUserDetails.get(0));
				listOfSearchResponse.add(searchResponsePojo);	
			}
			else{
				searchResponsePojo=new SearchResponsePojo();
				Query ImageFileObj=session.createQuery("from ImageFile where uid=:id");
				ImageFileObj.setParameter("id",friendRequest.getRecieverId());
				List<ImageFile> listOfImages=ImageFileObj.list();	
				for (ImageFile imageFile : listOfImages) {
					if(imageFile.getIsProfile().equalsIgnoreCase("profile"))
					{
						searchResponsePojo.setImagePojo(imageFile);
					}
				}
				
				Query query1=session.createQuery("from UserDetails where registerId=:id");
				query1.setParameter("id",friendRequest.getRecieverId());	
				List<UserDetails> listOfUserDetails=query1.list();
				searchResponsePojo.setUserObject(listOfUserDetails.get(0));
				listOfSearchResponse.add(searchResponsePojo);	
			}
			
		}
		 
		 return listOfSearchResponse;
		 
	}
	
	

}
