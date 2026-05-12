
package model.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Tool extends Equipment {

    public Tool() {
        super();
    }

    @Autowired
    public Tool(String model, Double dailyPrice) {
        super(model, dailyPrice);
    }
}
