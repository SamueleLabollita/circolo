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

    @Test
    public void testAggiungiSocio() {
        Soci socio1 = new Soci("Samuele", "Labollita", 18, 'M');
        circolo.aggiungiSocio(socio1);
        assertEquals(1, circolo.getSoci().size());

        Soci socio2 = new Soci("Matilde", "Loda", 17, 'F');
        circolo.aggiungiSocio(socio2);
        assertEquals(2, circolo.getSoci().size());
    }

    @Test
    public void testModificaSocio() {
        Soci socio = new Soci("Samuele", "Labollita", 18, 'M');
        circolo.aggiungiSocio(socio);
        assertEquals(18, circolo.getSoci().get("SamueleLabollita").getEta());

        circolo.modificaSocio("Samuele", "Labollita", 20, 'M');
        assertEquals(20, circolo.getSoci().get("SamueleLabollita").getEta());
    }

    @Test
    public void testRimuoviSocio() {
        Soci socio = new Soci("Samuele", "Labollita", 18, 'M');
        circolo.aggiungiSocio(socio);
        assertEquals(1, circolo.getSoci().size());

        circolo.rimuoviSocio("Samuele", "Labollita");
        assertEquals(0, circolo.getSoci().size());
    }

    @Test
    public void testCalcolaEtaMedia() {
        Soci socio1 = new Soci("Samuele", "Labollita", 18, 'M');
        Soci socio2 = new Soci("Matilde", "Loda", 17, 'F');

        circolo.aggiungiSocio(socio1);
        circolo.aggiungiSocio(socio2);

        double etaMedia = circolo.calcolaEtaMedia();
        assertEquals(17.5, etaMedia, 0.001);
    }

    @Test
    public void testCalcolaEtaMediaSesso() {
        Soci socio1 = new Soci("Samuele", "Labollita", 18, 'M');
        Soci socio2 = new Soci("Matilde", "Loda", 17, 'F');
        Soci socio3 = new Soci("Luca", "Rossi", 25, 'M');

        circolo.aggiungiSocio(socio1);
        circolo.aggiungiSocio(socio2);
        circolo.aggiungiSocio(socio3);

        double etaMediaMaschi = circolo.calcolaEtaMediaGenere('M');
        assertEquals(21.5, etaMediaMaschi, 0.001);

        double etaMediaFemmine = circolo.calcolaEtaMediaGenere('F');
        assertEquals(17.0, etaMediaFemmine, 0.001); 
    }

    @Test
    public void testCalcolaDistribuzionePercentualeSesso() {
        Soci socio1 = new Soci("Samuele", "Labollita", 18, 'M');
        Soci socio2 = new Soci("Matilde", "Loda", 17, 'F');
        Soci socio3 = new Soci("Luca", "Rossi", 25, 'M');

        circolo.aggiungiSocio(socio1);
        circolo.aggiungiSocio(socio2);
        circolo.aggiungiSocio(socio3);

        HashMap<Character, Double> distribuzioneSesso = circolo.calcolaDistribuzionePercentualeGenere();

        assertEquals(66.67, distribuzioneSesso.get('M'), 0.01); 
        assertEquals(33.33, distribuzioneSesso.get('F'), 0.01); 
    }
}
