package shop.payment;

import shop.exception.PaymentFailedException;

/** Contract for every payment method. */
public interface PaymentMethod {
    String getName();

    /** Pays the amount; returns normally on success. */
    void pay(long amount) throws PaymentFailedException;
}
