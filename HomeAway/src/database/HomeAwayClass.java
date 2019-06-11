/**
 * 
 */
package database;

import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;
import dataStructures.Iterator;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class HomeAwayClass implements HomeAway {
	
	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;
	
    /**
     * User signed in the system
     */
	private Dictionary<String, User> users;
	
	/**
	 * User's property registed in the system
	 */
	private Dictionary<String, Property> properties;
	
	/**
	 * User's property registed in the system, ordered by property
	 */
	private Dictionary<String, Locations> propertiesByLocation;
	
	
	public HomeAwayClass(){
		users = new ChainedHashTable<String,User>();
		properties = new ChainedHashTable<String,Property>();
		propertiesByLocation = new ChainedHashTable<String, Locations>();
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#addUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addUser(String idUser, String email, String phoneNumber, String name, String nationality,
			String address) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		User user = users.find(idUser.toUpperCase());
		if(user != null)
			throw new UserAlreadyExistsException();
		users.insert(idUser.toUpperCase(), new UserClass(idUser, email, phoneNumber, name, nationality, address));
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#updateUserInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateUserInfo(String idUser, String mail, String phoneNumber, String address)
			throws InexistentUserException {
		// TODO Auto-generated method stub
		User user = users.find(idUser.toUpperCase());
		if(user == null){
			throw new InexistentUserException();
		}else
			user.updateProfile(mail, phoneNumber, address);
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#removeUser(java.lang.String)
	 */
	@Override
	public void removeUser(String idUser) throws InexistentUserException, UserHasPropertiesException {
		// TODO Auto-generated method stub
		User user = users.find(idUser.toUpperCase());
		if(user == null){
			throw new InexistentUserException();
		}else if(user.getNumOfProperties() > 0)
			throw new UserHasPropertiesException();
		else{
			users.remove(idUser.toUpperCase());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#getUserInfo(java.lang.String)
	 */
	@Override
	public UserInfo getUserInfo(String idUser) throws InexistentUserException {
		// TODO Auto-generated method stub
		User user = users.find(idUser.toUpperCase());
		if(user == null)
			throw new InexistentUserException();
		
		return user;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#addProperty(java.lang.String, java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addProperty(String idHome, String idUser, int price, int capacity, String location, String description,
			String address) throws InvalidDataException, InexistentUserException, PropertyAlreadyExistsException {
		// TODO Auto-generated method stub
		User user = users.find(idUser.toUpperCase());
		Property property = properties.find(idHome.toUpperCase());
		if(0 > price ||( 0 >= capacity || capacity > 20)){
			throw new InvalidDataException();
		}else if(user == null){
			throw new InexistentUserException();
		}else if(property != null){
			throw new PropertyAlreadyExistsException();
		}else{	
			property = new PropertyClass(user, idHome, price, capacity, location, description, address);
			if(propertiesByLocation.find(location.toUpperCase()) == null)													
				propertiesByLocation.insert(location.toUpperCase(), new LocationsClass());		
			propertiesByLocation.find(location.toUpperCase()).insertPropertyCapacity(property);		
			propertiesByLocation.find(location.toUpperCase()).insertPropertyPoints(property);			
			properties.insert(idHome.toUpperCase(), property);												
			user.addProperty(property);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#removeProperty(java.lang.String)
	 */
	@Override
	public void removeProperty(String idHome) throws InexistentPropertyException, AlreadyVisitedException {
		// TODO Auto-generated method stub
		Property property = properties.find(idHome.toUpperCase());
		if(property == null){
			throw new InexistentPropertyException();
		}else if(property.wasVisited()){
			throw new AlreadyVisitedException();
		}else{
			User user = (User) property.getOwner();
			properties.remove(idHome.toUpperCase());
			propertiesByLocation.find(property.getLocation().toUpperCase()).removePropertyCapacity(property);	
			propertiesByLocation.find(property.getLocation().toUpperCase()).removePropertyPoints(property);	
			user.removeProperty(idHome);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#getPropertyInfo(java.lang.String)
	 */
	@Override
	public Property getPropertyInfo(String idHome) throws InexistentPropertyException {
		// TODO Auto-generated method stub
		Property property = properties.find(idHome.toUpperCase());
		if(property == null)
			throw new InexistentPropertyException();
		return property;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#travellerStaying(java.lang.String, java.lang.String, int)
	 */
	@Override
	public void travellerStaying(String idHome, String idUser, int points) throws InvalidDataException,
			InexistentUserException, InexistentPropertyException, BothTravellerAndOwnerException {
		// TODO Auto-generated method stub
		if(0 >= points || points > 20){
			throw new InvalidDataException();
		}else {
			User user = users.find(idUser.toUpperCase());
			Property property = properties.find(idHome.toUpperCase());
			if(user == null){
				throw new InexistentUserException();
			}else if(property == null){
				throw new InexistentPropertyException();
			}else if(user.isOwner() && user.equals(property.getOwner())){
				throw new BothTravellerAndOwnerException();
			}else{
				propertiesByLocation.find(property.getLocation().toUpperCase()).removePropertyPoints(property);
				user.addStay(property);
				property.setPoints(points);
				property.setVisit();
				propertiesByLocation.find(property.getLocation().toUpperCase()).insertPropertyPoints(property);
				
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#ownerStaying(java.lang.String, java.lang.String)
	 */
	@Override
	public void ownerStaying(String idHome, String idUser) throws InexistentUserException, InexistentPropertyException, OwnerIsntTravellerException {
		// TODO Auto-generated method stub
		User user = users.find(idUser.toUpperCase());
		Property property = properties.find(idHome.toUpperCase());
		
		if(user == null){
			throw new InexistentUserException();
		}else if(property == null){
			throw new InexistentPropertyException();
		}else if(!(user.isOwner() && user.equals(property.getOwner()))){
			throw new OwnerIsntTravellerException();
		}else {	
			user.addStay(property);
			property.setVisit();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#showUserProperties(java.lang.String)
	 */
	@Override
	public Iterator<PropertyInfo> showUserProperties(String idUser) throws InexistentUserException, UserNotOwnerException {
		// TODO Auto-generated method stub
		User user = users.find(idUser.toUpperCase());
		if(user == null){
			throw new InexistentUserException();
		}else if(!user.isOwner()){
			throw new UserNotOwnerException();
		}else
			return user.getProperties();
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#showUserStays(java.lang.String)
	 */
	@Override
	public Iterator<PropertyInfo> showUserStays(String idUser) throws InexistentUserException, UserDidntTravelledException {
		// TODO Auto-generated method stub
		User user = users.find(idUser.toUpperCase());
		if(user == null){
			throw new InexistentUserException();
		}else if(!user.isTraveller()){
			throw new UserDidntTravelledException();
		}else
			return user.getStays();
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#searchProperties(int, java.lang.String)
	 */
	@Override
	public Iterator<PropertyInfo> searchProperties(int capacity, String location) throws InvalidDataException, NoResultsException {
		// TODO Auto-generated method stub
		if( 0 >= capacity || capacity > 20)
			throw new InvalidDataException();
		else if(propertiesByLocation.find(location.toUpperCase()) == null || !propertiesByLocation.find(location.toUpperCase()).hasResultsForCapacity(capacity))
			throw new NoResultsException();
		else
			return propertiesByLocation.find(location.toUpperCase()).iteratorCapacity(capacity-1);
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.HomeAway#showBestProperties(java.lang.String)
	 */
	@Override
	public Iterator<PropertyInfo> showBestProperties(String location) throws NoResultsException {
		// TODO Auto-generated method stub
		if(propertiesByLocation.find(location.toUpperCase()) == null || !propertiesByLocation.find(location.toUpperCase()).hasResultsForPoints())
			throw new NoResultsException();
		else
			return propertiesByLocation.find(location.toUpperCase()).iteratorPoints();
	}

}
