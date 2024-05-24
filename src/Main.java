import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Event> events1 = List.of(
                new Event(EventType.PAYMENT_CREATED, 3),
                new Event(EventType.TRANSFER_RECEIVED, 3)
        );
        System.out.println(PaymentProcessor.determinePaymentState(events1)); //should print PAID

        List<Event> events2 = List.of(
                new Event(EventType.PAYMENT_CREATED, 3),
                new Event(EventType.TRANSFER_RECEIVED, 1),
                new Event(EventType.TRANSFER_RECEIVED, 2)
        );
        System.out.println(PaymentProcessor.determinePaymentState(events2)); //should print PAID

        List<Event> events3 = List.of(
                new Event(EventType.PAYMENT_CREATED, 3),
                new Event(EventType.TRANSFER_RECEIVED, 1),
                new Event(EventType.TRANSFER_RECEIVED, 1)
        );
        System.out.println(PaymentProcessor.determinePaymentState(events3)); //should print PARTIALLY_PAID

        List<Event> events4 = List.of(
                new Event(EventType.PAYMENT_CREATED, 3),
                new Event(EventType.TRANSFER_RECEIVED, 1),
                new Event(EventType.TRANSFER_RECEIVED, 1),
                new Event(EventType.PAYMENT_CANCELLED, 0)
        );
        System.out.println(PaymentProcessor.determinePaymentState(events4)); //should print CANCELLED

        List<Event> events5 = List.of(
                new Event(EventType.PAYMENT_CREATED, 3)
        );
        System.out.println(PaymentProcessor.determinePaymentState(events5));
    }

//Imagine a system, which manages payments. Each payment is represented as a series of events. The event can be one of 3 kinds:
//            * payment created(expected amount) – represent the fact that payment is requested and the system will expected transfers for the given amount)
//            * transfer received (amount) – represents incoming transfer to cover the payment. The received amount can be lower than the payment's expected amount. This event can be received multiple times.
//            * payment cancelled
//
//    Implement a function, which accepts list of such events (you can assume they are already sorted in chronological order) and determines the whole payment's state:
//            * if the payment has been requested, and nothing else happened – the state should be NEW
//* if transactions received cover the full requested amount – the state should be PAID
//* if transactions received cover less than the requested one – the state should be PARTIALLY_PAID
//* if payment cancelled event is present – the state should be CANCELLED
//
//            EXAMPLES
//* for events: payment created(3), transfer received(3) – the state should be PAID
//* for events: payment created(3), transfer received(1), transfer received(2) – the state should be PAID
//* for events: payment created(3), transfer received(1), transfer received(1) – the state should be PARTIALLY_PAID
//* for events: payment created(3), transfer received(1), transfer received(1), payment cancelled – the state should be CANCELLED
//
//    Provide your implementation in Java of the function and the events. You can make your own assumptions – just tell us what they were. You can use any JDK version you prefer.
}