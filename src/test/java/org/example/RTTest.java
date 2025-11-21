package org.example;

import org.example.Model.Marriage;
import org.example.Model.Person;
import org.example.Repository.Repo;
import org.example.Service.FamilyTreeService;
import org.example.Service.PersonService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class RTTest {
    public static void main(String[] args) {
        String name = "test1";

        Repo repo = new Repo(name);
        PersonService ps = new PersonService(name);

        Person testP = new Person("Lakshmi", "MS", "MS",
                LocalDate.of(1954, Month.MAY, 19));

        List<Person> testParents = new ArrayList<>();
        testParents.add(new Person("Richard", "Drew",
                LocalDate.of(1918, 1, 1)));

        testParents.add(new Person("Lacy", "Simmons",
                LocalDate.of(1918, 7, 6)));


    }
}
