/**
 * 
 */
package database;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
interface Property extends PropertyInfo {
	
	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;
    
    RatingClass getRating();
	
    /**
     * changes the value in visited to true
     */
	void setVisit();
	
	/**
	 * Add the points given by the traveler to points the property had 
	 * Replace that value in the RatingClass, rating
	 * 
	 * @param points classification given by the user
	 */
	void setPoints(int points);
	
	/**
	 * 
	 * @return true, if the property was visited, otherwise false
	 */
	boolean wasVisited();

}
