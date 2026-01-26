
package entities;

public class Rent {

    private Equipment equipment;
    private Integer days;
    private Double total;

    public Rent() {
    }

    public Rent(Equipment equipment, Integer days) {
        this.equipment = equipment;
        this.days = days;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void calculateFinalPrice() {
        double baseValue = equipment.totalCost(days);
        if (days > 7) {
            baseValue *= 0.90;
        }
        this.total = baseValue;
    }

    @Override
    public String toString() {
        return "Dados do aluguel: " + "\n" +
                "Modelo: " + equipment.getModel() + "\n" +
                String.format("Preço diária: %.2f%n", equipment.getDailyPrice()) +
                "Dias: " + days + "\n" +
                String.format("Valor total a pagar: R$ %.2f%n", total);
    }
}