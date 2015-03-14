import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactManagerTest {
	List<Contact> contacts;
	List<Meeting> meetings;

	SimpleDateFormat df = new SimpleDateFormat("yyyy.MMMMM.dd HH:mm");
	Contact Pete = new ContactImpl("Pete Jones", "Marketing manager");
	Contact Tom = new ContactImpl("Tom Hanks", "Actor"); 
	Contact Mary = new ContactImpl("Mary");
	Contact Chris;
	String notes;
	ContactManagerImpl cm;
	List<Contact> contacts2;
	
//	
//	@Test
//	public void addNewContact() {
//		cm = new ContactManagerImpl();
//		cm.addNewContact("Chris", "Supervisor");
//		Chris = new ContactImpl("Chris", "Supervisor");
//		contacts2 = new ArrayList(); 
//		contacts2.add(Pete);
//		contacts2.add(Tom);
//		contacts2.add(Mary);
//		contacts2.add(Chris);
//		String output = cm.getCMContacts().get(3).getName().toString();
//		String expected = "Chris";
//		assertEquals(output, expected);
//	}
//	
//	@Test
//	public void addMeetingNotes(){
//		
//	}
	
//	@Test
//	public void addNewPastMeeting(){
//		cm = new ContactManagerImpl();
//
//		Calendar csDate;
//		Meeting cs;
//		Set<Contact> csContacts = new HashSet<Contact>();
//		csContacts.add(Tom);
//		csContacts.add(Mary);
//		csDate = Calendar.getInstance();
//		csDate.set(2014, 5, 8, 14, 00);
//		//cs = new PastMeetingImpl(csContacts, csDate, "CS exam");
//		
//		cm.addNewPastMeeting(csContacts, csDate, "Dining philosophers");
//		int output = cm.getPastMeetings().size();
//		int expected = 2;
//		
//		assertEquals(expected, output);
//	}	
//	@Test
//	public void addFutureMeeting(){
//		cm = new ContactManagerImpl();
//		
//		Calendar dkmDate;
//		Meeting dkm;
//		Set<Contact> dkmContacts = new HashSet<Contact>();
//		dkmContacts.add(Tom);
//		dkmContacts.add(Mary);
//		dkmDate = Calendar.getInstance();
//		dkmDate.set(2015, 5, 4, 14, 30);
//		//dkm = new FutureMeetingImpl(dkmContacts, dkmDate, "DKM exam");
//		
//		cm.addFutureMeeting(dkmContacts, dkmDate);
//		
//		int output = cm.getFutureMeetings().size();
//		int expected = 3;
//		
//		assertEquals(expected, output);
//	}	
//	@Test
//	public void getContats(){
//		cm = new ContactManagerImpl();
//
//		cm.addNewContact("Tom Hanks", "Astronaut");
//		int output = cm.getContacts("Tom Hanks").size();
//		int expected = 2;
//		assertEquals(output, expected); 
//
//		Set<Contact> input2 = cm.getContacts(1, 3);
//		int output2 = input2.size();
//		int expected2 = 2;
//		assertEquals(expected2, output2); 
//	}	
//	@Test
//	public void getFutureMeeting(){
//		
//	}	
//	@Test
//	public void getFutureMeegingList(){
//		
//	}	
//	@Test
//	public void getMeeting(){
//		cm = new ContactManagerImpl();
//		
//		Meeting testMeeting = cm.getMeeting(1);
//		String output = df.format(testMeeting.getDate().getTime());
//		String expected = "2015.June.09 10:00";
//		
//		assertEquals(expected, output);
//	}	
	@Test
	public void getPastMeeting(){
		cm = new ContactManagerImpl();
		
		Meeting testMeeting = cm.getMeeting(3);
		String output = df.format(testMeeting.getDate().getTime());
		String expected = "2014.May.22 11:30";
		assertEquals(expected, output);
		
		//Meeting ID 1 is a future meeting, this test expected to fail.
		Meeting testMeeting2 = cm.getMeeting(1);
		String output2 = df.format(testMeeting2.getDate().getTime());
		String expected2 = "2015.June.09 10:00";
		assertEquals(expected2, output2);
	}	
//	@Test
//	public void getPastMeetingList(){
//
//	}	
//	@Test
//	public void flush(){
//		
//	}	
}
