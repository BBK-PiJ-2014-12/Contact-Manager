import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ContactManagerImpl implements ContactManager {
	private List<Contact> contacts;
	private List<Meeting> meetings;
	
	public ContactManagerImpl() {
		contacts = new ArrayList();
		meetings = new ArrayList();
		//Creating new Contacts
		Contact Pete = new ContactImpl("Pete Jones", "Marketing manager");
		Contact Tom = new ContactImpl("Tom Hanks", "Actor"); 
		Contact Mary = new ContactImpl("Mary");
		
		contacts.add(Pete);
		contacts.add(Tom);
		contacts.add(Mary);
		
		//Creating new Meetings
		Calendar pijDate;
		Meeting pij;
		Set<Contact> pijContacts = new HashSet<Contact>();
		pijContacts.add(Pete);
		pijContacts.add(Mary);
		pijDate = Calendar.getInstance();
		pijDate.set(2015, 5, 9, 10, 00);
		pij = new MeetingImpl(pijContacts, pijDate, "PiJ exam");

		Calendar sdpDate;
		Meeting sdp;
		Set<Contact> sdpContacts = new HashSet<Contact>();
		sdpContacts.add(Tom);
		sdpContacts.add(Mary);
		sdpDate = Calendar.getInstance();
		sdpDate.set(2015, 4, 26, 14, 30);
		sdp = new MeetingImpl(sdpContacts, sdpDate, "SDP exam");
		
		//Misc
		SimpleDateFormat df;
		df = new SimpleDateFormat("yyyy.MMMMM.dd HH:mm");
	}
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PastMeeting getPastMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meeting getMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date,
			String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMeetingNotes(int id, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewContact(String name, String notes) {
		contacts.add(new ContactImpl(name, notes));		
	}

	@Override
	public Set<Contact> getContacts(int... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Contact> getContacts(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	
	public List<Contact> getCMContacts() {
		return contacts;
	}

}
