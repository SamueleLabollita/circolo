package it.paleocapa.labollita;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
import java.io.*;
import java.util.*;

public class CircoloTest {

    private Circolo circolo = new Circolo();
    private File file;

    @Test
    public void testAggiungiSocio() {
        Soci socio1 = new Soci("Samuele", "Labollita", 35, 'M');
        circolo.aggiungiSocio(socio1);
        assertEquals(1, circolo.getSoci().size());

        Soci socio2 = new Soci("Matilde", "Loda", 40, 'F');
        circolo.aggiungiSocio(socio2);
        assertEquals(2, circolo.getSoci().size());
    }

    @Test
    public void testModificaSocio() {
        Soci socio = new Soci("Samuele", "Labollita", 35, 'M');
        circolo.aggiungiSocio(socio);
        assertEquals(35, circolo.getSoci().get("SamueleLabollita").getEta());

        circolo.modificaSocio("Samuele", "Labollita", 36, 'M');
        assertEquals(36, circolo.getSoci().get("SamueleLabollita").getEta());
    }

    @Test
    public void testRimuoviSocio() {
        Soci socio = new Soci("Samuele", "Labollita", 35, 'M');
        circolo.aggiungiSocio(socio);
        assertEquals(1, circolo.getSoci().size());

        circolo.rimuoviSocio("Samuele", "Labollita");
        assertEquals(0, circolo.getSoci().size());
    }

    @Test
    public void testCalcolaEtaMedia() {
        Soci socio1 = new Soci("Samuele", "Labollita", 35, 'M');
        Soci socio2 = new Soci("Matilde", "Loda", 40, 'F');

        circolo.aggiungiSocio(socio1);
        circolo.aggiungiSocio(socio2);

        double etaMedia = circolo.calcolaEtaMedia();
        assertEquals(37.5, etaMedia, 0.001);
    }

    @Test
    public void testCalcolaEtaMediaSesso() {
        Soci socio1 = new Soci("Samuele", "Labollita", 35, 'M');
        Soci socio2 = new Soci("Matilde", "Loda", 40, 'F');
        Soci socio3 = new Soci("Luca", "Rossi", 25, 'M');

        circolo.aggiungiSocio(socio1);
        circolo.aggiungiSocio(socio2);
        circolo.aggiungiSocio(socio3);

        double etaMediaMaschi = circolo.calcolaEtaMediaGenere('M');
        assertEquals(30.0, etaMediaMaschi, 0.001);

        double etaMediaFemmine = circolo.calcolaEtaMediaGenere('F');
        assertEquals(40.0, etaMediaFemmine, 0.001); 
    }

    @Test
    public void testCalcolaDistribuzionePercentualeSesso() {
        Soci socio1 = new Soci("Samuele", "Labollita", 35, 'M');
        Soci socio2 = new Soci("Matilde", "Loda", 40, 'F');
        Soci socio3 = new Soci("Luca", "Rossi", 25, 'M');

        circolo.aggiungiSocio(socio1);
        circolo.aggiungiSocio(socio2);
        circolo.aggiungiSocio(socio3);

        HashMap<Character, Double> distribuzioneSesso = circolo.calcolaDistribuzionePercentualeGenere();

        assertEquals(66.67, distribuzioneSesso.get('M'), 0.01); 
        assertEquals(33.33, distribuzioneSesso.get('F'), 0.01); 
    }
    @Test
    public void testCaricaSociSuFile() throws IOException {
        // Crea un file temporaneo per il test
        file = new File("soci_test.txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("Carlo Rossi 30 M\n");
        fileWriter.write("Anna Labollita 25 F\n");
        fileWriter.close();

        // Carica i soci dal file appena creato
        circolo.caricaSociSuFile(file.getPath());

        // Verifica che i soci siano stati caricati correttamente
        assertEquals(2, circolo.getSoci().size());

        Soci socio1 = circolo.getSoci().get("CarloRossi");
        assertEquals("Carlo", socio1.getNome());
        assertEquals("Rossi", socio1.getCognome());
        assertEquals(30, socio1.getEta());
        assertEquals('M', socio1.getGenere());

        Soci socio2 = circolo.getSoci().get("AnnaLabollita");
        assertEquals("Anna", socio2.getNome());
        assertEquals("Labollita", socio2.getCognome());
        assertEquals(25, socio2.getEta());
        assertEquals('F', socio2.getGenere());
    }

    
}
