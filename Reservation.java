import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;
import java.util.Objects;

public class Reservation extends ReservationDB {
    private String customerName;
    private Integer seatNumber;
    private LocalTime startTime;
    private LocalTime endTime;

    public Reservation() {
        this.seatNumber = null;
        this.startTime = null;
        this.endTime = null;
    }

    public Reservation(final String CUSTOMER_NAME, final Integer SEAT_NUMBER, final String START_TIME,
            final String END_TIME) {
        this.customerName = CUSTOMER_NAME;
        this.seatNumber = SEAT_NUMBER;
        this.startTime = parseToTime(START_TIME);
        this.endTime = parseToTime(END_TIME);
    }

    private static final LocalTime parseToTime(final String TIME_STR) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = null;
        try {
            time = LocalTime.parse(TIME_STR, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalStateException(e);
        }

        return time;
    }

    public static final Integer inputSeatNumber() {
        Integer tempSeatNumber = null;
        do {
            System.out.println("Enter the seat number: ");
            tempSeatNumber = InputReader.readInt();
        } while (tempSeatNumber < 1 || tempSeatNumber > 50);
        return tempSeatNumber;
    }

    public static LocalTime inputStartTime() {
        System.out.println("Enter the start time (HH:MM 24-Hour Format): ");
        String tempStartTime = InputReader.readString();
        return parseToTime(tempStartTime);
    }

    public static final LocalTime inputEndTime() {
        System.out.println("Enter the end time (HH:MM 24-Hour Format): ");
        String tempEndTime = InputReader.readString();
        return parseToTime(tempEndTime);
    }

    public final void setCustomerName(final String CUSTOMER_NAME) {
        this.customerName = CUSTOMER_NAME;
    }

    public final void setSeatNumber(final Integer SEAT_NUMBER) {
        this.seatNumber = SEAT_NUMBER;
    }

    public final void setStartTime(final LocalTime START_TIME) {
        this.startTime = START_TIME;
    }

    public final void setEndTime(final LocalTime END_TIME) {
        this.endTime = END_TIME;
    }

    // public final Integer getSeatNumber() {
    // return this.seatNumber;
    // }

    // public final LocalTime getStartTime() {
    // return this.startTime;
    // }

    // public final LocalTime getEndTime() {
    // return this.endTime;
    // }

    @Override
    public final String toString() {
        return "Name = '" + this.customerName +
                "', Seat Number = " + this.seatNumber +
                "', StartTime = " + this.startTime +
                ", EndTime = " + this.endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Reservation reservation = (Reservation) o;
        return Objects.equals(seatNumber, reservation.seatNumber) &&
                Objects.equals(startTime, reservation.startTime) &&
                Objects.equals(endTime, reservation.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatNumber, startTime, endTime);
    }
}