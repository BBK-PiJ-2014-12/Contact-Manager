import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ContactTest {
	
	@Before
	public void buildUp() {
		Contact Pete = new ContactImpl("Pete Jones", "Marketing manager");
		Contact Tom = new ContactImpl("Tom Hanks", "Actor"); 
		Contact Mary = new ContactImpl("Mary");
	}
	@Test
	public void addNotes() {
		String input = "Head of quality management";
		Mary.addNotes(input);
		String output = Mary.getNotes();
		assertEquals(input, output);		
	}
	
	@Test
	public void getName() {
		String output = Tom.getName();
		String expected = "Tom Hanks";
		assertEquals(output, expected);
	}
	
	@Test
	public void getID() {
		int output = Mary.getID();
		int expected = 3;
		assertEquals(output, expected); 
	}
	
	public void getNotes() {
		String output = Pete.getNotes();
		String expected = "Marketing manager"; 
		assertEquals(output, expected);
	}
}
