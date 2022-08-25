package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import model.Passenger;

public class PassengerAppl {
    public static void main(String[] args) {
        List<Passenger> passengers = new ArrayList<Passenger>();
        StringBuilder res = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader("train.csv"))){
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                  break;
                }
                if (line.startsWith("PassengerId")) {
                  continue;
                }
                passengers.add(new Passenger(line.split(",")));
              }
            } catch (IOException e) {
              e.printStackTrace();
            } catch (Exception e) {
              e.printStackTrace();
            }
        
        double totalFares = passengers.stream().mapToDouble(Passenger::getFare).reduce(0, (acc, fare) -> acc + fare);
        res.append("Total fares\t" + totalFares + "\n");
        
        double avFareClass1 = averageFareInPassClass(Integer.valueOf(1), passengers);
        double avFareClass2 = averageFareInPassClass(Integer.valueOf(2), passengers);
        double avFareClass3 = averageFareInPassClass(Integer.valueOf(3), passengers);

        res.append("\nAverage fare\n" + "1st class\t" + avFareClass1 + "\n2nd class\t"
        + avFareClass2 + "\n3d class\t" + avFareClass3 + "\n");

        int[] survived = new int[16];
            survived[0] = countSurviviors(passengers, p -> true, true);
            survived[1] = countSurviviors(passengers, p -> p.getGender().equals("male") && p.getAge() > 19, true);
            survived[2] = countSurviviors(passengers, p -> p.getGender().equals("female") && p.getAge() > 19, true);
            survived[3] = countSurviviors(passengers, p -> p.getAge() < 18, true);
            survived[4] = countSurviviors(passengers, p -> p.getPassengerClass().equals(1), true);
            survived[5] = countSurviviors(passengers, p -> p.getPassengerClass().equals(1) && p.getGender().equals("male") && p.getAge() > 19, true);
            survived[6] = countSurviviors(passengers, p -> p.getPassengerClass().equals(1) && p.getGender().equals("female") && p.getAge() > 19, true);
            survived[7] = countSurviviors(passengers, p -> p.getPassengerClass().equals(1) &&  p.getAge() < 18, true);
            survived[8] = countSurviviors(passengers, p -> p.getPassengerClass().equals(2), true);
            survived[9] = countSurviviors(passengers, p -> p.getPassengerClass().equals(2) && p.getGender().equals("male") && p.getAge() > 19, true);
            survived[10] = countSurviviors(passengers, p -> p.getPassengerClass().equals(2) && p.getGender().equals("female") && p.getAge() > 19, true);
            survived[11] = countSurviviors(passengers, p -> p.getPassengerClass().equals(2) &&  p.getAge() < 18, true);
            survived[12] = countSurviviors(passengers, p -> p.getPassengerClass().equals(3), true);
            survived[13] = countSurviviors(passengers, p -> p.getPassengerClass().equals(3) && p.getGender().equals("male") && p.getAge() > 19, true);
            survived[14] = countSurviviors(passengers, p -> p.getPassengerClass().equals(3) && p.getGender().equals("female") && p.getAge() > 19, true);
            survived[15] = countSurviviors(passengers, p -> p.getPassengerClass().equals(3) &&  p.getAge() < 18, true);
        
        int[] notSurvived = new int[16];
            notSurvived[0] = countSurviviors(passengers, p -> true, false);
            notSurvived[1] = countSurviviors(passengers, p -> p.getGender().equals("male") && p.getAge() > 19, false);
            notSurvived[2] = countSurviviors(passengers, p -> p.getGender().equals("female") && p.getAge() > 19, false);
            notSurvived[3] = countSurviviors(passengers, p -> p.getAge() < 18, false);
            notSurvived[4] = countSurviviors(passengers, p -> p.getPassengerClass().equals(Integer.valueOf(1)), false);
            notSurvived[5] = countSurviviors(passengers, p -> p.getPassengerClass().equals(1) && p.getGender().equals("male") && p.getAge() > 19, false);
            notSurvived[6] = countSurviviors(passengers, p -> p.getPassengerClass().equals(1) && p.getGender().equals("female") && p.getAge() > 19, false);
            notSurvived[7] = countSurviviors(passengers, p -> p.getPassengerClass().equals(1) &&  p.getAge() < 18, false);
            notSurvived[8] = countSurviviors(passengers, p -> p.getPassengerClass().equals(2), false);
            notSurvived[9] = countSurviviors(passengers, p -> p.getPassengerClass().equals(2) && p.getGender().equals("male") && p.getAge() > 19, false);
            notSurvived[10] = countSurviviors(passengers, p -> p.getPassengerClass().equals(2) && p.getGender().equals("female") && p.getAge() > 19, false);
            notSurvived[11] = countSurviviors(passengers, p -> p.getPassengerClass().equals(2) &&  p.getAge() < 18, false);
            notSurvived[12] = countSurviviors(passengers, p -> p.getPassengerClass().equals(3), false);
            notSurvived[13] = countSurviviors(passengers, p -> p.getPassengerClass().equals(3) && p.getGender().equals("male") && p.getAge() > 19, false);
            notSurvived[14] = countSurviviors(passengers, p -> p.getPassengerClass().equals(3) && p.getGender().equals("female") && p.getAge() > 19, false);
            notSurvived[15] = countSurviviors(passengers, p -> p.getPassengerClass().equals(3) &&  p.getAge() < 18, false);

            
        res.append("\nSurvived total\t" + survived[0] + "\nNot survived total\t" + notSurvived[0] +
        "\n\nSurvived men\t" + survived[1] + "\nNot survived men\t" + notSurvived[1] +
        "\n\nSurvived women\t" + survived[2] + "\nNot survived women\t" + notSurvived[2] +
        "\n\nSurvived children\t" + survived[3] + "\nNot survived children\t" + notSurvived[3] + "\n" +
        "\nSurvived total Cl1\t" + survived[4] + "\nNot survived total Cl1\t" + notSurvived[4] +
        "\nSurvived men Cl1\t" + survived[5] + "\nNot survived men Cl1\t" + notSurvived[5] +
        "\nSurvived women Cl1\t" + survived[6] + "\nNot survived women Cl1\t" + notSurvived[6] +
        "\nSurvived children Cl1\t" + survived[7] + "\nNot survived children Cl1\t" + notSurvived[7] + "\n" +
        "\nSurvived total Cl2\t" + survived[8] + "\nNot survived total Cl2\t" + notSurvived[8] +
        "\nSurvived men Cl2\t" + survived[9] + "\nNot survived men Cl2\t" + notSurvived[9] +
        "\nSurvived women Cl2\t" + survived[10] + "\nNot survived women Cl2\t" + notSurvived[10] +
        "\nSurvived children Cl2\t" + survived[11] + "\nNot survived children Cl2\t" + notSurvived[11] + "\n" +
        "\nSurvived total Cl3\t" + survived[12] + "\nNot survived total Cl3\t" + notSurvived[12] +
        "\nSurvived men Cl3\t" + survived[13] + "\nNot survived men Cl3\t" + notSurvived[13] +
        "\nSurvived women Cl3\t" + survived[14] + "\nNot survived women Cl3\t" + notSurvived[14] +
        "\nSurvived children Cl3\t" + survived[15] + "\nNot survived children Cl3\t" + notSurvived[15] + "\n"
        );
        
        System.out.println(res);
    
        try {
            PrintStream out = new PrintStream(new FileOutputStream("finalResult.txt"));
            out.println(res.toString());
            out.close();
    } catch (FileNotFoundException e) {
            e.printStackTrace();
    }
  }

    

   
    private static int countSurviviors(List<Passenger> passengers, Predicate<Passenger> predicate, boolean isSurvived) {
        return (int) passengers.stream()
        .filter(predicate)
        .filter(p -> p.isSurvived() == isSurvived
        )
        .count();
    }


    private static double averageFareInPassClass(Integer passengerClass, List<Passenger> passengers) {
        return passengers.stream()
        .filter(p -> p.getPassengerClass().equals(passengerClass))
        .mapToDouble(p -> p.getFare()).summaryStatistics().getAverage();
    }

}

