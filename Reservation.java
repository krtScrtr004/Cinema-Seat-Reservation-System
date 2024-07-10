import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;
import java.util.Scanner;

public class Reservation {
    private Integer seatNumber;
    private LocalTime startTime;
    private LocalTime endTime;

    public Reservation() {
        seatNumber = null;
        startTime = null;
        endTime = null;
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
        try (Scanner sc = new Scanner(System.in)) {
            do {
                System.out.println("Enter the seat number: ");
                tempSeatNumber = sc.nextInt();
            } while (tempSeatNumber < 1 || tempSeatNumber > 50);
        }
        return tempSeatNumber;
    }

    public static LocalTime inputStartTime() {
        LocalTime startTime = null;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the start time (HH:MM 24-Hour Format): ");
            String tempStartTime = sc.next();
            startTime = parseToTime(tempStartTime);
        }
        return startTime;
    }

    public static final LocalTime inputEndTime() {
        LocalTime endTime = null;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the end time (HH:MM 24-Hour Format): ");
            String tempEndTime = sc.next();
            endTime = parseToTime(tempEndTime);
        }
        return endTime;
    }

    // TODO: Unique time identifier methods

    public final void setSeatNumber(final Integer SEAT_NUMBER) {
        this.seatNumber = SEAT_NUMBER;
    }

    public final void setStartTime(final LocalTime START_TIME) {
        this.startTime = START_TIME;
    }

    public final void setEndTime(final LocalTime END_TIME) {
        this.endTime = END_TIME;
    }

    public final Integer getSeatNumber() {
        return this.seatNumber;
    }

    public final LocalTime getStartTime() {
        return this.startTime;
    }

    public final LocalTime getEndTime() {
        return this.endTime;
    }
}