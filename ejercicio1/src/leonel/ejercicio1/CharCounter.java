package ejercicio1;

public class CharCounter {
	private String word;
	
	public CharCounter(String word) {
		this.word = word;
	}

	public int howMany(char character) {
		int count = 0; 
		for(int i=0 ; i<word.length(); i++){
			
			if(word.charAt(i) == character){
				count++;
			}
		}
		return count;
	}
	
	

}
