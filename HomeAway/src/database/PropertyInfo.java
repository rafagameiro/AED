/**
 * 
 */
package database;

import java.io.Serializable;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public interface PropertyInfo extends Serializable {
	
	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;
	
    /**
     * 
     * @return user's information
     */
	UserInfo getOwner();
	
	/**
	 * 
	 * @return property's idHome
	 */
	String getIdHome();
	
	/**
	 * 
	 * @return property's description
	 */
	String getDescription();
	
	/**
	 * 
	 * @return property's address
	 */
	String getAddress();
	
	/**
	 * 
	 * @return property's location
	 */
	String getLocation();
	
	/**
	 * 
	 * @return property's daily price
	 */
	int getDailyPrice();
	
	/**
	 * 
	 * @return property's max number the can stay in the property
	 */
	int getCapacity();
	
	/**
	 * 
	 * @return property's total given points
	 */
	int getPoints();

}
