package it.paleocapa.labollita;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        
        Circolo circ = new Circolo();

       
        Soci socio1 = new Soci("Samuele", "Labollita", 18, 'M');
        Soci socio2 = new Soci("Matilde", "Loda", 17, 'F');
        Soci socio3 = new Soci("Luca", "Rossi", 25, 'M');
        
        
        circ.caricaSociSuFile("soci.txt");

        circ.aggiungiSocio(socio1);
        circ.aggiungiSocio(socio2);
        circ.aggiungiSocio(socio3);

        
        circ.modificaSocio("Samuele", "Labollita", 36, 'M');

        
        double etaMediaTotale = circ.calcolaEtaMedia();
        System.out.println("Età media dei soci: " + etaMediaTotale);

        
        double etaMediaMaschi = circ.calcolaEtaMediaGenere('M');
        System.out.println("Età media dei soci maschi: " + etaMediaMaschi);

       
        double etaMediaFemmine = circ.calcolaEtaMediaGenere('F');
        System.out.println("Età media dei soci femmine: " + etaMediaFemmine);

        
        HashMap<Character, Double> distribuzionegenere = circ.calcolaDistribuzionePercentualeGenere();
        System.out.println("Distribuzione percentuale dei soci maschi: " + distribuzionegenere.getOrDefault('M', 0.0) + "%");
        System.out.println("Distribuzione percentuale dei soci femmine: " + distribuzionegenere.getOrDefault('F', 0.0) + "%");

        
        circ.rimuoviSocio("Luca", "Rossi");

    }
}
