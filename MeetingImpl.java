import java.util.Calendar;
import java.util.Set;


public class MeetingImpl implements Meeting{
	private static int lastID;
	private int ID;
	private Calendar date;
	private String notes;
	private Set<Contact> contacts;
	
	public MeetingImpl(Set<Contact> contacts, Calendar date) {
		this.contacts = contacts; 
		this.date = date;
		this.notes = "";
		this.ID = lastID + 1;
		lastID = this.ID;
	}
	
	public MeetingImpl(Set<Contact> contacts, Calendar date, String notes) {
		this.contacts = contacts; 
		this.date = date;
		if (notes.charAt(notes.length() - 1) != '.') {
			this.notes = notes + ". ";
		} else {
			this.notes = notes;	
		}
		this.ID = lastID + 1;
		lastID = this.ID;
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
	
	public String getNotes() {
		return notes;
	}
	
}
