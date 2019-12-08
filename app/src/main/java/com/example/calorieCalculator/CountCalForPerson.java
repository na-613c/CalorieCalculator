package com.example.calorieCalculator;

public class CountCalForPerson {

    private static int cal = 0;
    private static int weight;
    private static int height;
    private static int age;
    private static double calDouble;

    public static int countCal(){
        weight = MainActivity.personObj.getWeight();
        height = MainActivity.personObj.getHeight();
        age = MainActivity.personObj.getAge();

        calDouble = 655.1 + 9.563*weight + 1.85*height - 4.676*age;

        cal = (int)calDouble;
        return  cal;
    }

}
