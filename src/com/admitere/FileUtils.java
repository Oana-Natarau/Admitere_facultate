package com.admitere;

import com.admitere.Concurent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<Concurent> citesteConcurentiDinFisier(String locatieFisier) {
        List<Concurent> concurenti = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(locatieFisier))) {
            String linie;
            while ((linie = reader.readLine()) != null) {
                String[] date = linie.split(";");
                String nume = date[0];
                double[] noteProba1 = parseStringToDoubleArray(date[1]);
                double[] noteProba2 = parseStringToDoubleArray(date[2]);
                concurenti.add(new Concurent(nume, noteProba1, noteProba2));
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului: " + e.getMessage());
        }
        return concurenti;
    }

    private static double[] parseStringToDoubleArray(String noteStr) {
        String[] noteStringArray = noteStr.split(",");
        double[] note = new double[noteStringArray.length];
        for (int i = 0; i < noteStringArray.length; i++) {
            note[i] = Double.parseDouble(noteStringArray[i]);
        }
        return note;
    }


    public static void scrieConcurentiInFisier(String locatieFisier, List<Concurent> concurenti) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(locatieFisier))) {
            for (Concurent c : concurenti) {
                String noteProba1 = convertArrayToString(c.getNoteProba1());
                String noteProba2 = convertArrayToString(c.getNoteProba2());
                writer.write(c.getNume() + ";" + noteProba1 + ";" + noteProba2 + "\n");
            }
        } catch (IOException e) {
            System.err.println("Eroare la scrierea fisierului: " + e.getMessage());
        }
    }

    private static String convertArrayToString(double[] note) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < note.length; i++) {
            sb.append(note[i]);
            if (i < note.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
