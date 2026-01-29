
package model.entities;

import model.exceptions.DomainExceptions;

public class HeavyEquipment extends Equipment {

    private Double transportFee;

    public HeavyEquipment() {
        super();
    }

    public HeavyEquipment(String model, Double dailyPrice, Double transportFee) {
        super(model, dailyPrice);
        this.transportFee = transportFee;
        validateExceptions();
    }

    public Double getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(Double transportFee) {
        this.transportFee = transportFee;
    }

    @Override
    public double totalCost(int days) {
        return super.totalCost(days) + transportFee;
    }

    @Override
    public void validateExceptions() {
        super.validateExceptions();
        if (transportFee <= 0) {
            throw new DomainExceptions("Valor de taxa de transporte inválido!");
        }
    }
}
