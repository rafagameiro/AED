/**
 * 
 */
package dataStructures;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public interface IterableStack<E> extends Stack<E> {


	public Iterator<E> iterator();

}
