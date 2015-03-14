import java.util.Calendar;
import java.util.Set;


public class FutureMeetingImpl extends MeetingAbstract implements FutureMeeting{
	public FutureMeetingImpl(Set<Contact> contacts, Calendar date) {
		super(contacts, date);
	}
	public FutureMeetingImpl(Set<Contact> contacts, Calendar date, String notes) {
		super(contacts, date, notes);
	}

}
