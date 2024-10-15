package com.admitere;

import com.admitere.Concurent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String fisierConcurenti = "C:\\Users\\oanai\\IdeaProjects\\Admitere_facultate\\src\\concurenti.txt";
        List<Concurent> concurenti = FileUtils.citesteConcurentiDinFisier(fisierConcurenti);

        Scanner scanner = new Scanner(System.in);
        boolean ruleaza = true;

        while (ruleaza) {
            System.out.println("\nMeniu:");
            System.out.println("1. Adauga concurent");
            System.out.println("2. Sterge concurent");
            System.out.println("3. Modifica note (contestatie)");
            System.out.println("4. Afisare concurenti sortati dupa nume");
            System.out.println("5. Afisare concurenti sortati dupa medie");
            System.out.println("6. Afisare admisi si respinsi");
            System.out.println("7. Iesire");
            System.out.print("Alege o optiune: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    adaugaConcurent(scanner, concurenti);
                    FileUtils.scrieConcurentiInFisier(fisierConcurenti, concurenti);
                    break;
                case 2:
                    stergeConcurent(scanner, concurenti);
                    FileUtils.scrieConcurentiInFisier(fisierConcurenti, concurenti);
                    break;
                case 3:
                    modificaNoteContestatie(scanner, concurenti);
                    FileUtils.scrieConcurentiInFisier(fisierConcurenti, concurenti);
                    break;
                case 4:
                    Collections.sort(concurenti);
                    System.out.println("Concurenti sortati dupa nume:");
                    concurenti.forEach(System.out::println);
                    break;
                case 5:
                    concurenti.sort((c1, c2) -> Double.compare(c2.getMedie(), c1.getMedie()));
                    System.out.println("Concurenti sortati dupa medie:");
                    concurenti.forEach(System.out::println);
                    break;
                case 6:
                    afiseazaAdmisiRespinsi(concurenti);
                    break;
                case 7:
                    ruleaza = false;
                    break;
                default:
                    System.out.println("Optiune invalida. Te rog alege din nou.");
            }
        }
        scanner.close();
    }

    public static void adaugaConcurent(Scanner scanner, List<Concurent> concurenti) {
        System.out.print("Introduceți numele concurentului: ");
        String nume = scanner.nextLine();

        System.out.print("Introduceți notele pentru proba 1 (separate prin spațiu): ");
        double[] noteProba1 = new double[3];
        for (int i = 0; i < 3; i++) {
            noteProba1[i] = scanner.nextDouble();
        }

        System.out.print("Introduceți notele pentru proba 2 (separate prin spațiu): ");
        double[] noteProba2 = new double[3];
        for (int i = 0; i < 3; i++) {
            noteProba2[i] = scanner.nextDouble();
        }

        scanner.nextLine();
        Concurent concurentNou = new Concurent(nume, noteProba1, noteProba2);
        concurenti.add(concurentNou);
        System.out.println("Concurentul a fost adăugat.");
    }

    public static void stergeConcurent(Scanner scanner, List<Concurent> concurenti) {
        System.out.print("Introduceți numele concurentului pe care doriți să-l ștergeți: ");
        String nume = scanner.nextLine();

        Concurent concurentDeSters = null;
        for (Concurent c : concurenti) {
            if (c.getNume().equalsIgnoreCase(nume)) {
                concurentDeSters = c;
                break;
            }
        }

        if (concurentDeSters != null) {
            concurenti.remove(concurentDeSters);
            System.out.println("Concurentul " + nume + " a fost șters.");
        } else {
            System.out.println("Concurentul " + nume + " nu a fost găsit.");
        }
    }

    public static void modificaNoteContestatie(Scanner scanner, List<Concurent> concurenti) {
        System.out.print("Introduceți numele concurentului care a făcut contestație: ");
        String nume = scanner.nextLine();

        Concurent concurentModificat = null;
        for (Concurent c : concurenti) {
            if (c.getNume().equalsIgnoreCase(nume)) {
                concurentModificat = c;
                break;
            }
        }

        if (concurentModificat != null) {
            System.out.print("Introduceți noile note pentru proba 1 (separate prin spațiu): ");
            double[] noiNoteProba1 = new double[3];
            for (int i = 0; i < 3; i++) {
                noiNoteProba1[i] = scanner.nextDouble();
            }

            System.out.print("Introduceți noile note pentru proba 2 (separate prin spațiu): ");
            double[] noiNoteProba2 = new double[3];
            for (int i = 0; i < 3; i++) {
                noiNoteProba2[i] = scanner.nextDouble();
            }

            scanner.nextLine();

            concurentModificat.setNoteProba1(noiNoteProba1);
            concurentModificat.setNoteProba2(noiNoteProba2);
            System.out.println("Notele concurentului " + nume + " au fost modificate.");
        } else {
            System.out.println("Concurentul " + nume + " nu a fost găsit.");
        }
    }

g    public static void afiseazaAdmisiRespinsi(List<Concurent> concurenti) {
        List<Concurent> admisi = new ArrayList<>();
        List<Concurent> respinsi = new ArrayList<>();

        for (Concurent c : concurenti) {
            if (c.getMedie() >= 5.0) {
                admisi.add(c);
            } else {
                respinsi.add(c);
            }
        }

        System.out.println("\nLista admisi:");
        admisi.forEach(System.out::println);

        System.out.println("\nLista respinsi:");
        respinsi.forEach(System.out::println);

        System.out.println("\nNumarul de admisi: " + admisi.size());
        System.out.println("Numarul de respinsi: " + respinsi.size());
    }
}
