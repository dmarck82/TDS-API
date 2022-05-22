package br.edu.utfpr.tdsapi.tdsapi.security.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("m0bil3")); // Senha que deseja que seja gerada
	}
}