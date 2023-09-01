package it.paleocapa.labollita;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        
        Circolo circ = new Circolo();
        Soci socio1 = new Soci("Samuele", "Labollita", 18, 'M');
        Soci socio2 = new Soci("Matilde", "Loda", 17, 'F');
        Soci socio3 = new Soci("Luca", "Rossi", 25, 'M');
        circ.aggiungiSocio(socio1);
        circ.aggiungiSocio(socio2);
        circ.aggiungiSocio(socio3);
        circ.modificaSocio("Samuele", "Labollita", 36, 'M');
        circ.caricaSociSuFile("soci.txt");

        double etaMediaTotale = circ.calcolaEtaMedia();
        double etaMediaMaschi = circ.calcolaEtaMediaGenere('M');
        double etaMediaFemmine = circ.calcolaEtaMediaGenere('F');
        HashMap<Character, Double> distribuzionegenere = circ.calcolaDistribuzionePercentualeGenere();
        Double percentualeMaschi = distribuzionegenere.get('M');
        Double percentualeFemmine = distribuzionegenere.get('F');
        
        System.out.println("Età media dei soci maschi: " + etaMediaMaschi + 
        "\nEtà media dei soci: " + etaMediaTotale + 
        "\nEtà media dei soci femmine: " + etaMediaFemmine + 
        "\nDistribuzione percentuale dei soci maschi: " + percentualeMaschi + "%" + 
        "\nDistribuzione percentuale dei soci femmine: " + percentualeFemmine + "%");

        circ.rimuoviSocio("Luca", "Rossi");

    }
}
