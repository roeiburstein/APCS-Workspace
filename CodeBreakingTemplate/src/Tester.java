public class Tester {

	public static void main(String[] args) {
		// rotationCipher();
		viginereCipher();
	}

	public static void viginereCipher() {
		String cipher2 = Cipher.getFileAsString("ciphertext2.txt");
		for (int character1 = 0; char1 < 84; char1++) {
			for (int char2 = 0; char2 < 84; char2++) {
				String code = Cipher.ALPHABET.substring(char1, char1 + 1) + Cipher.ALPHABET.substring(char2, char2 + 1);
				String plaintext = Cipher.vigenereCipherDecrypt(cipher2, code);
				if (Cipher.isDecoded(plaintext));
				System.out.println(plaintext);
			}

		}
	}

	public static void rotationCipher() {
		String cipher1 = Cipher.getFileAsString("ciphertext1.txt");
		for (int i = 0; i < 84; i++) {
			String plaintext = Cipher.rotationCipherDecrypt(cipher1, i);
			if (Cipher.isDecoded(plaintext))
				System.out.println(plaintext);
		}
	}
}