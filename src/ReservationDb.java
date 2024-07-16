import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ReservationDb {
    private static HashMap<TimeSchedule.Schedule, ArrayList<Reservation>> reservationDb = new HashMap<TimeSchedule.Schedule, ArrayList<Reservation>>();

    public static void addReservation(final TimeSchedule.Schedule SCHEDULE, final Reservation RESERVATION) {
        // check if particular schedule is already win the database
        if (!reservationDb.containsKey(SCHEDULE))
            reservationDb.put(SCHEDULE, new ArrayList<>());

        ArrayList<Reservation> tempArray = reservationDb.get(SCHEDULE);
        if (tempArray != null)
            tempArray.add(RESERVATION);
    }

    public static void deleteReservation(final TimeSchedule.Schedule SCHEDULE, final Reservation RESERVATION) {
        if (!reservationDb.containsKey(SCHEDULE)) {
            System.out.println("Reservation not found.");
            return;
        }

        ArrayList<Reservation> tempArray = reservationDb.get(SCHEDULE);
        if (tempArray != null)
            tempArray.remove(RESERVATION);
        if (tempArray.size() < 1)               // remove key with arraylist associated with it that has no more elements
            reservationDb.remove(SCHEDULE);
    }

    public static ArrayList<Reservation> retrieveReservationList(final TimeSchedule.Schedule SCHEDULE) {
        return (reservationDb.getOrDefault(SCHEDULE, null));
    }

    public static ArrayList<Reservation> findReservation(final TimeSchedule.Schedule SCHEDULE, final Reservation RESERVATION) {
        if (!reservationDb.containsKey(SCHEDULE)) {
            System.out.println("Reservation not found.");
            return null;
        }

        ArrayList<Reservation> tempArray = reservationDb.get(SCHEDULE);
        if (tempArray != null)
            Collections.sort(tempArray);
        final int INDEX = tempArray.indexOf(RESERVATION);
        if (INDEX < 0) {
            System.err.println("Reservation not found.");
            return null;
        }
        return tempArray;
    }

    public static void displayAllReservations() {
       for (Map.Entry<TimeSchedule.Schedule, ArrayList<Reservation>> entry : reservationDb.entrySet()) {
            ArrayList<Reservation> tempArray = entry.getValue();
            System.out.println(entry.getKey());
            for (Reservation reservation : tempArray)
                System.out.println(reservation);
        }
    }
}
