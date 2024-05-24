import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PaymentProcessor {
    public static PaymentState determinePaymentState(List<Event> events) {
        AtomicInteger expectedAmount = new AtomicInteger(0);
        AtomicInteger receivedAmount = new AtomicInteger(0);
        AtomicBoolean isCancelled = new AtomicBoolean(false);

        events.forEach(event -> {
            switch (event.eventType()) {
                case PAYMENT_CREATED -> expectedAmount.set(event.amount());
                case TRANSFER_RECEIVED -> receivedAmount.addAndGet(event.amount());
                case PAYMENT_CANCELLED -> isCancelled.set(true);
            }
        });

        return isCancelled.get() ? PaymentState.CANCELLED
                : receivedAmount.get() == 0 ? PaymentState.NEW
                : receivedAmount.get() >= expectedAmount.get() ? PaymentState.PAID
                : PaymentState.PARTIALLY_PAID;
    }
}
