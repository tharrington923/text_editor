package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		// Here  I have chosen to use sentinel nodes so two nulls need to be created
		this.head = new LLNode<E>(null);
		this.tail = new LLNode<E>(null);
		this.size = 0;
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		// Null pointers cannot be added to the list, if the user tries a NullPointerException is thrown
		/**if (element == null){
			throw new NullPointerException("Cannot add null to the list");
		}
		LLNode<E> newLLNode = new LLNode<E>(element,this.tail.prev,this.tail);
		this.tail.prev.next = newLLNode;
		this.tail.prev = newLLNode;
		this.size++;
		*/
		this.add(this.size(),element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		// Indexing of list starts at 0
		// Throw error if index is negative or larger than length of list -1
		if (index < 0 || index >= this.size() ){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		LLNode<E> current = this.head;
		for (int i = 0; i<=index; i++) {
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		// First start off by checking to make sure the index is a valid index
		// Throw error if index is negative or larger than length of list -1
		if (index < 0 || index > this.size() ){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		// Null pointers cannot be added to the list, if the user tries a NullPointerException is thrown
		if (element == null){
			throw new NullPointerException("Cannot add null to the list");
		}
		// Find the element at position index - 1
		LLNode<E> left = this.head;
		for(int i = 0; i< index; i++){
			left = left.next;
		}
		// Now we want to add a new node and set its next, prev, data fields correctly
		LLNode<E> newNode = new LLNode<E>(element,left,left.next);
		left.next.prev = newNode;
		left.next = newNode;
		this.size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index < 0 || index >= this.size() ){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		LLNode<E> delNode =this.head;
		for(int i = 0; i<= index; i++){
			delNode = delNode.next;
		}
		delNode.next.prev = delNode.prev;
		delNode.prev.next = delNode.next;
		this.size--;
		return delNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (index < 0 || index >= this.size() ){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		// Null pointers cannot be added to the list, if the user tries a NullPointerException is thrown
		if (element == null){
			throw new NullPointerException("Cannot add null to the list");
		}
		LLNode<E> node =this.head;
		for(int i = 0; i<= index; i++){
			node = node.next;
		}
		E temp = node.data;
		node.data = element;
		return temp;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	// TODO: Added my own constructor for a constructing a LLNode with prev and next nodes
	public LLNode(E e, LLNode<E> prevLLNode, LLNode<E> nextLLNode) {
		this.data = e;
		this.prev = prevLLNode;
		this.next = nextLLNode;
	}

}
