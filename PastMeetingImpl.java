import java.util.Calendar;
import java.util.Set;


public class PastMeetingImpl extends MeetingAbstract implements PastMeeting{
	public PastMeetingImpl(Set<Contact> contacts, Calendar date) {
		super(contacts, date);
	}
	public PastMeetingImpl(Set<Contact> contacts, Calendar date, String notes) {
		super(contacts, date, notes);
	}
}
