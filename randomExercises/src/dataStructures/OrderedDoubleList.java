/**
 * 
 */
package dataStructures;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {
	
	private static final long serialVersionUID = 1L;
	
	//Variaveis head, tail e currentSize

	private DListNode<Entry<K,V>> head;
	
	private DListNode<Entry<K,V>> tail;
	
	private int currentSize;
	
	public OrderedDoubleList() {
		head = null;
		tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return currentSize == 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return currentSize;
	}

	@Override
	public V find(K key) {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		else {
			boolean found = false;
			int i = 0;
			DListNode<Entry<K,V>> current = head;
			while(i < this.size() && !found) {
				if(current.getElement().getKey().compareTo(key) == 0) {
					found = true;
					continue;
				}
				current = current.getNext();
				i++;
			}
			if(found)
				return current.getElement().getValue();
			else
				return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public V insert(K key, V value) {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			addFirst(key, value);
		else {
			if(head.getElement().getKey().compareTo(key) > 0)
				addFirst(key, value);
			else if(tail.getElement().getKey().compareTo(key) < 0)
				addLast(key, value);
			else {
				boolean done = false;
				DListNode<Entry<K,V>> current = head;
				while(!done) {
					DListNode<Entry<K,V>> temp = current.getNext();
					if(current.getElement().getKey().compareTo(key) == 0) {
						current.setElement(new EntryClass(key, value));
						done = true;
						continue;
					}else if((current.getElement().getKey().compareTo(key) < 0) && (temp.getElement().getKey().compareTo(key) > 0)) {
							DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(new EntryClass(key, value), current, temp);
							current.setNext(newNode);
							temp.setPrevious(newNode);
							currentSize++;
							done = true;
							continue;
					}
					current = current.getNext();
						
				}
			}
		}
		
		return value;
	}
	
	@SuppressWarnings("unchecked")
	protected void addFirst(K key, V value) {
		if(this.isEmpty()) {
			@SuppressWarnings({ "rawtypes" })
			DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(new EntryClass(key, value), null, null);
			head = newNode;
			tail = newNode;
		}else {
			@SuppressWarnings({ "rawtypes" })
			DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(new EntryClass(key, value), null, head);
			head.setPrevious(newNode);
			head = newNode;
		}
		currentSize++;
	}
	
	@SuppressWarnings("unchecked")
	protected void addLast(K key, V value) {
		@SuppressWarnings("rawtypes")
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(new EntryClass(key, value), tail, null);
		tail.setNext(newNode);
		tail = newNode;
		currentSize++;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		if(this.isEmpty() || head.getElement().getKey().compareTo(key) > 0 || tail.getElement().getKey().compareTo(key) < 0)
			return null;
		else {
			if(head.getElement().getKey().compareTo(key) == 0)
				 return removeFirst();
			else if(tail.getElement().getKey().compareTo(key) == 0)
				return removeLast();
			else {
				boolean done = false;
				V value = null;
				DListNode<Entry<K,V>> current = head;
				while(!done && !current.equals(tail)) {
					DListNode<Entry<K,V>> next = current.getNext();
					DListNode<Entry<K,V>> previous = current.getPrevious();
					if(current.getElement().getKey().compareTo(key) == 0) {
						value = current.getElement().getValue();
						previous.setNext(next);
						next.setPrevious(previous);
						currentSize--;
						done = true;
						continue;
					}
					current = current.getNext();
						
				}
				return value;
			}
			
		}
		
	}

	protected V removeLast() {
		// TODO Auto-generated method stub
		DListNode<Entry<K,V>> oldTail = tail;
		tail = tail.getPrevious();
		if(tail == null)
			head = null;
		else
			tail.setNext(null);
		currentSize--;
		return oldTail.getElement().getValue();
	}

	protected V removeFirst() {
		// TODO Auto-generated method stub
		DListNode<Entry<K,V>> oldHead = head;
		head = head.getNext();
		if(head == null)
			tail = null;
		else
			head.setPrevious(null);
		currentSize--;
		return oldHead.getElement().getValue();
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		// TODO Auto-generated method stub
		return new DoublyLLIterator<Entry<K,V>>(head, tail);
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		// TODO Auto-generated method stub
		if(currentSize == 0)
			throw new EmptyDictionaryException();
		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		// TODO Auto-generated method stub
		if(currentSize == 0)
			throw new EmptyDictionaryException();
		return tail.getElement();
	}

}
