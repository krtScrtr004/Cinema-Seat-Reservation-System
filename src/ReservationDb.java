import java.util.HashSet;

public class ReservationDb {
    private static HashSet<Reservation> reservationDb = new HashSet<>();

    public static void addReservation(final Reservation RESERVATION) {
        reservationDb.add(RESERVATION);
    }

    public static void deleteReservation(final Reservation RESERVATION) {
        if (!reservationDb.contains(RESERVATION)) {
            System.out.println("Reservation not found.");
            return;
        }

        reservationDb.remove(RESERVATION);
    }

    public static Boolean findReservation(final Reservation RESERVATION) {
        return reservationDb.contains(RESERVATION);
    }

    public static void displayAllReservations() {
        for (Reservation reservation : reservationDb)
            System.out.println(reservation);
    }
}
