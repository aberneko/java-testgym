import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник вернулось одно занятие
        List<TrainingSession> mondayList = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        Assertions.assertEquals(1, mondayList.size());
        //Проверить, что за вторник не вернулось занятий
        List<TrainingSession> tuesdayList = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        Assertions.assertEquals(tuesdayList, Collections.emptyList());


    }

    @Test
    void testEqualsTrainingSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession singleTrainingSession2 = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);
        timetable.addNewTrainingSession(singleTrainingSession2);

        List<TrainingSession> mondayList = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        boolean isResult = mondayList.get(0).equals(mondayList.get(1));
        Assertions.assertTrue(isResult);
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession mondayChildTrainingSession2 = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(mondayChildTrainingSession2);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        List<TrainingSession> mondayList = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        Assertions.assertEquals(2, mondayList.size());
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        List<TrainingSession> thursdayList = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        List<TrainingSession> thursdayListOf = List.of(thursdayChildTrainingSession, thursdayAdultTrainingSession);
        Assertions.assertEquals(thursdayListOf, thursdayList);
        // Проверить, что за вторник не вернулось занятий
    }

//    @Test
//    void testGetTrainingSessionsForDayAndTime() {
//        Timetable timetable = new Timetable();
//
//        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
//        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
//        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
//                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

//        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        //Проверить, что за понедельник в 14:00 не вернулось занятий


}
