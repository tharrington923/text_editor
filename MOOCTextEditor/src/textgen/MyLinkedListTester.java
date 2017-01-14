/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> list2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		list2 = new MyLinkedList<Integer>();
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		try {
			list1.remove(-1);
			fail("Remove: check for out of bounds ");
		}
		catch(IndexOutOfBoundsException e){
		}
		try {
			list1.remove(100);
			fail("Remove: check for out of bounds ");
		}
		catch(IndexOutOfBoundsException e){
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		// Now 5 is the only element in the list
		assertEquals("Add: check size is correct ", 0, list2.size());
		list2.add(5);
		//test adding null element, get should throw an exception
		try {
			emptyList.add(null);
			fail("Check adding null element");
		}
		catch (NullPointerException e) {		
		}
		assertEquals("Add: check size is correct ", 1, list2.size());
		assertEquals("Add to end: check new element is correct", (Integer)5, list2.get(0));
		assertEquals("Add to end: check previous node is linked correctly to new node",list2.head.next.data, (Integer)5);
		assertEquals("Add to end: check new element is linked correctly to next node",list2.head.next.next,list2.tail);
		assertEquals("Add to end: check tail previous is linked correctly to new node",(Integer)5, list2.tail.prev.data);
		assertEquals("Add to end: check new element's previous is linked correctly",list2.tail.prev.prev, list2.head);
		list2.add(10);
		assertEquals("Add: check size is correct ", 2, list2.size());
		assertEquals("Add to end: check new element is correct", (Integer)10, list2.get(1));
		assertEquals("Add to end: check previous node is linked correctly to new node",list2.head.next.next.data, (Integer)10);
		assertEquals("Add to end: check new element is linked correctly to next node",list2.head.next.next.next,list2.tail);
		assertEquals("Add to end: check tail previous is linked correctly to new node",(Integer)10, list2.tail.prev.data);
		assertEquals("Add to end: check new element's previous is linked correctly",list2.tail.prev.prev, list2.head.next);
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Size: Test empty list size",emptyList.size(),0);
		assertEquals("Size: Test empty list size",longerList.size(),10);
		emptyList.add(29);
		assertEquals("Size: Test empty list size",emptyList.size(),1);
		emptyList.remove(0);
		assertEquals("Size: Test empty list size",emptyList.size(),0);
		emptyList.add(0,29);
		emptyList.add(1,39);
		assertEquals("Size: Test empty list size after adds",emptyList.size(),2);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		//test adding null element, get should throw an exception
		try {
			emptyList.add(0,null);
			fail("Check adding null element");
		}
		catch (NullPointerException e){
		}
		//test adding an element at index that doesn't exist
		try {
			emptyList.add(5,20);
			fail("Check for out of bounds");
		}
		catch(IndexOutOfBoundsException e) {
		}
		try {
			emptyList.add(-10,20);
			fail("Check for out of bounds");
		}
		catch(IndexOutOfBoundsException e) {
		}
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		//Try to set value out of range
		try {
			emptyList.set(50,20);
			fail("Check for out of bounds");
		}
		catch(IndexOutOfBoundsException e) {
		}
		try {
			emptyList.set(-10,20);
			fail("Check for out of bounds");
		}
		catch(IndexOutOfBoundsException e) {
		}
		try {
			emptyList.set(0,null);
			fail("Check adding null element");
		}
		catch(NullPointerException e) {
		}
	}
	
	
	// TODO: Optionally add more test methods.
	
}
