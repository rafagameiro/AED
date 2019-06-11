package dataStructures;

public class BSTKeyOrderIterator<K,V> implements Iterator<Entry<K, V>> {

		
	private static final long serialVersionUID = 1L;
	
	protected BSTNode<K,V> root;
	
	protected Stack<BSTNode<K,V>> s;

	public BSTKeyOrderIterator(BSTNode<K,V>root){
		this.root = root;
		rewind();
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return !s.isEmpty();
	}
	
	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(!this.hasNext())
			throw new NoSuchElementException();
		 BSTNode<K,V> node = s.pop();
		 if(node.getRight() != null)
			 pushElements(node.getRight());
		 return node.getEntry();	
	}
	
	@Override
	public void rewind() {
		// TODO Auto-generated method stub
		s = new StackInList<BSTNode<K,V>>();
		pushElements(root);
	}	
	
	private void pushElements(BSTNode<K,V> root) {
		BSTNode<K,V> node = root;
		// TODO Auto-generated method stub
		while(node != null) {
			s.push(node);
			node = node.getLeft();
		}
	}
	
	
	
}
