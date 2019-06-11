/**
 * 
 */
package database;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class UserNotOwnerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UserNotOwnerException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public UserNotOwnerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
