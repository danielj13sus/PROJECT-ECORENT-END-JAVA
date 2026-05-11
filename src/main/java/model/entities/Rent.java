
package model.entities;

import model.enums.RentStatus;
import model.exceptions.DomainExceptions;
import model.services.DiscountService;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Component

public class Rent {

    private Equipment equipment;
    private LocalDateTime start;
    private LocalDateTime finish;
    private Double total;

    private RentStatus status;
    private DiscountService discountService;

    public Rent() {
    }

    public Rent(DiscountService discountService, Equipment equipment, LocalDateTime start, LocalDateTime finish) {
        this.discountService = discountService;
        this.equipment = equipment;
        this.start = start;
        this.finish = finish;
        this.status = RentStatus.IN_PROGRESS;
        validateDates();
    }

    public RentStatus getStatus() {
        return status;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
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
        if (status == RentStatus.FINISHED) {
            double baseValue = equipment.totalCost(durationInDays());
            this.total = discountService.applyDiscount(baseValue, durationInDays());
        } if (status == RentStatus.CANCELED) {
            this.total = 0.0;
        }
    }

    public int durationInDays() {
        int days = (int) Duration.between(getStart(), getFinish()).toDays();
        return days > 0 ? days : 1;
    }

    public void validateDates() {
        if (!finish.isAfter(start)) {
            throw new DomainExceptions("A data de devolução deve ser posterior à data de retirada!");
        }
    }

    public void finishRent() {
        this.status = RentStatus.FINISHED;
        calculateFinalPrice();
    }

    public String stringReturn() {
        String returnTotal = status == RentStatus.FINISHED ? String.format("Valor total a pagar: R$ %.2f%n", total) : "Aluguel ainda não finalizado";
        return returnTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return Objects.equals(getTotal(), rent.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getTotal());
    }

    @Override
    public String toString() {
        return "Dados do aluguel: " + "\n" +
                "Modelo: " + equipment.getModel() + "\n" +
                String.format("Preço diária: %.2f%n", equipment.getDailyPrice()) +
                "Dias: " + durationInDays() + "\n" +
                "Status do aluguel: " + status.getDescricao() + "\n" +
                stringReturn();
    }
}