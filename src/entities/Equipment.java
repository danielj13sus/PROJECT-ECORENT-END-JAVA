
package entities;

public abstract class Equipment {

    private String model;
    protected Double dailyPrice;

    public Equipment() {
    }

    public Equipment(String model, Double dailyPrice) {
        this.model = model;
        this.dailyPrice = dailyPrice;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public double totalCost(int days) {
        return dailyPrice * days;
    }
}