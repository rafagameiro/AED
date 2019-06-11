/**
 * 
 */
package dataStructures;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class IteratorWFilter<K, V> implements Iterator<V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Iterator<Entry<K, V>> BSTIterator;
	
	public IteratorWFilter(Iterator<Entry<K, V>> treeIterator) {
		BSTIterator = treeIterator;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return BSTIterator.hasNext();
	}

	@Override
	public V next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return BSTIterator.next().getValue();
	}

	@Override
	public void rewind() {
		// TODO Auto-generated method stub
		BSTIterator.rewind();
	}
	
	

}
