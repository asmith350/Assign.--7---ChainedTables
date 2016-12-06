// File ChainedTableDemonstration.java
// This small demonstration program shows how to use the ChainedTable class

public class ChainedTableDemonstration
{
   public static void main(String[ ] args)
   {
      final int SIZE = 10;  // The size of the demonstration Table
      
      ChainedTable table = new ChainedTable(SIZE);
      table.put(43210, "Sally Adams, 845-123-4567");
      table.put(55555, "Cathy Baker, 255-765-4321");
      table.put(12345, "Lisa Cook, 255-123-4567");
      table.put(98765, "Jessica Green, 255-123-4567");
      table.put(22222, "Jerry Jefferson, 914-111-2222");
      table.put(21212, "Susan Rosenburg, 914-222-1111");
      table.put(33333, "John Silverburg, 845-462-2222");
      table.put(56789, "Frank Smith, 212-888-8888");
      table.put(11119, "Scott Walder, 212-999-9999");
      table.put(99999, "Peter Young, 212-000-0000");
      table.show();

      int key;
      ChainedHashNode cursor;

      key = 12345;
      if (table.containsKey(key))
          System.out.println(key + " is in the hash table.");
      else
          System.out.println(key + " is not in the hash table.");
            
      key = 12340;
      if (table.containsKey(key))
          System.out.println(key + " is in the hash table.");
      else
          System.out.println(key + " is not in the hash table.");
            
      key=56789;
      cursor = (ChainedHashNode)table.get(key);
      if (cursor == null)
          System.out.println(key + " is not in the hash table.");
      else
          System.out.println(key + " is in the hash table with data " 
                                 + "\"" + cursor.element + "\"");
          
      key=66666;
      cursor = (ChainedHashNode)table.get(key);
      if (cursor == null)
          System.out.println(key + " is not in the hash table.");
      else
          System.out.println(key + " is in the hash table with data " 
                                 + "\"" + cursor.element + "\"");
                    
      table.remove(21212);
      table.remove(12345);
      table.remove(56789);
      table.remove(00000);
      table.show();      
   }      
}
