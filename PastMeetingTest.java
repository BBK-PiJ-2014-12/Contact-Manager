import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class PastMeetingTest {

	@Test
	public void test() {
		Contact Pete = new ContactImpl("Pete Jones", "Marketing manager");
		Contact Tom = new ContactImpl("Tom Hanks", "Actor"); 
		Contact Mary = new ContactImpl("Mary");
		
		Calendar focDate;	//id = 3
		PastMeeting foc;
		Set<Contact> focContacts = new HashSet<Contact>();
		focContacts.add(Tom);
		focContacts.add(new ContactImpl("Mark", "examiner")	);
		focDate = Calendar.getInstance();
		focDate.set(2014, 4, 22, 11, 30);
		foc = new PastMeetingImpl(focContacts, focDate, "FoC exam");
		
		String output = foc.getNotes();
		String expected = "FoC exam.";
		assertEquals(output, expected);
	}
}
