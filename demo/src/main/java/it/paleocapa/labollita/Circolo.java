package it.paleocapa.labollita;

import java.io.*;
import java.util.HashMap;

public class Circolo {
    private HashMap<String, Soci> socis; 

    public Circolo() {
        socis = new HashMap<>();
    }

    public void aggiungiSocio(Soci socio) {
        socis.put(socio.getNome() + socio.getCognome(), socio);
        //System.out.println("Socio aggiunto");
    }

    public void modificaSocio(String nome, String cognome, int nuovaEta, char nuovoGenere) {
        String chiave = nome + cognome;
        Soci socio = socis.get(chiave);
        if (socio != null) {
            socio.setGenere(nuovoGenere);
            socio.incrementaEta();
            System.out.println("Socio modificato");
        } else {
            System.out.println("Socio non trovato");
        }
    }

    public void rimuoviSocio(String nome, String cognome) {
        String chiave = nome + cognome;
        Soci socio = socis.remove(chiave);
        if (socio != null) {
            System.out.println("Socio rimosso");
        } else {
            System.out.println("Socio non trovato");
        }
    }

    public double calcolaEtaMedia() {
        int sommaEta = 0;
        for (Soci socio : socis.values()) {
            sommaEta += socio.getEta();
        }
        return (double) sommaEta / socis.size();
    }

    public double calcolaEtaMediaGenere(char sesso) {
        int sommaEta = 0;
        int contSoci = 0;
        for (Soci socio : socis.values()) {
            if (socio.getGenere() == sesso) {
                sommaEta += socio.getEta();
                contSoci++;
            }
        }
        if (contSoci == 0) {
            return 0;
        }
        return (double) sommaEta / contSoci;
    }

    public HashMap<Character, Double> calcolaDistribuzionePercentualeGenere() {
        HashMap<Character, Double> distribuzione = new HashMap<>();
        int contM = 0;
        int contF = 0;
        for (Soci socio : socis.values()) {
            if (socio.getGenere() == 'M') {
                contM++;
            } else if (socio.getGenere() == 'F') {
                contF++;
            }
        }
        int contTot = contM + contF;
        if (contTot > 0) {
            double percM = (double) contM / contTot * 100;
            double percF = (double) contF / contTot * 100;
            distribuzione.put('M', percM);
            distribuzione.put('F', percF);
        }
        return distribuzione;
    }
    public HashMap<String, Soci> getSoci() {
        return socis;
    }
    public void caricaSociSuFile(String nomeFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] oggetti = linea.split(" ");
                if (oggetti.length == 4) {
                    String nome = oggetti[0];
                    String cognome = oggetti[1];
                    int eta = Integer.parseInt(oggetti[2]);
                    char sesso = oggetti[3].charAt(0);

                    Soci socio = new Soci(nome, cognome, eta, sesso);
                    aggiungiSocio(socio);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File non trovato: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Errore lettura file: " + e.getMessage());
        }
    }
}