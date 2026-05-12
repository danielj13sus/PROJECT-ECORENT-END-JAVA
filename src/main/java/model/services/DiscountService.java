package model.services;

import org.springframework.stereotype.Component;

@Component
public interface DiscountService {

    double applyDiscount(double baseValue, int days);
}
