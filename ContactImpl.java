
public class ContactImpl implements Contact{
	private static int lastID = 0;
	private int ID;
	private String name;
	private String notes;
	
	public ContactImpl(String name) {
		this.name = name;
		this.notes = "";
		this.ID = lastID + 1;
		lastID = this.ID;	
		System.out.println(lastID);
	}
	
	public ContactImpl(String name, String notes) {
		this.name = name;
		this.notes = notes;
		this.ID = lastID + 1;
		lastID = this.ID;
		System.out.println(lastID);
	}

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public void addNotes(String note) {
		if (!notes.equals("") && notes.charAt(notes.length() - 1) != '.') {
			notes = notes + ". " + note;
		} else if (!notes.equals("") && notes.charAt(notes.length() - 1) == '.') {
			notes = notes + " " + note;
		} else {
			notes = note;
		}	
	}
}
