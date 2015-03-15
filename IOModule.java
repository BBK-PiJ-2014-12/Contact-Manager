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
	
	public List<String> importData() throws FileNotFoundException, IOException{
		File file = new File("." + File.separator+ "src" + File.separator + "contacts.csv");
		input = new ArrayList();
		in = new BufferedReader(new FileReader(file));
		while ((line = in.readLine()) != null) {
			input.add(line);
		}
		if (in != null) {in.close();}
		return input;
	}
	
	public void exportData(List<String> output) throws IOException  {
		File file = new File("." + File.separator+ "src" + File.separator + "contacts.csv");
		out = new PrintWriter(file);
		for(String str: output) {
				out.write(str + "\n");	
		}
		out.close();
	}
}
