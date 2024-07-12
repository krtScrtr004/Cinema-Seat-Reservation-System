// import java.util.Scanner;

public class SeatReservationSystem {
    private static UserDB userDB = new UserDB();
    private static ReservationDB reservationDB = new ReservationDB();

    static private class Seats {
        private static Integer[][] seats = new Integer[5][10];

        public static final Boolean isFull() {
            for (Integer[] row : seats) {
                for (Integer i : row) {
                    if (i != null)
                        return false;
                }
            }

            return true;
        }

        public static final Boolean isReserved(final int SEAT_NUMBER) {
            if (SEAT_NUMBER < 1 || SEAT_NUMBER > 50)
                throw new IllegalArgumentException("Invalid seat number");

            int row, column;
            if (SEAT_NUMBER == 10 || SEAT_NUMBER == 20
                    || SEAT_NUMBER == 30 || SEAT_NUMBER == 40
                    || SEAT_NUMBER == 50) {
                row = (SEAT_NUMBER / 10) - 1;
                column = 9;
            } else {
                row = (SEAT_NUMBER / 10 <= 0 ? 0 : SEAT_NUMBER % 10);
                column = (SEAT_NUMBER % 10) - 1;
            }

            return seats[row][column] != null;
        }

        public static final void reserve(final int SEAT_NUMBER) {
            if (SEAT_NUMBER < 1 || SEAT_NUMBER > 50)
                throw new IllegalArgumentException("Invalid seat number");

            int row, column;
            if (SEAT_NUMBER == 10 || SEAT_NUMBER == 20
                    || SEAT_NUMBER == 30 || SEAT_NUMBER == 40
                    || SEAT_NUMBER == 50) {
                row = (SEAT_NUMBER / 10) - 1;
                column = 9;
            } else {
                row = (SEAT_NUMBER / 10 <= 0 ? 0 : SEAT_NUMBER % 10);
                column = (SEAT_NUMBER % 10) - 1;
            }

            seats[row][column] = 1;
        }

        public static final void cancel(final int SEAT_NUMBER) {
            if (SEAT_NUMBER < 1 || SEAT_NUMBER > 50)
                throw new IllegalArgumentException("Invalid seat number");

            int row, column;
            if (SEAT_NUMBER == 10 || SEAT_NUMBER == 20
                    || SEAT_NUMBER == 30 || SEAT_NUMBER == 40
                    || SEAT_NUMBER == 50) {
                row = (SEAT_NUMBER / 10) - 1;
                column = 9;
            } else {
                row = (SEAT_NUMBER / 10 <= 0 ? 0 : SEAT_NUMBER % 10);
                column = (SEAT_NUMBER % 10) - 1;
            }

            seats[row][column] = null;
        }

        public static final void displaySeats() {
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

    public static void main(String[] args) {
        Reservation reservation = new Reservation("Narcos", 12, "12:00", "13:00"); 
        Reservation reservation1 = new Reservation("Perwisyo", 22, "13:00", "14:00"); 

        ReservationDB.addReservation(reservation);
        ReservationDB.addReservation(reservation1);
        ReservationDB.displayAllReservations();

        ReservationDB.deleteReservation(reservation1);
        ReservationDB.displayAllReservations();
    }
}
