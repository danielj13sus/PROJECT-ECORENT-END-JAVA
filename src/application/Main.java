package application;

import entities.Equipment;
import entities.Rent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);

        List<Rent> rentals = new ArrayList<>();
        Equipment equipment;
        Rent r1;

        char verif;
        do {
            System.out.println("--- NOVO ALUGUEL ---");
            System.out.print("Digite o modelo do equipamento: ");
            String modelEquipment = scan.nextLine();
            System.out.print("Digite o preço da diária: ");
            double dailyPrice = scan.nextDouble();
            System.out.print("Quantos dias de aluguel? ");
            int daysRental = scan.nextInt();
            equipment = new Equipment(modelEquipment, dailyPrice);
            r1 = new Rent(equipment, daysRental);
            r1.calculateFinalPrice();
            rentals.add(r1);
            System.out.println("(Adicionado à lista!)");
            System.out.print("Deseja registrar mais um aluguel? (s/n): ");
            verif = scan.next().charAt(0);
            scan.nextLine();
            System.out.println();
        } while (verif == 's');

        System.out.println("--- RELATÓRIO FINAL ---");
        for (Rent x: rentals) {
            int pos = rentals.indexOf(x);
            pos++;
            System.out.print(pos + ". " + x);
            System.out.println();
        }

        scan.close();

    }
}

