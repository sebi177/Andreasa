package Sallary;

import java.util.*;

public class CalculSumaInstalator {

    public static void main(String[] args) {
        // Definirea tarifelor pe metru pătrat pentru fiecare tip de muncă
        Map<String, Double> tarife = new HashMap<>();
        tarife.put("Dampfsperre", 1.50);
        tarife.put("EineLage", 2.70);
        tarife.put("ZweiLagen", 3.45);
        tarife.put("DreiLagen", 4.65);
        tarife.put("VierLagen", 5.70);
        tarife.put("BKT", 1.80);
        tarife.put("Trennlage", 0.30);
        tarife.put("EineLageOhneTackern", 1.20);
        tarife.put("ZweiLagenOhneTackern", 2.40);
        tarife.put("Tackern", 2.10);
        tarife.put("Noppenlage", 2.85);
        tarife.put("Airconomy", 4.50);

        // Map to store daily information
        Map<String, Double> dailyData = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        // Input loop for daily data
        while (true) {
            // Citirea datei
            System.out.print("Introduceți data (sau 'stop' pentru a încheia introducerea datelor): ");
            String data = scanner.nextLine();

            if (data.equalsIgnoreCase("stop")) {
                break;
            }

            // Afișarea tipurilor de muncă disponibile
            System.out.println("Tipurile de muncă disponibile:");
            int index = 1;
            for (String munca : tarife.keySet()) {
                System.out.println(index + ". " + munca);
                index++;
            }

            // Selectarea tipului de muncă
            System.out.print("Selectați un tip de muncă: ");
            int optiune = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            // Obținerea tipului de muncă selectat
            String muncaSelectata = "";
            index = 1;
            for (String munca : tarife.keySet()) {
                if (index == optiune) {
                    muncaSelectata = munca;
                    break;
                }
                index++;
            }

            // Citirea numărului de metri pătrați
            System.out.print("Introduceți numărul de metri pătrați: ");
            double metriPatrati = scanner.nextDouble();
            scanner.nextLine(); // Consumă newline

            // Selectarea numărului de persoane în echipă
            System.out.print("Introduceți numărul de persoane din echipă: ");
            int numarPersoane = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            // Calcularea tarifului pentru muncă (ținând cont de numărul de persoane)
            double tarifMunca = tarife.get(muncaSelectata);

            // Calcularea sumei pentru muncă
            double sumaMunca = tarifMunca * metriPatrati / numarPersoane;

            // Citirea numărului de ore de călătorie
            System.out.print("Introduceți numărul de ore pentru călătorie: ");
            int oreCalatorie = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            // Calcularea sumei pentru orele de călătorie
            double tarifOraCalatorie = 12.0;
            double sumaCalatorie = tarifOraCalatorie * oreCalatorie;

            // Citirea altor ore și descrieri
            Map<String, Integer> oreSuplimentare = new HashMap<>();
            System.out.print("Doriți să adăugați ore suplimentare? (da/nu): ");
            String raspuns = scanner.nextLine();
            while (raspuns.equalsIgnoreCase("da")) {
                System.out.print("Introduceți numărul de ore: ");
                int ore = scanner.nextInt();
                scanner.nextLine(); // Consumă newline
                System.out.print("Introduceți descrierea tipului de muncă: ");
                String descriere = scanner.nextLine();
                oreSuplimentare.put(descriere, ore);
                System.out.print("Doriți să mai adăugați ore suplimentare? (da/nu): ");
                raspuns = scanner.nextLine();
            }

            // Calcularea sumei pentru orele suplimentare
            double tarifOraSuplimentare = 12.0;
            double sumaOreSuplimentare = 0.0;
            for (Map.Entry<String, Integer> entry : oreSuplimentare.entrySet()) {
                int ore = entry.getValue();
                sumaOreSuplimentare += ore * tarifOraSuplimentare;
            }

            // Calcularea sumei totale
            double sumaTotala = sumaMunca + sumaCalatorie + sumaOreSuplimentare;

            // Store daily data
            String dailyInfo = "Data: " + data + ", Suma totală: " + sumaTotala;
            dailyData.put(data, sumaTotala);
            System.out.println("Informații salvate pentru ziua: " + data);
            System.out.println();

            // Afisarea sumei primite pentru lucrare
            System.out.println("Suma primită pentru lucrare este: " + sumaMunca + " Euro.");

            // Afisarea sumei primite pentru orele de călătorie
            System.out.println("Suma primită pentru orele de călătorie este: " + sumaCalatorie + " Euro.");

            // Afisarea sumei primite pentru orele suplimentare
            System.out.println("Suma primită pentru orele suplimentare este: " + sumaOreSuplimentare + " Euro.");

            // Afisarea sumei totale
            System.out.println("Suma totală primită pentru munca ta este: " + sumaTotala + " Euro.");
            System.out.println();
        }

        // Display monthly summary
        System.out.println("Rezumat lunar:");
        System.out.println("==============");
        double totalSumaLunara = 0.0;
        for (Map.Entry<String, Double> entry : dailyData.entrySet()) {
            String data = entry.getKey();
            double sumaTotala = entry.getValue();
            totalSumaLunara += sumaTotala;
            System.out.println("Data: " + data + ", Suma totală: " + sumaTotala + " Euro.");
        }
        System.out.println("Suma totală pentru luna curentă: " + totalSumaLunara + " Euro.");
    }
}
