import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class URLReader {
	public static void main(String[] args) throws Exception {
		

		//Create HttpURLConnection 
		HttpURLConnection httpcon = (HttpURLConnection) new URL("https://api.github.com/repos/goxr3plus/XR3Player/releases").openConnection();
		httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
		
		//Read line by line
		String line = "" , inputLine;
		while ( ( inputLine = in.readLine() ) != null) {
			line += "\n" + inputLine;
			System.out.println(inputLine);
		}
		in.close();
		
		//Get Git Hub Downloads of XR3Player
	     Arrays.stream(line.split("\"download_count\":")).skip(1).map(l -> l.split(",")[0]).forEach(l -> System.out.println(l));
				

	    //Sum up all download counts
	    int total = Arrays.stream(line.split("\"download_count\":")).skip(1).mapToInt(l -> Integer.parseInt(l.split(",")[0])).sum();		
	    System.out.println("\nTotal Downloads: " + total);
		
	}
	
}
