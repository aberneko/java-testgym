import java.util.Objects;

public class TimeOfDay implements Comparable<TimeOfDay>{

    //часы (от 0 до 23)
    private final  int hours;
    //минуты (от 0 до 59)
    private final  int minutes;

    public TimeOfDay(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public int compareTo(TimeOfDay timeOfDay) {
       int number = Integer.compare(this.hours, timeOfDay.hours);
       if (number == 0) {
           return Integer.compare(this.minutes, timeOfDay.minutes);
       }
       return number;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        TimeOfDay timeOfDay = (TimeOfDay) obj;
        return hours == timeOfDay.hours && minutes == timeOfDay.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes);

    }



}