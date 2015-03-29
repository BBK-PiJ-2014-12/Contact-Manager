/**
 * The TextBuilder class builds a list of string from the contact and meeting objects and passes it to the IOModule to written out. 
 * 
 * @author Zsolt Balvanyos
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;


public class TextBuilder {
	IOModule io = new IOModule();
	List<String> dataOut = new ArrayList();
	
	/**
	 * Builds the final array from the components that is being passed to the IOModule
	 * 
	 * @param contacts
	 * @param meetings
	 * @throws IOException
	 */
	public void buildOutput(List<Contact> contacts, List<Meeting> meetings) throws IOException {
		dataOut.add("Contact Manager");
		dataOut.add("Contacts");
		contactString(contacts);
		dataOut.add("Meetings");
		meetingString(meetings);
		io.exportData(dataOut);
	}
	
	/**
	 * Converts all contacts into a string and adds each string to the dataOut list.
	 * 
	 * @param contacts
	 */
	public void contactString(List<Contact> contacts) {
		String line = "";
		for(Contact c: contacts) {
			line = "" + c.getId() + "," + c.getName() + "," + c.getNotes();
			dataOut.add(line);
		}
	}
	
	/**
	 * Converts all meetings into a string and adds each string to the dataOut list.
	 * 
	 * @param meetings
	 */
	public void meetingString(List<Meeting> meetings) {
		String line = "";
		String participants = "";
		for(Meeting m: meetings) {
			System.out.println("Meeting id: " + m.getId() + " Year: " + m.getDate().get(Calendar.YEAR));

			participants = participants(m.getContacts());
			line = "" + m.getId() + "," + m.getDate().get(Calendar.YEAR) + "," 
										+ m.getDate().get(Calendar.MONTH) + "," 
										+ m.getDate().get(Calendar.DAY_OF_MONTH) + "," 
										+ m.getDate().get(Calendar.HOUR_OF_DAY) + "," 
										+ m.getDate().get(Calendar.MINUTE) + "," 
										+ participants;
			if(m.getClass().getName().equals("PastMeetingImpl")) {
				PastMeetingImpl pm = (PastMeetingImpl) m;
				if(!pm.getNotes().equals("")) {
					System.out.println("Notes: " + pm.getNotes());
					line = line + "Notes:,";
					line = line + pm.getNotes();
				}
			}
			dataOut.add(line);
		}
	}
	
	/**
	 * Creates a string from the meeting participants' id number. 
	 * 
	 * @param participants, a set of contacts. 
	 * @returna string of contact ids. 
	 */
	public String participants(Set<Contact> participants) {
		String result = "";
		System.out.println("Number of participants: " + participants.size());
		for(Contact c: participants) {
			result = result + c.getId() + ",";
		}
		System.out.println("Participents: " + result);
		return result;
	}
}