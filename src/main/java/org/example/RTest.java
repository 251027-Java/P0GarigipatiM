package org.example;

import org.example.Model.Marriage;
import org.example.Model.Person;
import org.example.Repository.Repo;
import org.example.Service.PersonService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class RTest {
    public static void main(String[] args) {
        Repo repo = new Repo("test");
        PersonService ps = new PersonService();
        Person testP = new Person(256, "Lakshmi", "MS", "MS",
                LocalDate.of(1954, Month.MAY, 19));
        List<Person> testParents = new ArrayList<>();
        testParents.add(new Person(44, "Richard", "Drew",
                LocalDate.of(1918, 1, 1)));
        testParents.add(new Person(45, "Lacy", "Simmons",
                LocalDate.of(1918, 7, 6)));
        ps.addPerson(testP, testParents);

    }
}
