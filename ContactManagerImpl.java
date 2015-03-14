import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ContactManagerImpl implements ContactManager {
	private List<Contact> contacts;
//	private List<Meeting> futureMeetings;
//	private List<Meeting> pastMeetings;
	private List<Meeting> meetings;
	
	public ContactManagerImpl() {
		contacts = new ArrayList();
//		futureMeetings = new ArrayList();
//		pastMeetings = new ArrayList();
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
		pij = new FutureMeetingImpl(pijContacts, pijDate, "PiJ exam");

		Calendar sdpDate;
		Meeting sdp;
		Set<Contact> sdpContacts = new HashSet<Contact>();
		sdpContacts.add(Tom);
		sdpContacts.add(Mary);
		sdpDate = Calendar.getInstance();
		sdpDate.set(2015, 4, 26, 14, 30);
		sdp = new FutureMeetingImpl(sdpContacts, sdpDate, "SDP exam");
		
		Calendar focDate;
		Meeting foc;
		Set<Contact> focContacts = new HashSet<Contact>();
		focContacts.add(Tom);
		focContacts.add(new ContactImpl("Mark", "examiner")	);
		focDate = Calendar.getInstance();
		focDate.set(2014, 4, 22, 11, 30);
		foc = new PastMeetingImpl(focContacts, focDate);
		
		meetings.add(pij);
		meetings.add(sdp);
		meetings.add(foc);
		
		//Misc
		SimpleDateFormat df;
		df = new SimpleDateFormat("yyyy.MMMMM.dd HH:mm");
	}
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		Meeting addMeeting = new FutureMeetingImpl(contacts, date);
		meetings.add(addMeeting);
		return addMeeting.getId();
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
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		Meeting addMeeting = new PastMeetingImpl(contacts, date);
		meetings.add(addMeeting);
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
		Set<Contact> result = new HashSet();
		for (int i: ids) {
			contacts.stream()
					.filter(c -> c.getId() == i)
					.forEach(c -> result.add(c));
		}
		return result;
	}

	@Override
	public Set<Contact> getContacts(String name) {
		Set<Contact> result = new HashSet();
		contacts.stream()
				.filter(c -> c.getName().equals(name))
				.forEach(c -> result.add(c));		
		return result;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	
	public List<Contact> getCMContacts() {
		return contacts;
	}
	
	public List<Meeting> getMeetings() {
		return meetings;
	}
	
	public List<Meeting> getPastMeetings() {
		List<Meeting> result = new ArrayList();
		meetings.stream()
				.filter(c -> c.getClass().getName().equals("PastMeetingImpl"))
				.forEach(c -> result.add(c));
		return result;
	}
	
	public List<Meeting> getFutureMeetings() {
		List<Meeting> result = new ArrayList();
		meetings.stream()
				.filter(c -> c.getClass().getName().equals("FutureMeetingImpl"))
				.forEach(c -> result.add(c));
		return result;
	}
}
