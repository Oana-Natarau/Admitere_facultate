package com.admitere;

import java.util.Arrays;

public class Concurent implements Comparable<Concurent> {
    private String nume;
    private double[] noteProba1;
    private double[] noteProba2;
    private double medie;

    public Concurent(String nume, double[] noteProba1, double[] noteProba2) {
        this.nume = nume;
        this.noteProba1 = noteProba1;
        this.noteProba2 = noteProba2;
        this.medie = calculeazaMedie();
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double[] getNoteProba1() {
        return noteProba1;
    }

    public void setNoteProba1(double[] noteProba1) {
        this.noteProba1 = noteProba1;
        this.medie = calculeazaMedie();
    }

    public double[] getNoteProba2() {
        return noteProba2;
    }

    public void setNoteProba2(double[] noteProba2) {
        this.noteProba2 = noteProba2;
        this.medie = calculeazaMedie();
    }

    public double getMedie() {
        return medie;
    }

    public void setMedie(double medie) {
        this.medie = medie;
    }

    public double calculeazaNota(double[] note) {
        return Arrays.stream(note).average().orElse(0);
    }

    public double calculeazaMedie() {
        return (calculeazaNota(noteProba1) + calculeazaNota(noteProba2)) / 2;
    }

    @Override
    public int compareTo(Concurent altConcurent) {
        return this.nume.compareTo(altConcurent.getNume());
    }

    @Override
    public String toString() {
        return "Nume: " + nume + ", Medie: " + medie + ", Note Proba 1: " + Arrays.toString(noteProba1) + ", Note Proba 2: " + Arrays.toString(noteProba2);
    }
}
