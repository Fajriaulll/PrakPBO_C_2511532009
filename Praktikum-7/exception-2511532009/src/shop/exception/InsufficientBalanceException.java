package shop.exception;

/** A payment failure caused by a balance or credit limit that is too small. */
public class InsufficientBalanceException extends PaymentFailedException {
    private static final long serialVersionUID = 1L;

    private final long required;
    private final long available;

    public InsufficientBalanceException(long required, long available) {
        super("Saldo tidak cukup: perlu Rp" + required + ", tersedia Rp" + available);
        this.required = required;
        this.available = available;
    }

    public long getRequired() { return required; }
    public long getAvailable() { return available; }

    /** How much money is missing. */
    public long getShortfall() { return required - available; }
}
