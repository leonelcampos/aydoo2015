package ejercicio1;

import java.util.HashMap;

public class CharCounter {
	private String word;

	public CharCounter(String word) {
		this.word = word;
	}

	public int howMany(char character) {
		int count = 0;
		if (character == '$') {
			count = -1;
		} else {
			if (character == '@') {
				count = -2;
			} else {
				count = countCharacterFromWord(character, count);
			}
		}
		return count;
	}

	private int countCharacterFromWord(char character, int count) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == character) {
				count++;
			}
		}
		return count;
	}

	public HashMap<Character, Integer> countAll() {
		HashMap<Character, Integer> result = new HashMap<Character, Integer>();
		int count = 0;
		initializeMap(result);

		for (int i = 0; i < word.length(); i++) {

			if (!result.containsKey(word.charAt(i))) {
				count = countCharacterFromWord(word.charAt(i), count);
				result.put(word.charAt(i), count);
			}
			count = 0;
		}

		return result;
	}

	private void initializeMap(HashMap<Character, Integer> result) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz123456789@#$";

		for (int i = 0; i < alphabet.length(); i++) {
			result.put(alphabet.charAt(i), howMany(alphabet.charAt(i)));
		}
	}
}
