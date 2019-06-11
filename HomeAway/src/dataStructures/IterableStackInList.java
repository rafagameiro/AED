/**
 * 
 */
package dataStructures;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class IterableStackInList<E> extends StackInList<E> implements IterableStack<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public IterableStackInList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return super.list.iterator();
	}

}
