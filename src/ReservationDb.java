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
        if (!reservationDb.contains(SCHEDULE)) {
            System.out.println("Reservation not found.");
            return;
        }

        ArrayList<Reservation> tempArray = reservationDb.get(SCHEDULE);
        if (tempArray != null)
            tempArray.remove(RESERVATION);
    }

    public static ArrayList<Reservation> findReservation(final TimeSchedule.Schedule SCHEDULE, final Reservation RESERVATION) {
        if (!reservationDb.contains(SCHEDULE)) {
            System.out.println("Reservation not found.");
            return null;
        }

        ArrayList<Reservation> tempArray = reservationDb.get(SCHEDULE);
        if (tempArray != null)
            Collections.sort(tempArray);
        final int INDEX = tempArray.indexOf(RESERVATION);
        return ((INDEX > 0) ? tempArray : null);
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
