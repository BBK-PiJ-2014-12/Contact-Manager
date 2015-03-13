import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ContactTest {
	Contact Pete; 
	Contact Tom;
	Contact Mary;
	
	@Before
	public void buildUp() {
		Pete = new ContactImpl("Pete Jones", "Marketing manager");
		Tom = new ContactImpl("Tom Hanks", "Actor"); 
		Mary = new ContactImpl("Mary");
	}
	@Test
	public void addNotes() {
		String input = "Head of quality management";
		Mary.addNotes(input);
		String output = Mary.getNotes();
		assertEquals(input, output);	
		
		String input2 = "Based at the Head Office";
		Pete.addNotes(input2);
		String output2 = Pete.getNotes();
		String expected = "Marketing manager. Based at the Head Office";
		assertEquals(output2, expected);
	}
	
	@Test
	public void getName() {
		String output = Tom.getName();
		String expected = "Tom Hanks";
		assertEquals(output, expected);
	}
	
	@Test
	public void getId() {
		int output = Mary.getId();
		int expected = 3;
		assertEquals(output, expected); 
	}
	
	@Test
	public void getNotes() {
		String output = Pete.getNotes();
		String expected = "Marketing manager"; 
		assertEquals(output, expected);
	}
}
