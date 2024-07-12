import java.util.HashSet;
import java.util.Scanner;

public class ReservationDB {
    private static HashSet<Reservation> reservationDb = new HashSet<>();

    public static final void addReservation(final Reservation RESERVATION) {
        reservationDb.add(RESERVATION);
    }

    public static final void deleteReservation(final Reservation RESERVATION) {
        if (!reservationDb.contains(RESERVATION)) {
            System.out.println("Reservation not found.");
            return;
        }

        reservationDb.remove(RESERVATION);
    }

    public static final Boolean findReservation(final Reservation RESERVATION) {
        return reservationDb.contains(RESERVATION);
    }

    public static final void displayAllReservations() {
        for (Reservation reservation : reservationDb)
            System.out.println(reservation);
    }
}
