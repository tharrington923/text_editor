package spelling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		String lcWord = word.toLowerCase();
		TrieNode currNode = root;
		TrieNode childNode;
		/**
		for(int i = 0; i< lcWord.length(); i++){
			// Find the TrieNode that contains the letter word(i)
			Character currLetter = lcWord.charAt(i);
			childNode = currNode.getChild(currLetter);
			if(childNode == null){
				// If the letter doesn't exit, add it and move to the new node
				currNode = currNode.insert(currLetter);
			}
			else { // The letter exists, set the current node to the child node
				currNode = childNode;
			}
		}*/
		for(Character currLetter : lcWord.toCharArray()){
			// Find the TrieNode that contains the letter word(i)
			childNode = currNode.getChild(currLetter);
			if(childNode == null){
				// If the letter doesn't exit, add it and move to the new node
				currNode = currNode.insert(currLetter);
			}
			else { // The letter exists, set the current node to the child node
				currNode = childNode;
			}
		}
		// Now we have made it down to the end. We need to check if this word ends a node.
		// If it does, we don't add this word.
		if (!currNode.endsWord()) { // Doesn't end a word
			currNode.setEndsWord(true); // This node now ends a word
			size++; // Increase the size since a word was added.
			currNode.setText(lcWord); // Set the text of the word
			return true;
		}
		else{ // Already ends a word, so it exists in the Trie
			return false;
		}
	    
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		String lcWord = s.toLowerCase();
		TrieNode currNode = root;
		TrieNode childNode;
		for(Character currLetter : lcWord.toCharArray()){
			// Find the TrieNode that contains the letter word(i)
			childNode = currNode.getChild(currLetter);
			if(childNode == null){
				// If the letter doesn't exit, this word isn't in dictionary
				return false;
			}
			else { // The letter exists, set the current node to the child node
				currNode = childNode;
			}
		}
		// Now we have made it down to the end. We need to check if this word ends a node.
		if (currNode.endsWord()) { // Ends a word
			return true;
		}
		else{ // Doesn't ends a word, so it exists in the Trie
			return false;
		}
	}
	
	public TrieNode stemNode(String word)
	{
		String lcWord = word.toLowerCase();
		TrieNode currNode = root;
		TrieNode childNode;
		for(Character currLetter : lcWord.toCharArray()){
			childNode = currNode.getChild(currLetter);
			if(childNode == null){
				return null;
			}
			else { // The letter exists, set the current node to the child node
				currNode = childNode;
			}
		}
		return currNode;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 List<String> listCompletions = new ArrayList<String>();
    	 TrieNode curr = stemNode(prefix);
    	 if(curr == null) {
    		 return Collections.<String> emptyList();
    	 }
    	 if(curr.endsWord()){
    		 // If the current node ends a word
    		 listCompletions.add(curr.getText());
    		 numCompletions--;
    	 }
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 for (Character c : curr.getValidNextCharacters()){
    		 queue.add(curr.getChild(c));
    	 }
    	 // Started off initializing the queue
    	 while (numCompletions > 0 && !queue.isEmpty()){
    		 curr = queue.removeFirst();
    		 for (Character c : curr.getValidNextCharacters()){
        		 queue.add(curr.getChild(c));
        	 }
    		 if(curr.endsWord()){
    			 listCompletions.add(curr.getText());
        		 numCompletions--;
    		 }
    		 
    	 }
         return listCompletions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 		
}