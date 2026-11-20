import java.util.*;

public class Timetable {

    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();
    private final Map<DayOfWeek, List<TrainingSession>> sessionsByDay = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();

        TreeMap<TimeOfDay, List<TrainingSession>> timeList = timetable.get(dayOfWeek);

        if(timeList == null) {
            timeList = new TreeMap<>();
            timetable.put(dayOfWeek, timeList);
        }

        List<TrainingSession> trainingSessionList = timeList.get(timeOfDay);

        if(trainingSessionList == null) {
            trainingSessionList = new ArrayList<>();
            timeList.put(timeOfDay, trainingSessionList);
        }

        trainingSessionList.add(trainingSession);

        List<TrainingSession> allSessionsForDay = new ArrayList<>();
        for (List<TrainingSession> list : timeList.values()) {
            allSessionsForDay.addAll(list);
        }

        sessionsByDay.put(dayOfWeek, allSessionsForDay);
        //сохраняем занятие в расписании
    }

    //сложность должна быть О(1)
    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        return sessionsByDay.getOrDefault(dayOfWeek, Collections.emptyList());
    }

    //сложность должна быть О(1)
    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        var daySchedule = timetable.get(dayOfWeek);
        return (timetable.get(dayOfWeek) == null) ? Collections.emptyList() : daySchedule.getOrDefault(timeOfDay, Collections.emptyList());
    }

}