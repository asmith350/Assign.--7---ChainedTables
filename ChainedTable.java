// File: ChainedTable.java from the package edu.colorado.collections
// This is an assignment for students to complete after reading Chapter 11 of
// "Data Structures and Other Objects Using Java" by Michael Main.

//package edu.colorado.collections;

/******************************************************************************
* A <CODE>ChainedTable</CODE> is a chained hash table.
* The implementation isn't given here since it is an assignment in a typical
* data structures class. In general,
* programs should  use java.util.HashTable
* rather than this ChainedTable.
*
* <b>Outline of Java Source Code for this class:</b>
*   <A HREF="../../../../edu/colorado/collections/ChainedTable.java">
*   http://www.cs.colorado.edu/~main/edu/colorado/collections/ChainedTable.java
*   </A>
*
* @author Michael Main 
*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
*
* @version Feb 10, 2016
******************************************************************************/
public class ChainedTable
{
   // Invariant of the ChainedTable class:
   //   1. An element with a given key is stored as part of the linked list at
   //      table[hash(key)].
   private ChainedHashNode[] table;

     
   /**
   * Initialize an empty ChainedTable with a specified table size.
   * @param tableSize
   *   the table size for this new chained hash table
   * <b>Precondition:</b>
   *   <CODE>tableSize \&gt; 0</CODE>
   * <b>Postcondition:</b>
   *   This ChainedTable is empty and has the specified table size.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the specified table size. 
   * @exception IllegalArgumentException
   *   Indicates that tableSize is not positive.
   **/   
   public ChainedTable(int tableSize)
   {
      if (tableSize <= 0)
	   throw new IllegalArgumentException("Table size must be positive.");
      // Allocate the table which is initially all null head references.
      table = new ChainedHashNode[tableSize];
   }
   
   
   /**
   * Determines whether a specified key is in this ChainedTable.
   * @param key
   *   the non-null key to look for
   * <b>Precondition:</b>
   *   <CODE>key</CODE> cannot be null.
   * @return
   *   <CODE>true</CODE> (if this ChainedTable contains an object with the specified 
   *   key); <CODE>false</CODE> otherwise. Note that <CODE>key.equals( )</CODE>
   *   is used to compare the <CODE>key</CODE> to the keys that are in the 
   *   ChainedTable.
   * @exception NullPointerException
   *   Indicates that <CODE>key</CODE> is null.
   **/
   public boolean containsKey(Object key)
   {
      // The following return statement should be replaced by the student's code:
	  int index  = hash(key);
	  boolean containing = false;
	  ChainedHashNode cursor;
	  ChainedHashNode head;
	  head = (ChainedHashNode)table[index];
	  //System.out.println("about to look for whether the given key contains");
	  for(cursor = head; cursor != null; cursor = cursor.link){
		 // System.out.println("---searching...");
		  if(cursor.key.equals(key)){
			  containing = true;
		  }
	  }
      return containing;
   }
   
   
   /** Retrieves an object for a specified key.
   * @param key
   *   the non-null key to look for
   * <b>Precondition:</b>
   *   <CODE>key</CODE> cannot be null.
   * @return
   *   a reference to the object with the specified <CODE>key</CODE> (if this 
   *   ChainedTable contains an such an object);  null otherwise. Note that 
   *   <CODE>key.equals( )</CODE> is used to compare the <CODE>key</CODE>
   *   to the keys that are in the ChainedTable.
   * @exception NullPointerException
   *   Indicates that <CODE>key</CODE> is null.
   **/
   public Object get(Object key)
   {
      // The following return statement should be replaced by the student's code:
	  ChainedHashNode cursor = null;
	  int index = hash(key);
	  
	  
	  if(table[index] != null){
		  cursor = (ChainedHashNode)table[index];
		  while(cursor != null){
			  if(cursor.key.equals(key)){
				  return cursor.element;
			  }
			  else{
				  cursor = cursor.link;
			  }
		  }
		  return null;
	  }
	  else{
		  return null;
	  }
	  
   }
   
   
   private int hash(Object key)
   // The return value is a valid index of the ChainedTable's table. The index is
   // calculated as the remainder when the absolute value of the key's
   // hash code is divided by the size of the ChainedTable's table.
   {
      return Math.abs(key.hashCode()) % table.length;
   }
   
   
   /**
   * Add a new element to this ChainedTable, using the specified key.
   * @param key
   *   the non-null key to use for the new element
   * @param element
   *   the new element that's being added to this ChainedTable
   * <b>Precondition:</b>
   *   Neither <CODE>key</CODE> nor <CODE>element</CODE> is null.
   * @return
   *   If this ChainedTable already has an object with the specified <CODE>key</CODE>,
   *   then that object is replaced by <CODE>element</CODE>, and the return 
   *   value is a reference to the replaced object. Otherwise, the new 
   *   <CODE>element</CODE> is added with the specified <CODE>key</CODE>
   *   and the return value is null.
   * @exception NullPointerException
   *   Indicates that <CODE>key</CODE> or <CODE>element</CODE> is null.   
   **/
   public Object put(Object key, Object element)
   {
	  ChainedHashNode cursor = null;
      Object answer = null;
	  int index;
	  
	  if(key == null || element == null){
		  throw new NullPointerException("Key or element is null");
	  }
	 
      // Student code should be placed here to set cursor so that it refers to
      // the node that already contains the specified key. If there is no such
      // node, then the student code should set cursor to null.
	  index = hash(key);
	  
	  if(table[index] != null){
			cursor = (ChainedHashNode)table[index];
			while(cursor != null){
				cursor = cursor.link;
			}
	  }
	  else{
		  cursor = null;
	  }

      if (cursor == null)
      {  // Add a new node at the front of the list of table[hash(key)].
	 int i = hash(key);
	 cursor = new ChainedHashNode( );
	 cursor.element = element;
	 cursor.key = key;
	 cursor.link = table[i];
	 table[i] = cursor;
      }
      else
      {  // The new element replaces an old node.
         answer = cursor.element;
         cursor.element = element;
      }
      return answer;
   }
      
   
   /**
   * Removes an object for a specified key.
   * @param key
   *   the non-null key to look for
   * <b>Precondition:</b>
   *   <CODE>key</CODE> cannot be null.
   * @return
   *   If an object was found with the specified <CODE>key</CODE>, then that
   *   object has been removed from this ChainedTable and a copy of the removed object
   *   is returned; otherwise, this ChainedTable is unchanged and the null reference
   *   is returned.  Note that 
   *   <CODE>key.equals( )</CODE> is used to compare the <CODE>key</CODE>
   *   to the keys that are in the ChainedTable.
   * @exception NullPointerException
   *   Indicates that <CODE>key</CODE> is null.
   **/
   public Object remove(Object key)
   {
      // The following return statement should be replaced by the student's code:
	  ChainedHashNode cursor = null;
	  ChainedHashNode prev_cursor = null;
	  
	  int index = hash(key);
	  if(table[index] != null){
		  prev_cursor = null;
		  cursor = (ChainedHashNode)table[index];
		  while(cursor != null){
			  if(cursor.key.equals(key)){
				  if(prev_cursor != null){
					  prev_cursor.link = cursor.link;
				  }
				  else{
					  table[index] = null; //first node
				  }
				  return cursor.element;
			  }
			  else{
				  prev_cursor = cursor;
				  cursor = cursor.link;
			  }
		  }
		  return null;
	  }
	  else{
		  return null;
	  }
      
   }
   
   public void show()
	{
		ChainedHashNode cursor = null;
		for(int i = 0; i < table.length; i++)
		{
			System.out.printf("\n %d:",i);
			if(table[i] != null)
			{
				cursor = (ChainedHashNode) table[i];
				while(cursor != null)
				{
					System.out.printf("\n\t %s\t%s\n", cursor.key.toString(), cursor.element.toString());
					cursor = cursor.link;
				}
			}
			else
			{
				System.out.print("");
			}
			//System.out.println("\n\nend");
		}
		
	}
        
}
           
class ChainedHashNode
{
   Object element;
   Object key;
   ChainedHashNode link;
}
