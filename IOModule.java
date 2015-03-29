/**
 * The IOModule interacts with the supporting text file, reads in the previously saved data and writes out information in the current session.
 * 
 * @author Zsolt Balvanyos
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class IOModule {
	List<String> input;
	List<String> output;
	PrintWriter out = null;
	BufferedReader in = null;
	String line;
	
	/**
	 * Reads in all lines from the contact.csv file that is saved in the src folder and returns them as an array of strings. 
	 * @return array of strings.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public List<String> importData() throws FileNotFoundException, IOException{
		File file = new File("." + File.separator+ "src" + File.separator + "contacts.csv");
		input = new ArrayList();
		in = new BufferedReader(new FileReader(file));
		while ((line = in.readLine()) != null) {
			for(int i = 0; i < line.length() - 1; i++) {
				if(line.charAt(i) == ',' && line.charAt(i + 1) == ',') {
					line = line.substring(0, i);
				}
			}
			input.add(line);
			System.out.println(line);
		}
		if (in != null) {in.close();}
		return input;
	}
	
	/**
	 * Receives a list of strings from the TextBulder class and writes them out to the contacts.csv file. 
	 * @param output, a list of strings. 
	 * @throws IOException
	 */
	public void exportData(List<String> output) throws IOException  {
		File file = new File("." + File.separator+ "src" + File.separator + "contacts.csv");
		out = new PrintWriter(file);
		for(String str: output) {
				out.write(str + "\n");	
		}
		out.close();
	}
}
