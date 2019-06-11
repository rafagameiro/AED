/**
 * 
 */
package dataStructures;

/**
 * @author Rafael Gameiro
 *
 */
public interface InvertibleQueue<E> extends Queue<E> {
	
	//Puts all elements in the queue in the opposite order.
	void invert();

}
