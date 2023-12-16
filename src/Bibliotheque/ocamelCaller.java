package Bibliotheque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class ocamelCaller {

		public static void appelProgrammeOCaml(String commandeOCaml) throws IOException {
		Process process = Runtime.getRuntime().exec(commandeOCaml);
		
	    // Lisez la sortie du programme OCaml
	    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	    String ligne;
	    while ((ligne = reader.readLine()) != null) {
	        System.out.println(ligne);
	    }

	    try {
	        process.waitFor();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    System.out.println("Appel au programme OCaml terminé.");
	}


}
