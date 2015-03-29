/**
 * The TextProcessor class receives list of strings from the IOModule and creates the Contact and Meeting instances.
 * 
 * @author Zsolt Balvanyos
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TextProcessor {
	ContactManager cm;
	IOModule io = new IOModule();
	List<String> dataIn = new ArrayList();
	List<String> contactList = new ArrayList();
	List<String> meetingList = new ArrayList();
	List<Contact> contacts = new ArrayList();
	List<Meeting> meetings = new ArrayList();
	String[] lineM;
	int position = 0;
	int i = 2;
	
	public TextProcessor(ContactManager cm) {
		this.cm = cm;
	}
	
	/**
	 * Separates lines according to what they hold information on. Contacts go into contactList and meetings go into meetingList. 
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void recoverData() throws FileNotFoundException, IOException {
		dataIn = io.importData();
		
		while(!dataIn.get(i).equals("Meetings")) {
			contactList.add(dataIn.get(i));
			i++;
		}
		i++;
		for(int j = i; j < dataIn.size(); j++) {
			meetingList.add(dataIn.get(j));
		}
	}
	
	/**
	 * Creates the contact instances by using the contactList array. 
	 * 
	 * @return list of contacts. 
	 */
	public List<Contact> createContacts() {
		String[] line;
		for(String str: contactList) {
			line = str.split(",");
			int id = Integer.parseInt(line[0]);
			String name = line[1];
			String notes = "";
			if(line.length > 2) {
				notes = line[2];
				for(int x = 3; x < line.length; x++) {
					notes = notes + ", " + line[x];
				}
			}
			contacts.add(new ContactImpl(id, name, notes));
		}
		return contacts;
	}
	
	/**
	 * Creates the meeting instances by using the meetingList array. 
	 * 
	 * @return list of meetings. 
	 */
	public List<Meeting> createMeeting() {
		Set<Contact> contacts = new HashSet<Contact>();
		
		String notes = "";
		
		Calendar now = Calendar.getInstance();
		
		for(String str: meetingList) {
			Calendar date = Calendar.getInstance();
			lineM = str.split(",");
			int id = getInt(lineM);
			System.out.println("Meeting " + id);
			int year = getInt(lineM);
			int month = getInt(lineM);
			int day = getInt(lineM);
			int hour = getInt(lineM);
			int minute = getInt(lineM);
			System.out.println("Date: " + year + "/" +  month + "/" +  day + " " + hour + ":" + minute);
			int i = 0;
			System.out.println("Pos:" + position);
			while(i < lineM.length - 6 && (!lineM[position + i].equals("Notes:"))) {
				i++;
			}
			System.out.println("i = " + i);
			int[] cons = new int[i];
			
			for(int j = 0; j < i; j++) {
				cons[j] = getInt(lineM);
			}
			
			contacts = cm.getContacts(cons);
			
			if(position < lineM.length) {
				position++;
				notes = lineM[position];
				position++;
				for(int x = position; x < lineM.length; x++) {
					notes = notes + ", " + lineM[x];
				}
			}
			date.set(year, month, day, hour, minute);
			if(date.getTime().getTime() < now.getTime().getTime()) {
				meetings.add(new PastMeetingImpl(id, contacts, date, notes));
			} else {
				meetings.add(new FutureMeetingImpl(id, contacts, date));
			}
			position = 0;
			System.out.println("Meeting added");
		}
		return meetings;
	}
	
	/**
	 * Converts the upcoming string into integer.
	 * @param intList
	 * @return an integer.
	 */
	private int getInt(String[] intList) {
		int num;
		num = Integer.parseInt(intList[position]);
		position++;		
		return num;
	}
}
