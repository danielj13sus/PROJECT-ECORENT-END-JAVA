
package entities;

public class Tool extends Equipment {

    public Tool() {
        super();
    }

    public Tool(String model, Double dailyPrice) {
        super(model, dailyPrice);
    }

    @Override
    public double totalCost(int days) {
        return super.totalCost(days);
    }
}
