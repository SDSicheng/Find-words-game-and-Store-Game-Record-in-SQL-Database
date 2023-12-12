import java.util.ArrayList;

public class FindWordGame{

	private GameSet Frame = new GameSet(); 
	private ArrayList<WordCells> WordList = new ArrayList<WordCells> ();
	private int numOfGuesses = 0; 

	public	void setUpGame() {
		
		WordCells one = new WordCells("apple");
		WordCells two = new WordCells("banana");
		WordCells three = new WordCells("computer");

		
		WordList.add(one);
		WordList.add(two);
		WordList.add(three);

		
		System.out.println("Your goal is to find three words.");
		System.out.println("apple, banana, computer");
		System.out.println("Try to find them all in the fewest number of guesses!");

		
		for (WordCells Cells : WordList) {
			ArrayList<String> newLocation = Frame.placewords(3);
			Cells.setLocationCells(newLocation);
		} 

	} 

	public void startPlaying() {
		
		while (!WordList.isEmpty()) {
			String userGuess = Frame.getUserInput("Enter your guess A0-G6 (Not case sensitive)"); 
			checkUserGuess(userGuess); 
		} 

		finishGame(); 

	}
	private void checkUserGuess(String userGuess) {
		numOfGuesses++; 
		String result = "miss"; 
		for (WordCells dotComToTest : WordList) { 
			result = dotComToTest.checkStatus(userGuess); 

			if (result.equals("hit")) { 
				break;
			} 

			if (result.equals("kill")) { 
				WordList.remove(dotComToTest);
				break;
			} 
		} 

		System.out.println(result); 
	}

	private void finishGame() {
		System.out.println("All words are found!");
		if (numOfGuesses <= 18) { 
			System.out.println("It only took you " + numOfGuesses + " guesses.");
		} else { 
			System.out.println("Took you long enough. " + numOfGuesses + " guesses.");	
		} 

	}

	
} 