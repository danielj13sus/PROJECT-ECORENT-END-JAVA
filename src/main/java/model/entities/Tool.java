
package model.entities;

import org.springframework.stereotype.Component;

@Component

public class Tool extends Equipment {

    public Tool() {
        super();
    }

    public Tool(String model, Double dailyPrice) {
        super(model, dailyPrice);
    }
}
