package application;

import entities.Equipment;
import entities.HeavyEquipment;
import entities.Rent;
import entities.Tool;

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
        Rent rent;

        char verif;
        do {
            System.out.println("--- NOVO ALUGUEL ---");
            System.out.print("É equipamento pesado? (s/n): ");
            char ch = scan.next().charAt(0);
            System.out.print("Digite o modelo do equipamento: ");
            scan.nextLine();
            String modelEquipment = scan.nextLine();
            System.out.print("Digite o preço da diária: ");
            double dailyPrice = scan.nextDouble();
            System.out.print("Quantos dias de aluguel? ");
            int daysRental = scan.nextInt();
            if (ch == 's') {
                System.out.print("Taxa de transporte: ");
                double transportFee = scan.nextDouble();
                equipment = new HeavyEquipment(modelEquipment, dailyPrice, transportFee);
            } else {
                equipment = new Tool(modelEquipment, dailyPrice);
            }
            rent = new Rent(equipment, daysRental);
            rent.calculateFinalPrice();
            rentals.add(rent);
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

