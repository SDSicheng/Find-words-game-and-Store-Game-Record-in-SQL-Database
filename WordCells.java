import java.util.ArrayList;

public class WordCells {
	private ArrayList<String>locationCells;
	private String name;
	
	public void setLocationCells(ArrayList<String> location) {
		locationCells = location;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public WordCells(String name){
		setName(name);
	}
	
	public String checkStatus(String userInput) {
		String result = "miss";
		int index = locationCells.indexOf(userInput);
		if(index>=0) {
			locationCells.remove(index);
			if(locationCells.isEmpty()) {
				result = "kill";
				System.out.println("You find the word :"+name+"");
			}else{
				result ="hit";
			}
		}
		return result;
	}
	
}
