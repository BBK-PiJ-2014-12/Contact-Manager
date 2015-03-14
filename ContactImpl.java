
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
	}
	
	public ContactImpl(String name, String notes) {
		this.name = name;
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
	public String getName() {
		return name;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public void addNotes(String note) {
		if (note.charAt(note.length() - 1) != '.') {
			notes = notes + note  + ". ";
		} else {
			notes = notes + note;
		}	
	}
}
