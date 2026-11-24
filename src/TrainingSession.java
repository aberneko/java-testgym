import java.util.Objects;

public class TrainingSession {

    //группа
    private final Group group;
    //тренер
    private final Coach coach;
    //день недели
    private final DayOfWeek dayOfWeek;
    //время начала занятия
    private final TimeOfDay timeOfDay;

    public TrainingSession(Group group, Coach coach, DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        this.group = group;
        this.coach = coach;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
    }

    public Group getGroup() {
        return group;
    }

    public Coach getCoach() {
        return coach;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        TrainingSession trainingSession = (TrainingSession) obj;
        return Objects.equals(group, trainingSession.group) &&
                Objects.equals(coach, trainingSession.coach) &&
                Objects.equals(dayOfWeek, trainingSession.dayOfWeek) &&
                Objects.equals(timeOfDay, trainingSession.timeOfDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, coach, dayOfWeek, timeOfDay);
    }
}