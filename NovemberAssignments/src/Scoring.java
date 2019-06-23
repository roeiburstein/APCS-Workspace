
public class Scoring {
	public static void main(String[] args) {
		String[] answers = { "a", "a", "b", "b" };
		String[] key = { "a", "?", "a", "b" };
		System.out.println(scoreUp(key, answers));
	}

	public static int scoreUp(String[] key, String[] answers) {
		int score = 0;
		for (int i = 0; i < key.length; i++) {
			if (key[i] == answers[i])
				score += 4;
			else if (key[i] !=  answers[i] && key[i] != "?")
				score--;
		}
		return score;
	}

}
