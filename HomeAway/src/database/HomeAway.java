/**
 * 
 */
package database;

import java.io.Serializable;

import dataStructures.Iterator;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public interface HomeAway extends Serializable {
	
	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;
	
    /**
     * Adds a User to the system
     * 
     * @param idUser user inserted identifier
     * @param email user's inserted email
     * @param phoneNumber user's inserted phone number
     * @param name user's inserted name
     * @param nationality user's inserted nationality
     * @param address user's inserted address
     * @throws UserAlreadyExistsException if the there is a user with the idUser inserted
     */
	void addUser(String idUser, String email, String phoneNumber, String name, String nationality, String address) throws UserAlreadyExistsException;
	
	/**
	 * Updates info of a user
	 * 
	 * @param idUser identify user in the system
	 * @param mail user's new email
	 * @param phoneNumber user's new phone number
	 * @param address user's new address
	 * @throws InexistentUserException if there is not a user with the typed idUser
	 */
	void updateUserInfo(String idUser, String mail, String phoneNumber, String address) throws InexistentUserException;
	
	/**
	 * Removes the user from the system
	 * 
	 * @param idUser user's identifier
	 * @throws InexistentUserException if there is not a user with the typed idUser
	 * @throws UserHasPropertiesException if the user has properties added to the system
	 */
	void removeUser(String idUser) throws InexistentUserException, UserHasPropertiesException;
	
	/**
	 * 
	 * @param idUser user's identifier
	 * @return returns the user's profile
	 * @throws InexistentUserException if there is not a user with the typed idUser
	 */
	UserInfo getUserInfo(String idUser) throws InexistentUserException;
	
	/**
	 * Adds a User's property to renting 
	 * 
	 * @param idHome property's inserted identifier
	 * @param idUser user's identifier
	 * @param price price per day
	 * @param capacity max number of people, the property can hold
	 * @param location property's location
	 * @param description property's inserted description
	 * @param address property's address
	 * @throws InvalidDataException if the capacity is exceeded or negative
	 * @throws InexistentUserException if there is not a user with the typed idUser
	 * @throws PropertyAlreadyExistsException if there is already a property with the same idHome
	 */
	void addProperty(String idHome, String idUser, int price, int capacity, String location, String description, String address) 
			throws InvalidDataException, InexistentUserException, PropertyAlreadyExistsException;
	
	/**
	 * Removes a User's property from the system
	 * 
	 * @param idHome property's identifier
	 * @throws InexistentPropertyException if there is not a property with the typed idHome
	 * @throws AlreadyVisitedException if the property was already visited
	 */
	void removeProperty(String idHome) throws InexistentPropertyException, AlreadyVisitedException;
	
	/**
	 * 
	 * @param idHome property's identifier
	 * @return the property's information
	 * @throws InexistentPropertyException if there is not a property with the typed idHome
	 */
	PropertyInfo getPropertyInfo(String idHome) throws InexistentPropertyException;
	
	/**
	 * Add owner's stay in the corresponding IdHome property. 
	 * Marks this property has visited.
	 * 
	 * @param idHome property's identifier
	 * @param idUser user's identifier
	 * @throws InexistentUserException if there is not a user with the typed idUser
	 * @throws InexistentPropertyException if there is not a property with the typed idHome
	 * @throws OwnerIsntTravellerException if user is not the owner and is not the owner of the property
	 */
	void ownerStaying(String idHome, String idUser) throws InexistentUserException, InexistentPropertyException, OwnerIsntTravellerException;
	
	/**
	 * Add traveler's stay in the corresponding property. 
	 * The points are added to the other points, gave from other users, to this property. 
	 * Marks this property has visited.
	 * 
	 * @param idHome property's identifier
	 * @param idUser user's identifier
	 * @param points classification given by the user to the property
	 * @throws InvalidDataException if the given points are superior to the limit, or if they are negative
	 * @throws InexistentUserException if there is not a user with the typed idUser
	 * @throws InexistentPropertyException if there is not a property with the typed idHome
	 * @throws BothTravellerAndOwnerException if the user is the owner of the property
	 */
	void travellerStaying(String idHome, String idUser, int points) throws InvalidDataException, InexistentUserException, InexistentPropertyException, BothTravellerAndOwnerException;
	
	/**
	 * 
	 * @param idUser user's identifier
	 * @return user's list of properties with their information
	 * @throws InexistentUserException if there is not a user with the typed idUser
	 * @throws UserNotOwnerException if the user does not have properties
	 */
	Iterator<PropertyInfo> showUserProperties(String idUser) throws InexistentUserException, UserNotOwnerException;
	
	/**
	 * 
	 * @param idUser
	 * @return the properties where the user stayed
	 * @throws InexistentUserException if there is not a user with the typed idUser
	 * @throws UserDidntTravelledException if the user did not travelled
	 */
	Iterator<PropertyInfo> showUserStays(String idUser) throws InexistentUserException, UserDidntTravelledException;
	
	/**
	 * 
	 * @param capacity max number of people the property can hold
	 * @param location property's location
	 * @return properties that match the criteria
	 * @throws InvalidDataException if the capacity is exceeded or it is negative
	 * @throws NoResultsException if there are not properties that match the criteria
	 */
	Iterator<PropertyInfo> searchProperties(int capacity, String location) throws InvalidDataException, NoResultsException;
	
	/**
	 * 
	 * @param location property's location
	 * @return properties with the best ratings
	 * @throws NoResultsException if there are not properties that match the criteria
	 */
	Iterator<PropertyInfo> showBestProperties(String location) throws NoResultsException;

}
