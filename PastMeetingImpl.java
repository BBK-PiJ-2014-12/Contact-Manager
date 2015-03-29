import java.util.Calendar;
import java.util.Set;


public class PastMeetingImpl extends MeetingAbstract implements PastMeeting{
	String notes = "";

	public PastMeetingImpl(Set<Contact> contacts, Calendar date, String notes) {
		super(contacts, date);
		setNotes(notes);
	}
	
	public PastMeetingImpl(int id, Set<Contact> contacts, Calendar date, String notes) {
		super(id, contacts, date);
		setNotes(notes);	
	}
	
	public PastMeetingImpl(int id, Set<Contact> contacts, Calendar date) {
		super(id, contacts, date);
	}
	
	@Override
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String note) {
		if ((!note.equals("")) && note.trim().charAt(note.length() - 1) != '.') {
			this.notes = (notes + " " + note  + ".").trim();
		} else {
			this.notes = (notes + " " + note).trim();
		}	
	}
}
