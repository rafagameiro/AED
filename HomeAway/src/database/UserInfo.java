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
public interface UserInfo extends Serializable {
	
	/**
	 * 
	 * @return user's idUser
	 */
	String getIdUser();
	
	/**
	 * 
	 * @return user's name
	 */
	String getName();
	
	/**
	 * 
	 * @return user's nationality
	 */
	String getNationality();
	
	/**
	 * 
	 * @return user's address
	 */
	String getAddress();
	
	/**
	 * 
	 * @return user's email
	 */
	String getEmail();
	
	/**
	 * 
	 * @return user's phone number
	 */
	String getPhoneNumber();

}
