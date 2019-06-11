/**
 * 
 */
package dataStructures;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class EntryClass<K, V> implements Entry<K, V> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private K key;
	
	private V value;
	
	public EntryClass(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return value;
	}

}
