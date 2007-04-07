package simple.xml.load;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;
import simple.xml.Attribute;
import simple.xml.ElementList;
import simple.xml.Root;

public class FieldScannerTest extends TestCase {
   
   @Root(name="name")
   public static class Example {

      @ElementList(name="list", type=Entry.class)
      private Collection<Entry> list;
      
      @Attribute(name="version")
      private int version;
      
      @Attribute(name="name")
      private String name;
   }
   
   @Root(name="entry")
   public static class Entry {
      
      @Attribute(name="text")
      public String text;
   }
   
   public void testExample() throws Exception {
      FieldScanner scanner = new FieldScanner(Example.class);
      ArrayList<Class> list = new ArrayList<Class>();
      
      for(Contact contact : scanner.getContacts()) {
         list.add(contact.getType());
      }
      assertEquals(scanner.getContacts().size(), 3);
      assertTrue(list.contains(Collection.class));
      assertTrue(list.contains(String.class));
      assertTrue(list.contains(int.class));
   }
}
