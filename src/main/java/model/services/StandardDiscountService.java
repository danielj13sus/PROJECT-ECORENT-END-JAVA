package model.services;

import org.springframework.stereotype.Component;

@Component

public class StandardDiscountService implements DiscountService {

    @Override
    public double applyDiscount(double baseValue, int days) {
        if (days > 7) {
           return baseValue * 0.90;
        }
        return baseValue;
    }
}
