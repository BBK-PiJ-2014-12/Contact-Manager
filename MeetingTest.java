import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class MeetingTest {
	Calendar date;
	SimpleDateFormat df;
	Contact Pete;
	Contact Tom;
	Contact Mary;
	Set<Contact> contacts = new HashSet<Contact>();;
	String notes;
	Meeting m1;
	
	@Before
	public void buildUp() {
		Pete = new ContactImpl("Pete Jones", "Marketing manager");
		Tom = new ContactImpl("Tom Hanks", "Actor"); 
		Mary = new ContactImpl("Mary", "Director");
		contacts.add(Pete);
		contacts.add(Tom);
		contacts.add(Mary);
		
		df = new SimpleDateFormat("yyyy.MMMMM.dd HH:mm");
		date = Calendar.getInstance();
		date.set(2015, 5, 9, 10, 00);
		
		notes = "PiJ exam";
		
		m1 = new PastMeetingImpl(contacts, date, notes);
	}
	
	@Test
	public void getDate() {
		String output = df.format(m1.getDate().getTime());
		String expected = "2015.June.09 10:00";
		assertEquals(output, expected);
	}
	
	//Passed testing when the other tests are commented out and only the correct number or objects were created.
	@Test
	public void getId() {
		int output = m1.getId();
		int expected = 1;
		assertEquals(output, expected);
	}
	
	@Test
	public void getContacts() {
		Set<Contact> output = m1.getContacts();
		Set<Contact> expected = contacts;
		assertEquals(output, expected);
	}
}
