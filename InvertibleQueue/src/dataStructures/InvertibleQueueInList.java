/**
 * 
 */
package dataStructures;

/**
 * @author Rafael Gameiro
 *
 */
public class InvertibleQueueInList<E> extends QueueInList<E> implements InvertibleQueue<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	
	boolean inverted;
	
	public InvertibleQueueInList(){
		super();
		inverted = false;
	}


	@Override
	public void invert() {
		// TODO Auto-generated method stub
		inverted = !inverted;
	}
	
	@Override
	public void enqueue(E element) {
		// TODO Auto-generated method stub
		if(!inverted)
			list.addLast(element);
		else
			list.addFirst(element);
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if(super.isEmpty())
			throw new EmptyQueueException();
		
		if(!inverted)
			return list.removeFirst();
		else
			return list.removeLast();
	}

}
