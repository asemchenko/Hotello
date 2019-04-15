package example.company.model.entity;

public class Bill extends Entity {
    private BillStatus status;

    // TODO
    public BillStatus getStatus() {
        // TODO
        throw new UnsupportedOperationException();
    }

    public enum BillStatus {PAYMENT_EXPECTED, PAID}
}
