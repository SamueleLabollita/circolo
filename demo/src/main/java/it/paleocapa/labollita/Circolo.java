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
        int conteggioSoci = 0;
        for (Soci socio : socis.values()) {
            if (socio.getGenere() == sesso) {
                sommaEta += socio.getEta();
                conteggioSoci++;
            }
        }
        if (conteggioSoci == 0) {
            return 0;
        }
        return (double) sommaEta / conteggioSoci;
    }

    public HashMap<Character, Double> calcolaDistribuzionePercentualeGenere() {
        HashMap<Character, Double> distribuzione = new HashMap<>();
        int conteggioMaschi = 0;
        int conteggioFemmine = 0;
        for (Soci socio : socis.values()) {
            if (socio.getGenere() == 'M') {
                conteggioMaschi++;
            } else if (socio.getGenere() == 'F') {
                conteggioFemmine++;
            }
        }
        int conteggioTotale = conteggioMaschi + conteggioFemmine;
        if (conteggioTotale > 0) {
            double percentualeMaschi = (double) conteggioMaschi / conteggioTotale * 100;
            double percentualeFemmine = (double) conteggioFemmine / conteggioTotale * 100;
            distribuzione.put('M', percentualeMaschi);
            distribuzione.put('F', percentualeFemmine);
        }
        return distribuzione;
    }
    public HashMap<String, Soci> getSoci() {
        return socis;
    }
    public void caricaSociSuFile(String nomeFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (tokens.length == 4) {
                    String nome = tokens[0];
                    String cognome = tokens[1];
                    int eta = Integer.parseInt(tokens[2]);
                    char sesso = tokens[3].charAt(0);

                    Soci socio = new Soci(nome, cognome, eta, sesso);
                    aggiungiSocio(socio);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File non trovato: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Errore durante la lettura del file: " + e.getMessage());
        }
    }
}