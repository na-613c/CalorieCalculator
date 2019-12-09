package com.example.calorieCalculator;

public class CountCalForPerson {

    private static int cal = 0;
    private static int weight;
    private static int height;
    private static int age;
    private static double calDouble;
    private static double activity;
    private static String gender;

    public static int countCal() {
        weight = MainActivity.personObj.getWeight();
        height = MainActivity.personObj.getHeight();
        age = MainActivity.personObj.getAge();
        activity = MainActivity.personObj.getActivity();
        gender = MainActivity.personObj.getGender();

        switch (gender) {
            case ("man"):
                calDouble = (88.36 + 13.4 * weight + 4.8 * height - 5.7 * age) * activity;
                break;
            case ("woman"):
                calDouble = (447.6 + 9.2 * weight + 3.1 * height - 4.3 * age) * activity;
                break;
        }

        cal = (int) calDouble;
        return cal;
    }

}
