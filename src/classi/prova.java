package classi;

import java.security.NoSuchAlgorithmException;

public class prova {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String stringa = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		SHA256 sha256 = new SHA256();
		System.out.println(sha256.encrypt(stringa));

	}

}
