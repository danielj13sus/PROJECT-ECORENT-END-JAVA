
package model.entities;

import model.exceptions.DomainExceptions;
import model.services.DiscountService;

import java.time.Duration;
import java.time.LocalDateTime;

public class Rent {

    private Equipment equipment;
    private LocalDateTime start;
    private LocalDateTime finish;
    private Double total;

    private DiscountService discountService;

    public Rent() {
    }

    public Rent(DiscountService discountService, Equipment equipment, LocalDateTime finish, LocalDateTime start) {
        this.discountService = discountService;
        this.equipment = equipment;
        this.finish = finish;
        this.start = start;
        validateDates();
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void calculateFinalPrice() {
        double baseValue = equipment.totalCost(durationInDays());
        this.total = discountService.applyDiscount(baseValue, durationInDays());
    }

    public int durationInDays() {
        return (int) Duration.between(getStart(), getFinish()).toDays();
    }

    public void validateDates() {
        if (durationInDays() >= 0) {
            throw new DomainExceptions("Quantidade de dias inválido!");
        }
    }

    @Override
    public String toString() {
        return "Dados do aluguel: " + "\n" +
                "Modelo: " + equipment.getModel() + "\n" +
                String.format("Preço diária: %.2f%n", equipment.getDailyPrice()) +
                "Dias: " + durationInDays() + "\n" +
                String.format("Valor total a pagar: R$ %.2f%n", total);
    }
}