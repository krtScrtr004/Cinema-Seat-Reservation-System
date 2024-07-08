import java.util.ArrayList;
import java.util.Collections;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;
import java.util.Scanner;

public class SeatReservationSystem {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Reservation> reservationsDb = new ArrayList<Reservation>();
    private ArrayList<User> usersDb = new ArrayList<User>();

    static private class Seats {
        private static Integer[][] seats = new Integer[5][10];

        public static Boolean isFull() {
            for (Integer[] row : seats) {
                for (Integer i : row) {
                    if (i != null)
                        return false;
                }
            }

            return true;
        }

        public static Boolean isReserved(final int seatNumber) {
            if (seatNumber < 1 || seatNumber > 50)
                throw new IllegalArgumentException("Invalid seat number");

            int row, column;
            if (seatNumber == 10 || seatNumber == 20
                    || seatNumber == 30 || seatNumber == 40
                    || seatNumber == 50) {
                row = (seatNumber / 10) - 1;
                column = 9;
            } else {
                row = (seatNumber / 10 <= 0 ? 0 : seatNumber % 10);
                column = (seatNumber % 10) - 1;
            }

            return seats[row][column] != null;
        }

        public static void reserve(final int seatNumber) {
            if (seatNumber < 1 || seatNumber > 50)
                throw new IllegalArgumentException("Invalid seat number");

            int row, column;
            if (seatNumber == 10 || seatNumber == 20
                    || seatNumber == 30 || seatNumber == 40
                    || seatNumber == 50) {
                row = (seatNumber / 10) - 1;
                column = 9;
            } else {
                row = (seatNumber / 10 <= 0 ? 0 : seatNumber % 10);
                column = (seatNumber % 10) - 1;
            }

            seats[row][column] = 1;
        }

        public static void cancel(final int seatNumber) {
            if (seatNumber < 1 || seatNumber > 50)
                throw new IllegalArgumentException("Invalid seat number");

            int row, column;
            if (seatNumber == 10 || seatNumber == 20
                    || seatNumber == 30 || seatNumber == 40
                    || seatNumber == 50) {
                row = (seatNumber / 10) - 1;
                column = 9;
            } else {
                row = (seatNumber / 10 <= 0 ? 0 : seatNumber % 10);
                column = (seatNumber % 10) - 1;
            }

            seats[row][column] = null;
        }

        public static void displaySeats() {
            for (Integer[] row : seats) {
                for (Integer i : row) {
                    if (i == null)
                        System.out.print("0 ");
                    else
                        System.out.print("1 ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public class Reservation implements Comparable<Reservation> {
        private Integer seatNumber;
        private LocalTime startTime;
        private LocalTime endTime;

        public Reservation() {
            seatNumber = null;
            startTime = null;
            endTime = null;
        }

        private LocalTime parseToTime(final String timeStr) {
            LocalTime time = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            try {
                time = LocalTime.parse(timeStr, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalStateException(e);
            }

            return time;
        }

        public void inputSeatNumber() {
            Integer tempSeatNumber = null;
            do {
                System.out.println("Enter the seat number: ");
                tempSeatNumber = sc.nextInt();
            } while (tempSeatNumber < 1 || tempSeatNumber > 50);
            seatNumber = tempSeatNumber;
        }

        public LocalTime inputStartTime() {
            System.out.println("Enter the start time (HH:MM): ");
            String tempStartTime = sc.next();
            LocalTime startTime = parseToTime(tempStartTime);

            return startTime;
        }

        public LocalTime inputEndTime() {
            System.out.println("Enter the end time (HH:MM): ");
            String tempEndTime = sc.next();
            LocalTime endTime = parseToTime(tempEndTime);

            return endTime;
        }

        public void setSeatNumber(final Integer seatNumber) {
            this.seatNumber = seatNumber;
        }

        public void setStartTime(final LocalTime startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(final LocalTime endTime) {
            this.endTime = endTime;
        }

        public Integer getSeatNumber() {
            return this.seatNumber;
        }

        public LocalTime getStartTime() {
            return this.startTime;
        }

        public LocalTime getEndTime() {
            return this.endTime;
        }

        @Override
        public int compareTo(Reservation other) {
            return this.startTime.compareTo(other.startTime);
        }
    }

    public static void main(String[] args) {
        sc.close();
    }
}
