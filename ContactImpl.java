
public class ContactImpl implements Contact{
	private static int lastID = 0;
	private int ID;
	private String name;
	private String notes;
	
	public ContactImpl(int id, String name, String notes) {
		this.name = name;
		this.notes = "";
		addNotes(notes);
		this.ID = id;
		lastID++;	
	}
	
	public ContactImpl(String name, String notes) {
		this.name = name;
		this.notes = "";
		addNotes(notes);
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
		if ((!note.equals("")) && note.trim().charAt(note.length() - 1) != '.') {
			notes = (notes + " " + note  + ".").trim();
		} else {
			notes = (notes + " " + note).trim();
		}	
	}
	
	public String toString() {
		return "ID " + ID + " | Name: " + name + " | Notes: " + notes;
	}
}
