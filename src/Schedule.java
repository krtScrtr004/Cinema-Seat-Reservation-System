import java.time.LocalTime;
import java.util.Objects;

public class Schedule {
    LocalTime startTime;
    LocalTime endTime;

    public Schedule(final LocalTime START_TIME, final LocalTime END_TIME) {
        this.startTime = START_TIME;
        this.endTime = END_TIME;
    }

    @Override
    public String toString() {
        return "Start: " + this.startTime + " End: " + this.endTime;
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