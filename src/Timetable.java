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
        sessionsByDay.put(dayOfWeek, trainingSessionList);
        //сохраняем занятие в расписании
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        return sessionsByDay.getOrDefault(dayOfWeek, Collections.emptyList());
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
    }

//    public непонятно, что возвращать getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
//        //как реализовать, тоже непонятно, но сложность должна быть О(1)
//    }

}