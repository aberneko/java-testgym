import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        Assertions.assertEquals(Collections.emptyList(), tuesdayList);
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
        List<TrainingSession> tuesdayList = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        Assertions.assertEquals(Collections.emptyList(), tuesdayList);
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession singleTrainingSession2 = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(14, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        List<TrainingSession> mondayList = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, singleTrainingSession.getTimeOfDay());
        Assertions.assertEquals(1, mondayList.size());
        //Проверить, что за понедельник в 14:00 не вернулось занятий
        List<TrainingSession> mondayList2 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, singleTrainingSession2.getTimeOfDay());
        Assertions.assertEquals(Collections.emptyList(), mondayList2);
        List<TrainingSession> sundayList = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.SUNDAY, singleTrainingSession2.getTimeOfDay());
        Assertions.assertEquals(Collections.emptyList(), sundayList);
    }

    // Свой тест
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
    void testMapCoachCountTrainingSessionWeek1() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Group group1 = new Group("Акробатика для взрослых", Age.ADULT, 60);
        Group group2 = new Group("Плаванье для детей", Age.CHILD, 60);
        Group group3 = new Group("Плаванье для взрослых", Age.ADULT, 60);
        Group group4 = new Group("Качалка для детей", Age.CHILD, 60);
        Group group5 = new Group("Качалка для взрослых", Age.ADULT, 60);
        Group group6 = new Group("Гимнастика для детей", Age.CHILD, 60);
        Group group7 = new Group("Гимнастика для взрослых", Age.ADULT, 60);
        Group group8 = new Group("Бег для детей", Age.CHILD, 60);
        Group group9 = new Group("Бег для взрослых", Age.ADULT, 60);

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Гребешёк", "Анатолий", "Вафилович");
        Coach coach3 = new Coach("Грубённок", "Степан", "Ростиславович");
        Coach coach4 = new Coach("Друлейко", "Арарат", "Кещанович");
        Coach coach5 = new Coach("Теньков", "Аркадий", "Междулорейтевич");

        TrainingSession singleTrainingSession = new TrainingSession(group, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession singleTrainingSession1 = new TrainingSession(group1, coach1,
                DayOfWeek.TUESDAY, new TimeOfDay(15, 0));
        TrainingSession singleTrainingSession2 = new TrainingSession(group2, coach2,
                DayOfWeek.WEDNESDAY, new TimeOfDay(10, 0));
        TrainingSession singleTrainingSession3 = new TrainingSession(group3, coach2,
                DayOfWeek.THURSDAY, new TimeOfDay(18, 0));
        TrainingSession singleTrainingSession4 = new TrainingSession(group4, coach3,
                DayOfWeek.FRIDAY, new TimeOfDay(19, 0));
        TrainingSession singleTrainingSession5 = new TrainingSession(group5, coach3,
                DayOfWeek.SUNDAY, new TimeOfDay(20, 0));
        TrainingSession singleTrainingSession6 = new TrainingSession(group6, coach3,
                DayOfWeek.SATURDAY, new TimeOfDay(17, 0));
        TrainingSession singleTrainingSession7 = new TrainingSession(group7, coach4,
                DayOfWeek.MONDAY, new TimeOfDay(14, 0));
        TrainingSession singleTrainingSession8 = new TrainingSession(group8, coach5,
                DayOfWeek.TUESDAY, new TimeOfDay(16, 0));
        TrainingSession singleTrainingSession9 = new TrainingSession(group9, coach5,
                DayOfWeek.TUESDAY, new TimeOfDay(16, 0));


        timetable.addNewTrainingSession(singleTrainingSession);
        timetable.addNewTrainingSession(singleTrainingSession1);
        timetable.addNewTrainingSession(singleTrainingSession2);
        timetable.addNewTrainingSession(singleTrainingSession3);
        timetable.addNewTrainingSession(singleTrainingSession4);
        timetable.addNewTrainingSession(singleTrainingSession5);
        timetable.addNewTrainingSession(singleTrainingSession6);
        timetable.addNewTrainingSession(singleTrainingSession7);
        timetable.addNewTrainingSession(singleTrainingSession8);
        timetable.addNewTrainingSession(singleTrainingSession9);

        List<Map.Entry<Coach, Integer>> mapCount = timetable.getCountByCoaches();

        Assertions.assertEquals(3, mapCount.get(0).getValue());
        Assertions.assertEquals(2, mapCount.get(1).getValue());
        Assertions.assertEquals(2, mapCount.get(2).getValue());
        Assertions.assertEquals(2, mapCount.get(3).getValue());
        Assertions.assertEquals(1, mapCount.get(4).getValue());

    }





}
