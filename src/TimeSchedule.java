import java.time.LocalTime;
import java.util.Objects;
import java.util.TreeMap;

public class TimeSchedule {
    private static final TreeMap<Integer, Schedule> timeSchedules = new TreeMap<>();

    public static final int MIN_INDEX = 1;
    public static final int MAX_INDEX = 7;

    public static class Schedule {
        LocalTime startTime;
        LocalTime endTime;

        public Schedule(final LocalTime START_TIME, final LocalTime END_TIME) {
            this.startTime = START_TIME;
            this.endTime = END_TIME;
        }

        @Override
        public String toString() {
            return "Start = " + this.startTime + ", End = " + this.endTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (o == null || getClass() != o.getClass())
                return false;

            Schedule schedule = (Schedule) o;
            return Objects.equals(startTime, schedule.startTime) &&
                    Objects.equals(endTime, schedule.endTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startTime, endTime);
        }
    }

    static {
        timeSchedules.put(MIN_INDEX, new Schedule(LocalTime.of(9, 0), LocalTime.of(10, 0)));
        timeSchedules.put(2, new Schedule(LocalTime.of(9, 0), LocalTime.of(10, 0)));
        timeSchedules.put(3, new Schedule(LocalTime.of(10, 0), LocalTime.of(11, 0)));
        timeSchedules.put(4, new Schedule(LocalTime.of(11, 0), LocalTime.of(12, 0)));
        timeSchedules.put(5, new Schedule(LocalTime.of(13, 0), LocalTime.of(14, 0)));
        timeSchedules.put(6, new Schedule(LocalTime.of(14, 0), LocalTime.of(15, 0)));
        timeSchedules.put(MAX_INDEX, new Schedule(LocalTime.of(15, 0), LocalTime.of(16, 0)));
    }

    public static Schedule getSchedule(final int INDEX) {
        return timeSchedules.getOrDefault(INDEX, null);
    }

    public static void printSchedule() {
        for (Integer key : timeSchedules.keySet()) {
            System.out.println("[" + key + "] " + timeSchedules.get(key));
        }
    }


}
