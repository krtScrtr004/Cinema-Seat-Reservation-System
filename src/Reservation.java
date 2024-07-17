import java.util.Objects;

public class Reservation extends ReservationDb implements Comparable<Reservation> {
    private final String customerEmail;
    private final Integer seatNumber;
    private final Schedule schedule;

//    public Reservation() {
//        this.customerEmail = null;
//        this.seatNumber = null;
//        this.schedule = null;
//    }

    public Reservation(final String EMAIL, final Integer SEAT_NUMBER, final Schedule SCHEDULE) {
        this.customerEmail = EMAIL;
        this.seatNumber = SEAT_NUMBER;
        this.schedule = SCHEDULE;
    }

    public static Integer inputSeatNumber() {
        boolean isValid = true;
        int tempSeatNumber;
        do {
            System.out.print("Enter the seat number: ");
            tempSeatNumber = InputReader.readInt();
            if (tempSeatNumber < 1 || tempSeatNumber > 50) {
                System.err.println("Seat number must be within the range of 1 to 50 only.");
                isValid = false;
            }
        } while (!isValid);
        return tempSeatNumber;
    }

    public static Schedule inputScheduleIndex() {
        boolean isValid = true;
        int scheduleIndex;
        do {
            System.out.print("Enter schedule index: ");
            scheduleIndex = InputReader.readInt();
            if (scheduleIndex < Main.ScheduleList.MIN_INDEX ||
                    scheduleIndex > Main.ScheduleList.MAX_INDEX) {
                System.err.println("Schedule index must be within the range of " + Main.ScheduleList.MIN_INDEX + " to " + Main.ScheduleList.MAX_INDEX + " only.");
                isValid = false;
            }
        } while (!isValid);
        return Main.ScheduleList.getSchedule(scheduleIndex);
    }

//    public final void setCustomerName(final String CUSTOMER_EMAIL) {
//        this.customerEmail = CUSTOMER_EMAIL;
//    }

//    public final void setSeatNumber(final Integer SEAT_NUMBER) {
//        this.seatNumber = SEAT_NUMBER;
//    }

//    public final void setSchedule(final Schedule SCHEDULE) {
//        this.schedule = SCHEDULE;
//    }

    public final Integer getSeatNumber() { return this.seatNumber; }

    public final Schedule getSchedule() { return this.schedule; }

    @Override
    public final String toString() {
        return "Email: " + this.customerEmail +
                " Seat Number " + this.seatNumber + " " +
                this.schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Reservation reservation = (Reservation) o;
        return Objects.equals(seatNumber, reservation.seatNumber) &&
                Objects.equals(schedule, reservation.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatNumber, schedule);
    }

    @Override
    public int compareTo(Reservation reservation) {
        return Integer.compare(this.seatNumber, reservation.seatNumber);
    }
}