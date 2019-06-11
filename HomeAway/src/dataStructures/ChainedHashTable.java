package dataStructures;  

/**
 * Chained Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class ChainedHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty chained hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public ChainedHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ ) {
            table[i] = new OrderedDoubleList<K,V>();
        }    
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public ChainedHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }

    @Override
    public V insert( K key, V value )
    {
        if ( this.isFull() )
        	this.rehash();
      
            
        	V oldValue = null;
        	if(table[hash(key)].isEmpty()) {
        		table[hash(key)].insert(key, value);
        	}else {
        		if(table[hash(key)].find(key) != null) 
        			oldValue = table[hash(key)].find(key);
        		table[hash(key)].insert(key, value);
        	}       
        	if(oldValue == null)
        		currentSize++;
        	return oldValue;
    }

    private void rehash() {
		// TODO Auto-generated method stub
		int capacity = table.length * 2;
		int newArraySize = HashTable.nextPrime((int) (1.1 * capacity));
		@SuppressWarnings("unchecked")
		Dictionary<K,V>[] newtable = (Dictionary<K,V>[]) new Dictionary[newArraySize];
		for ( int i = 0; i < newArraySize; i++ ) {
            newtable[i] = new OrderedDoubleList<K,V>();
        } 
		for(int i = 0; i < table.length; i++) {
			OrderedDoubleList<K,V> list = (OrderedDoubleList<K, V>) table[i]; 
			Iterator<Entry<K,V>> it = list.iterator();
			while(it.hasNext()) {
				Entry<K,V> entry = it.next();
				int hash = Math.abs( entry.getKey().hashCode() ) % newtable.length;
				newtable[hash].insert(entry.getKey(), entry.getValue());
			}
				
		}
		table = newtable;
		maxSize = newArraySize;
		
	}


	@Override
    public V remove( K key )
    {
		if(find(key) == null)
			return null;
		else {
			currentSize--;
			return table[hash(key)].remove(key);
		}	
    }

	@Override
    public Iterator<Entry<K,V>> iterator( )
    {
        return new ChainedHTIterator<K,V>(table);
    } 
}
































