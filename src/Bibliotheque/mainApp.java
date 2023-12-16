package Bibliotheque;


import java.io.IOException;

public class mainApp {
public static void main(String[] args) {
try {
// Appel du programme OCaml avec une commande spécifique
ocamelCaller.appelProgrammeOCaml("ocaml_programme.exe");
} catch (IOException e) {
e.printStackTrace();
}
}
}
