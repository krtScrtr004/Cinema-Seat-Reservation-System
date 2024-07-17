import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ReservationDb {
    private static final HashMap<Schedule, ArrayList<Reservation>> reservationDb;

    static {
        reservationDb = new HashMap<>();
    }

    public static void addReservation(final Schedule SCHEDULE, final Reservation RESERVATION) {
        // check if particular schedule is already win the database
        if (!reservationDb.containsKey(SCHEDULE))
            reservationDb.put(SCHEDULE, new ArrayList<>());

        ArrayList<Reservation> tempArray = reservationDb.get(SCHEDULE);
        if (tempArray != null)
            tempArray.add(RESERVATION);
    }

    public static void deleteReservation(final Schedule SCHEDULE, final Reservation RESERVATION) {
        if (!reservationDb.containsKey(SCHEDULE)) {
            System.out.println("Reservation not found.");
            return;
        }

        ArrayList<Reservation> tempArray = reservationDb.get(SCHEDULE);
        if (tempArray != null)
            tempArray.remove(RESERVATION);
        if ((tempArray == null) || (tempArray.isEmpty()))               // remove key with arraylist associated with it that has no more elements
            reservationDb.remove(SCHEDULE);
    }

    public static ArrayList<Reservation> retrieveReservationList(final Schedule SCHEDULE) {
        return (reservationDb.getOrDefault(SCHEDULE, null));
    }

    public static ArrayList<Reservation> findReservation(final Schedule SCHEDULE, final Reservation RESERVATION) {
        if (!reservationDb.containsKey(SCHEDULE)) {
            System.out.println("Reservation not found.");
            return null;
        }

        ArrayList<Reservation> tempArray = reservationDb.get(SCHEDULE);
        if (tempArray != null)
            Collections.sort(tempArray);
        else
            return null;

        if (Collections.binarySearch(tempArray, RESERVATION) < 0) {
            System.err.println("Reservation not found.");
            return null;
        }
        return tempArray;
    }

//    public static void displayAllReservations() {
//       for (Map.Entry<Schedule, ArrayList<Reservation>> entry : reservationDb.entrySet()) {
//            ArrayList<Reservation> tempArray = entry.getValue();
//            System.out.println(entry.getKey());
//            for (Reservation reservation : tempArray)
//                System.out.println(reservation);
//        }
//    }
}
