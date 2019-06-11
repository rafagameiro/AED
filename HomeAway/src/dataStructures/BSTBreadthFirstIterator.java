package dataStructures;

public class BSTBreadthFirstIterator<K,V> implements Iterator<V> {
	
	private static final long serialVersionUID = 1L;
	
	protected BSTNode<K,V> root;
	
	protected Queue<BSTNode<K,V>> q;

	public BSTBreadthFirstIterator(BSTNode<K,V>root){
		this.root = root;
		rewind();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return !(q.isEmpty());
	}
	
	@Override
	public V next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(!this.hasNext())
			throw new NoSuchElementException();
		 BSTNode<K,V> node = q.dequeue();
		 if(node.getLeft() != null)
			 q.enqueue(node.getLeft());
		 if(node.getRight() != null)
			 q.enqueue(node.getRight());
		 return node.getEntry().getValue();		 
	}

	@Override
	public void rewind() {
		// TODO Auto-generated method stub
		q = new QueueInList<BSTNode<K,V>>();
		if(root != null)
			q.enqueue(root);
	}
}
