/**
 * 
 */
package database;

import dataStructures.Iterator;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
interface User extends UserInfo {
	
	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;
	
    /**
     * User adds a new property, to properties
	 * Increments by one, the number of properties the user has
	 * User becomes the owner of this property
     * 
     * @param property new property of the user
     */
	void addProperty(Property property);
	
	/**
	 * Adds a visit to a property by the user
	 * Increments by one, the number of visits the user had
	 * 
	 * @param stay new property where the user stayed
	 */
	void addStay(Property stay);
	
	/**
	 * User removes a property
	 * Decrements by one, the number of properties the user has
	 * User is no longer the owner of the property
	 * 
	 * @param idHome identifier of user's property
	 */
	void removeProperty(String idHome);
	
	/**
	 * Updates user information
	 * 
	 * @param mail user's new email
	 * @param phoneNumber user's new phone number
	 * @param address user's address
	 */
	void updateProfile(String mail, String phoneNumber, String address);
	
	/**
	 * 
	 * @return number of properties the user has
	 */
	int getNumOfProperties();
	
	/**
	 * 
	 * @return true if the user has already visited some property, otherwise false
	 */
	boolean isTraveller();
	
	/**
	 * 
	 * @return true if the user has already any property, otherwise false
	 */
	boolean isOwner();
	
	/**
	 * 
	 * @return all the properties the user has
	 */
	Iterator<PropertyInfo> getProperties();
	
	/**
	 * 
	 * @return all the stays the user had
	 */
	Iterator<PropertyInfo> getStays();

}
