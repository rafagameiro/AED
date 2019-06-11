/**
 * 
 */
package dataStructures;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class ArrayBSTIterator<K extends Comparable<K>,V> implements Iterator<V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int current;
	private Iterator<Entry<K,V>> currentIt;
	private OrderedDictionary<K,V>[] array;

	/**
	 * 
	 */
	public ArrayBSTIterator(int beginning, OrderedDictionary<K,V>[] array) {
		// TODO Auto-generated constructor stub
		this.array = array;
		current = beginning;
		rewind();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return currentIt.hasNext();
	}

	@Override
	public V next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(!hasNext())
			throw new NoSuchElementException();
		Entry<K,V> entry = currentIt.next();
		searchNext();
		return entry.getValue();
	}

	@Override
	public void rewind() {
		// TODO Auto-generated method stub
		if(array[current] != null) {
			currentIt = array[current].iterator();
			searchNext();
		}else {
			current ++;
			if(current < array.length)
				rewind();
		}	
	}

	private void searchNext() {
		// TODO Auto-generated method stub
		if(!hasNext()) {
			current++;
			if(current < array.length)
				rewind();
		}	
	}

}
