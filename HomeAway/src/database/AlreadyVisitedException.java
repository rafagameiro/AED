/**
 * 
 */
package database;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class AlreadyVisitedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AlreadyVisitedException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public AlreadyVisitedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
