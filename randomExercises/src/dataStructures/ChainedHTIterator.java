/**
 * 
 */
package dataStructures;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class ChainedHTIterator<K,V> implements Iterator<Entry<K,V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int current;
	private Iterator<Entry<K,V>> currentIt;
	private Dictionary<K ,V>[] table;
	
	public ChainedHTIterator(Dictionary<K, V>[] table2) {
		this.table = table2;
		current = 0;
		rewind();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return currentIt.hasNext();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(!hasNext())
			throw new NoSuchElementException();
		Entry<K,V> entry = currentIt.next();
		searchNext();
		return entry;
	}

	@Override
	public void rewind() {
		// TODO Auto-generated method stub
		currentIt = table[current].iterator();
		searchNext();
	}
	
	private void searchNext() {
		if(!hasNext()) {
			current++;
			if(current < table.length)
				rewind();
		}	
					
	}

}
