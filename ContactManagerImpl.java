import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;


public class ContactManagerImpl implements ContactManager {
	private List<Contact> contacts;
	private List<Meeting> meetings;
	private Timer timer;
	private Scheduler scheduler;
	private final int DELAY = 0;
	private final int PERIOD = 1000;
	private TextProcessor textProcessor;
	private TextBuilder textBuilder;
	
	public ContactManagerImpl() {
		contacts = new ArrayList();
		meetings = new ArrayList();
		scheduler = new Scheduler(this);
		timer = new Timer();
		timer.schedule(scheduler, DELAY, PERIOD);
		textProcessor = new TextProcessor(this);
		textBuilder = new TextBuilder();
		
		try {
			textProcessor.recoverData();
		} catch (IOException e) {
			System.out.println("The application failed to recover data!");
			e.printStackTrace();
		}
		contacts = textProcessor.createContacts();
		meetings = textProcessor.createMeeting();		
	}
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		Calendar now = Calendar.getInstance();
		if(date.before(now) || !contactsExist(contacts)) {
			throw new IllegalArgumentException();
		} else {
			Meeting addMeeting = new FutureMeetingImpl(contacts, date);
			meetings.add(addMeeting);
			return addMeeting.getId();
		}
	}
	
	/**
	 * Checks if all the contact in a set of contacts exists. 
	 * 
	 * @param participants, the contacts who are checked if they a part of our contact list.
	 * @return true or false
	 */
	private boolean contactsExist(Set<Contact> participants) {
		for(Contact p: participants) {
			if(!contacts.contains(p)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public PastMeeting getPastMeeting(int id) {
		Calendar now = Calendar.getInstance();

		List<Meeting> resultList = new ArrayList();
		getPastMeetings().stream()
						 .filter(m -> m.getId() == id)
						 .forEach(m -> resultList.add(m));

		if(resultList.size() > 1 || resultList.get(0).getDate().after(now)) {
			throw new IllegalStateException();
		}
		
		return (PastMeeting) resultList.get(0);
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {
		Calendar now = Calendar.getInstance();
		
		List<Meeting> resultList = new ArrayList();
		getFutureMeetings().stream()
						   .filter(m -> m.getId() == id)
						   .forEach(m -> resultList.add(m));

		if(resultList.size() > 1 || resultList.get(0).getDate().before(now)) {
			throw new IllegalStateException();
		}
		return (FutureMeeting) resultList.get(0);
	}

	@Override
	public Meeting getMeeting(int id) {
		List<Meeting> resultList = new ArrayList();
		meetings.stream()
				.filter(m -> m.getId() == id)
				.forEach(m -> resultList.add(m));
		
		if(resultList.size() > 1) {
			throw new IllegalStateException();
		}
		return resultList.get(0);
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		List<Meeting> result = new ArrayList();
		
		if(!contacts.contains(contact)) {
			throw new IllegalArgumentException(); 
		}
		
		for(Meeting m: getFutureMeetings()) {
			for(Contact c: m.getContacts()) {
				if(c.getId() == contact.getId()) {
					result.add(m);
				}
			}
		}
		return result;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		List<Meeting> result = new ArrayList();
		for(Meeting m: getFutureMeetings()) {
			m.getDate();
			m.getDate();
			if(m.getDate().get(Calendar.YEAR) == date.get(Calendar.YEAR) && m.getDate().get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)){
				result.add(m);
			}
		}
		return result;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		List<PastMeeting> result = new ArrayList();
		
		if(!contacts.contains(contact)) {
			throw new IllegalArgumentException(); 
		}
		
		for(Meeting m: getPastMeetings()) {
			for(Contact c: m.getContacts()) {
				if(c.getId() == contact.getId()) {
					result.add((PastMeeting) m);
				}
			}
		}
		return result;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contact, Calendar date, String text) {
		if(contact.size() == 0 || !contactsExist(contact)) {
			throw new IllegalArgumentException();
		}
		if(contact == null || date == null || text == null) {
			throw new NullPointerException();
		}
		Meeting addMeeting = new PastMeetingImpl(contact, date, text);
		meetings.add(addMeeting);
	}

	@Override
	public void addMeetingNotes(int id, String text) {
		if(!meetings.contains(getMeeting(id))) {
			throw new IllegalArgumentException();
		}
		Calendar now = Calendar.getInstance();
		if(getMeeting(id).getDate().after(now)) {
			throw new IllegalStateException();
		}
		if(text == null) {
			throw new NullPointerException();
		}
		PastMeetingImpl pm = (PastMeetingImpl) getPastMeeting(id);
		pm.setNotes(text);
	}

	@Override
	public void addNewContact(String name, String notes) {
		if(name == null || notes == null) {
			throw new NullPointerException();
		}
		contacts.add(new ContactImpl(name, notes));		
	}

	@Override
	public Set<Contact> getContacts(int... ids) {
		Set<Integer> allIDs = ids();
		for(int i: ids) {
			if(!allIDs.contains(i)) {
				throw new IllegalArgumentException();
			}
		}
		Set<Contact> result = new HashSet();
		for (int i: ids) {
			contacts.stream()
					.filter(c -> c.getId() == i)
					.forEach(c -> result.add(c));
		}
		return result;
	}
	
	/**
	 * Returns all existing contact ID. 
	 * 
	 * @return set of ids.
	 */
	private Set<Integer> ids() {
		Set<Integer> result = new HashSet();
		for(Contact c: contacts) {
			result.add(c.getId());
		}
		return result;
	}

	@Override
	public Set<Contact> getContacts(String name) {
		if(name == null) {
			throw new NullPointerException();
		}
		Set<Contact> result = new HashSet();
		contacts.stream()
				.filter(c -> c.getName().equals(name))
				.forEach(c -> result.add(c));		
		return result;
	}

	@Override
	public void flush() {
		shutOffTimer();
		try {
			textBuilder.buildOutput(contacts, meetings);
		} catch (IOException e) {
			System.out.println("Saving was unsuccessful!");
			e.printStackTrace();
		}		
	}
	
	/**
	 * Returns all contacts.
	 * @return List of contacts. 
	 */
	public List<Contact> getCMContacts() {
		return contacts;
	}
	
	/**
	 * Returns all meetings.
	 * @return List of meetings.
	 */
	public List<Meeting> getMeetings() {
		return meetings;
	}
	
	/**
	 * Returns a list of past meetings.
	 * 
	 * @return List of past meetings. 
	 */
	public List<Meeting> getPastMeetings() {
		List<Meeting> result = new ArrayList();
		meetings.stream()
				.filter(c -> c.getClass().getName().equals("PastMeetingImpl"))
				.forEach(c -> result.add(c));
		return result;
	}
	
	/**
	 * Returns a list of future meetings.
	 * 
	 * @return List of future meetings. 
	 */
	public List<Meeting> getFutureMeetings() {
		List<Meeting> result = new ArrayList();
		meetings.stream()
				.filter(c -> c.getClass().getName().equals("FutureMeetingImpl"))
				.forEach(c -> result.add(c));
		return result;
	}
	
	/**
	 * Stops the timer running. 
	 */
	public void shutOffTimer() {
		timer.cancel();
	}
	
	/**
	 * The timer calls this method according to a preset interval in order to convert future meeting 
	 * to past meetings in case the meeting has already taken place. 
	 */
	public void revaluateMeetings() {
		Calendar now = Calendar.getInstance();
		List<Meeting> convert = new ArrayList();
		getFutureMeetings().stream()
						   .filter(m -> m.getDate().getTime().getTime() < now.getTime().getTime())
						   .forEach(m -> convert.add(m));
		
		for(Meeting m: convert) {
			convertMeeting(m);
		}
	}
	
	/**
	 * Converts a future meeting into a past meeting by locating the future meeting in the meetings list and 
	 * replacing it with a new past meeting with the same parameters as the future meeting. 
	 * 
	 * @param m the future meeting to be converted into a past meeting. 
	 */
	public void convertMeeting(Meeting m) {
		int i = 0;
		Meeting pastTemp;
		Meeting futureTemp;
		while(meetings.get(i).getId() != m.getId() && i < meetings.size()) {
			futureTemp = meetings.get(i);
			pastTemp = new PastMeetingImpl(futureTemp.getId(), futureTemp.getContacts(), futureTemp.getDate());
			meetings.set(i, pastTemp);
			i++;
		}
	}
	
	/**
	 * The method is to be used to convert future meetings into past meetings. 
	 * 
	 * @param m the future meeting to be converted into a past meeting.
	 * @param notes to be added to the meeting. 
	 */
	public void manualMeetingConversion(Meeting m, String notes) {
		convertMeeting(m);
		addMeetingNotes(m.getId(), notes);
	}
}
