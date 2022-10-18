package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Main {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //напишите тут ваш код
        switch (args[0]){
            case "-c" -> create(args);
            case "-r", "-i" -> read(args);
            case "-u" -> update(args);
            case "-d" -> delete(args);
            default -> System.out.println("Some Error Occurs");
        }
    }

    public static void create(String[] args){
        for (int i = 1; i < args.length; i+=3) {
            String[] myArgs = {args[i], args[i+1], args[i+2]};
            Person p = createPerson(myArgs);
            allPeople.add(p);
            System.out.println(allPeople.indexOf(p));
        }
    }

    public static void read(String[] args){
        for (int i = 1; i < args.length; i++) {
            int id = Integer.parseInt(args[i]);
            System.out.println(allPeople.get(id).toString());
        }
    }
    public static void update(String[] args){
        for (int i = 1; i < args.length; i+=4) {
            String[] myArgs = {args[i+1], args[i+2], args[i+3]};
            int id = Integer.parseInt(args[i]);
            Person p = createPerson(myArgs);
            allPeople.set(id, p);
        }
    }
    public static void delete(String[] args){
        Person p = Person.createMale(null, null);
        p.setSex(null);
        for (int i = 1; i < args.length; i++) {
            int id = Integer.parseInt(args[i]);
            allPeople.set(id, p);
        }
    }

    private static Person createPerson(String[] args){
        //Person p;
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            date = format.parse(args[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (args[1].equalsIgnoreCase("м")){
            return Person.createMale(args[0], date);
        } else {
            return Person.createFemale(args[0], date);
        }
    }
}
