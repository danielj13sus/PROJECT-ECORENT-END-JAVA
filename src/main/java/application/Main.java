package application;

import model.entities.Equipment;
import model.entities.HeavyEquipment;
import model.entities.Rent;
import model.entities.Tool;
import model.enums.RentStatus;
import model.exceptions.DomainExceptions;
import model.services.DiscountService;
import model.services.StandardDiscountService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.*;

@SpringBootApplication(scanBasePackages = "model")
public class Main {
    public static void main(String[] args) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);

        List<Rent> rentals = new ArrayList<>();
        Equipment equipment;
        Rent rent;

        char verif = 0;
        do {
            try {
                System.out.println("--- NOVO ALUGUEL ---");
                System.out.print("É equipamento pesado? (s/n): ");
                char ch = scan.next().charAt(0);
                System.out.print("Digite o modelo do equipamento: ");
                scan.nextLine();
                String modelEquipment = scan.nextLine();
                System.out.print("Digite o preço da diária: ");
                double dailyPrice = scan.nextDouble();
                System.out.print("Data de Retirada (dd/MM/yyyy HH:mm): ");
                scan.nextLine();
                LocalDateTime start = LocalDateTime.parse(scan.nextLine(), dtf);
                System.out.print("Data de Devolução (dd/MM/yyyy HH:mm): ");
                LocalDateTime finish = LocalDateTime.parse(scan.nextLine(), dtf);
                double transportFee;
                if (ch == 's') {
                    System.out.print("Taxa de transporte: ");
                    transportFee = scan.nextDouble();
                    equipment = new HeavyEquipment(modelEquipment, dailyPrice, transportFee);
                } else {
                    equipment = new Tool(modelEquipment, dailyPrice);
                }
                DiscountService discountService = new StandardDiscountService();
                rent = new Rent(discountService, equipment, start, finish);
                System.out.print("Deseja finalizar o aluguel agora? (s/n): ");
                char validStatus = scan.next().charAt(0);
                if (validStatus == 's'){
                    rent.finishRent();
                }
                rentals.add(rent);
                System.out.println("(Adicionado à lista!)");
                System.out.println();

                System.out.println("------------------------------");
                System.out.print("Deseja registrar mais um aluguel? (s/n): ");
                verif = scan.next().charAt(0);
                scan.nextLine();
                System.out.println();
            }
            catch (DomainExceptions e) {
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException e) {
                System.out.println("Erro na execução!");
                scan.nextLine();
            } catch (DateTimeParseException e) {
                System.out.println("Erro: Formato de data inválido!");
            }
        } while (verif == 's');

        System.out.println("==================================");
        System.out.println("--- DASHBOARD EXECUTIVO ---");
        System.out.println("==================================");

        double lucroTotal = rentals.stream()
                .filter(r -> r.getStatus() == RentStatus.FINISHED)
                .mapToDouble(Rent::getTotal)
                .sum();
        System.out.printf("Faturamento Total (Finalizados): R$ %.2f%n", lucroTotal);

        long totalPesadas = rentals.stream()
                .filter(r -> r.getEquipment() instanceof HeavyEquipment)
                .count();
        System.out.println("Total de Máquinas Pesadas Alugadas: " + totalPesadas);

        System.out.println("\n--- TOP ALUGUEIS MAIS CAROS (FINALIZADOS) ---");

        rentals.stream()
                .filter(r -> r.getStatus() == RentStatus.FINISHED)
                .sorted((r1, r2) -> r2.getTotal().compareTo(r1.getTotal()))
                .forEach(r -> {
                    System.out.println("- " + r.getEquipment().getModel() + ": R$ " + String.format("%.2f", r.getTotal()));
                });

        System.out.println("==================================\n");

        scan.close();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("summary.csv"))) {

            for (Rent he: rentals) {
                String totalCsv = (he.getTotal() != null) ? String.format("%.2f", he.getTotal()) : RentStatus.IN_PROGRESS.getDescricao();
                bw.write(he.getEquipment().getModel() + "," + totalCsv);
                bw.newLine();
            }

            System.out.println("Arquivo criado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro crítico ao gerar o relatório: " + e.getMessage());;
        }

        scan.close();

    }
}

