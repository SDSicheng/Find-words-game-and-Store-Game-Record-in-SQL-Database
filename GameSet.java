import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameSet {

	private static final String alphabet = "abcdefg";
	final int gridLength = 7;
	final int gridSize = 49; //area of grid
	private int[] grid = new int[gridSize]; 
	private int Count = 0;

	public String getUserInput (String prompt) {
		String inputLine = null;
		System.out.print(prompt + " ");

		try{
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
			if(inputLine.length() == 0) return null;
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		} 

		return inputLine.toLowerCase();
	} 

	public ArrayList<String> placewords(int WordSize) {
		ArrayList<String> alphaCells = new ArrayList<String>();
		String temp = null; 
		int[] coords = new int[WordSize]; 
		int attempts = 0;  
		boolean success = false; 
		int location = 0; 

		Count++; 
		int incr = 1; 

		if ((Count % 2) == 1) { 
			incr = gridLength; 
		}

		while (!success & attempts++ < 60) { 
			location = (int) (Math.random() * gridSize); 
			int x = 0;
			success = true; 

			while (success && x < WordSize) { 

				if (grid[location] == 0) { 
					coords[x++] = location; 
					location += incr; 

					if (location >= gridSize) { 
						success = false; 
					} 

					if ( x>0 && (location % gridLength == 0)) { 
						success = false; 
					} 			
				} else { 
					
					success = false; 
				} 
			} 
		} 

		int x = 0; 
		int row = 0;
		int column = 0;
		
		while (x < WordSize) {
			grid[coords[x]] = 1; 
			row = (int) (coords[x] / gridLength); 
			column = coords[x] % gridLength; 
			temp = String.valueOf(alphabet.charAt(column)); 
			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;
		} 
		return alphaCells;
	}

} 