import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;
import java.util.Objects;

public class Reservation extends ReservationDb {
    private String customerEmail;
    private Integer seatNumber;
    private TimeSchedule.Schedule schedule;

    public Reservation() {
        this.customerEmail = null;
        this.seatNumber = null;
        this.schedule = null;
    }

    public Reservation(final String EMAIL, final Integer SEAT_NUMBER, final TimeSchedule.Schedule SCHEDULE) {
        this.customerEmail = EMAIL;
        this.seatNumber = SEAT_NUMBER;
        this.schedule = SCHEDULE;
    }

//    private static LocalTime parseToTime(final String TIME_STR) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        LocalTime time;
//        try {
//            time = LocalTime.parse(TIME_STR, formatter);
//        } catch (DateTimeParseException e) {
//            throw new IllegalStateException(e);
//        }
//
//        return time;
//    }

    public static Integer inputSeatNumber() {
        int tempSeatNumber;
        do {
            System.out.print("Enter the seat number: ");
            tempSeatNumber = InputReader.readInt();
        } while (tempSeatNumber < 1 || tempSeatNumber > 50);
        return tempSeatNumber;
    }

    public static TimeSchedule.Schedule inputScheduleIndex() {
        int scheduleIndex;
        do {
            System.out.print("Enter schedule index: ");
            scheduleIndex = InputReader.readInt();
        } while (scheduleIndex < TimeSchedule.MIN_INDEX ||
                scheduleIndex > TimeSchedule.MAX_INDEX);

        return TimeSchedule.getSchedule(scheduleIndex);
    }

    public final void setCustomerName(final String CUSTOMER_EMAIL) {
        this.customerEmail = CUSTOMER_EMAIL;
    }

    public final void setSeatNumber(final Integer SEAT_NUMBER) {
        this.seatNumber = SEAT_NUMBER;
    }

    public final void setSchedule(final TimeSchedule.Schedule SCHEDULE) {
        this.schedule = SCHEDULE;
    }


    @Override
    public final String toString() {
        return "Email " + this.customerEmail +
                ", Seat Number " + this.seatNumber + ", " +
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
}