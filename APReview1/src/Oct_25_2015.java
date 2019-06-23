import java.util.ArrayList;

public class Oct_25_2015 {

	
	public  int countAs(String word) {
		int counter = 0;
		for (int i = 0; i < word.length(); i++) {
			System.out.println(word.substring(i, i + 1));

			if (word.substring(i, i + 1) == "a")
				counter++;
		}
		return counter;
	}
	
	public  String getAWord(ArrayList<String> words) {
		String aWord = "";
		for(int i = 0; i < words.size(); i++){
			if(countAs(words.get(i)) > countAs(aWord)) aWord = words.get(i); 
		}
		
		return aWord;
	}
	
	public void method1(ArrayList<String> words) {
		for(int i = 0; i < words.size(); i++){
			for(int j = 0; j < (words.get(i)).length(); j++){
				if(words.get(i).substring(j, j + 1) == "c" && words.get(i).substring(j + 1, j + 2) == "a" && words.get(i).substring(j + 2, j + 3) == "t"){
					StringBuilder temp = new StringBuilder(words.get(i));
					temp.setCharAt(j, 'X');
					temp.setCharAt(j + 1, 'X');
					temp.setCharAt(j + 2, 'X');
					words.set(i, temp.toString());
				}
			}
		}

	}
}
