package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        if(args.length <1) {
            System.out.println("input file path !!");
            System.exit(1);
        }
        String inputFile =args[0];
        File file = new File(inputFile);
        try {

            List<List<String>> records = new ArrayList<>();
            ArrayList<Person> people = new ArrayList<>();
//            open and close in try block
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                 br.readLine(); // read header line
                String line;
                while ((line = br.readLine()) != null) { // read input data
                    String[] values = line.split(",");
                    records.add(Arrays.asList(values));
                }
            }
            for( List<String> person : records){ // split data
                String firstName = person.get(0);
                String secondName = person.get(1);
                int score = Integer.parseInt(person.get(2));
                people.add( new Person(firstName ,secondName,score) );
            }
            getHighestScores(people);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private  static  void getHighestScores(List<Person> people){
        List<Person> sortedPeople = people.stream().sorted(Comparator.comparingInt(Person::getScore).reversed()).collect(Collectors.toList());
        int maxScore = sortedPeople.get(0).getScore();
        List<Person> highestScores = new ArrayList<>();
        for ( Person highest :sortedPeople){
            if( highest.getScore() == maxScore){
                highestScores.add(highest);
            }
        }
        if(highestScores.size() > 1){
            highestScores.sort(Comparator.comparing(Person::getFirstName));
            for(int i= 0 ; i <= highestScores.size()-1;i++){
                System.out.print(highestScores.get(i).firstName +"\t" );
            }

        }
        else {
            System.out.println(highestScores.get(0).firstName );
        }

        System.out.print("\n"+"score :"  + maxScore);

    }
    private  static class  Person{
        String firstName;
        String secondName;
        int score;

        public Person(String firstName, String secondName, int score) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.score = score;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getSecondName() {
            return secondName;
        }

        public void setSecondName(String secondName) {
            this.secondName = secondName;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", secondName='" + secondName + '\'' +
                    ", score='" + score + '\'' +
                    '}';
        }
    }
}
