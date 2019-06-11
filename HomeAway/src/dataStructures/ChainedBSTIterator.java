/**
 * 
 */
package dataStructures;


/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class ChainedBSTIterator<K1 extends Comparable<K1> ,K2 extends Comparable<K2>, V> implements Iterator<V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Iterator<Entry<K2,V>> currentIt;
	private Iterator<Entry<K1, OrderedDictionary<K2, V>>> tree;

	public ChainedBSTIterator(OrderedDictionary<K1, OrderedDictionary<K2, V>> tree) {
		// TODO Auto-generated constructor stub
		this.tree = tree.iterator();
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
		Entry<K2,V> entry = currentIt.next();
		searchNext();
		return entry.getValue();
	}

	@Override
	public void rewind() {
		// TODO Auto-generated method stub
		currentIt = tree.next().getValue().iterator();
		searchNext();
	}
	
	private void searchNext() {
		// TODO Auto-generated method stub
		if(!hasNext()) {
			if(tree.hasNext())
				rewind();
		}	
	}

}
