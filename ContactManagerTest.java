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
	private List<Contact> contacts;
	private List<Meeting> meetings;
	Calendar pijDate;
	Calendar sdpDate;
	SimpleDateFormat df;
	Contact Pete;
	Contact Tom;
	Contact Mary;
	Set<Contact> pijContacts = new HashSet<Contact>();
	Set<Contact> sdpContacts = new HashSet<Contact>();
	String notes;
	Meeting pij;
	Meeting sdp;
	ContactManager cm;
	
	@Before
	public void buildUp() {
		//Creating new Contacts
		Pete = new ContactImpl("Pete Jones", "Marketing manager");
		Tom = new ContactImpl("Tom Hanks", "Actor"); 
		Mary = new ContactImpl("Mary");
		contacts = new ArrayList();
		contacts.add(Pete);
		contacts.add(Tom);
		contacts.add(Mary);
		
		//Creating new Meetings
		pijContacts.add(Pete);
		pijContacts.add(Mary);
		pijDate = Calendar.getInstance();
		pijDate.set(2015, 5, 9, 10, 00);
		pij = new MeetingImpl(pijContacts, pijDate, "PiJ exam");
		
		sdpContacts.add(Tom);
		sdpContacts.add(Mary);
		sdpDate = Calendar.getInstance();
		sdpDate.set(2015, 4, 26, 14, 30);
		sdp = new MeetingImpl(sdpContacts, sdpDate, "SDP exam");
		
		//Misc
		df = new SimpleDateFormat("yyyy.MMMMM.dd HH:mm");
		
	}
	
	@Test
	public void addNewContact() {
		cm = new ContactManagerImpl();
		cm.addNewContact("Chris", "Supervisor");
		Contact Chris = new ContactImpl("Chris", "Supervisor");
		List<Contact> contacts2 = new ArrayList(); 
		contacts2.add(Pete);
		contacts2.add(Tom);
		contacts2.add(Mary);
		contacts2.add(Chris);
		assertEquals(contacts, contacts2);
	}
	
	@Test
	public void addMeetingNotes(){
		
	}
	
	@Test
	public void addPastMeeting(){
		
	}	
	@Test
	public void addFutureMeeting(){
		
	}	
	@Test
	public void getContats(){
		
	}	
	@Test
	public void getFutureMeeting(){
		
	}	
	@Test
	public void getFutureMeegingList(){
		
	}	
	@Test
	public void getMeeting(){
		
	}	
	@Test
	public void getPastMeeting(){
		
	}	
	@Test
	public void getPastMeetingList(){
		
	}	
	@Test
	public void flush(){
		
	}	
}
