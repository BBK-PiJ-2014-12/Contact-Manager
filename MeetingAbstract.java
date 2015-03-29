import java.util.Calendar;
import java.util.Set;


public abstract class MeetingAbstract implements Meeting{
	private static int lastID;
	private int ID;
	private Calendar date;
	private Set<Contact> contacts;
	
	public MeetingAbstract(Set<Contact> contacts, Calendar date) {
		this.contacts = contacts; 
		this.date = date;
		this.ID = lastID + 1;
		lastID = this.ID;
	}
	
	public MeetingAbstract(int id, Set<Contact> contacts, Calendar date) {
		this.contacts = contacts; 
		this.date = date;
		this.ID = id;
		lastID++;
	}
	
	@Override
	public int getId() {
		return ID;
	}
	@Override
	public Calendar getDate() {
		return date;
	}
	@Override
	public Set<Contact> getContacts() {
		return contacts;
	}
}
