
package entities;

public class HeavyEquipment extends Equipment {

    private Double transportFee;

    public HeavyEquipment() {
        super();
    }

    public HeavyEquipment(String model, Double dailyPrice, Double transportFee) {
        super(model, dailyPrice);
        this.transportFee = transportFee;
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
}
