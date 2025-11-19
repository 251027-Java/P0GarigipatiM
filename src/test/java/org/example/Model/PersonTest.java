package org.example.Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    static Person test;
    static List<Person> testParents;

    @BeforeAll
    public static void before() {
        test = new Person(1, "Nancy", "Claire", "Drew", LocalDate.of(1954, Month.MAY,
                19));
        testParents = new ArrayList<>();
        testParents.add(new Person(44, "Richard", "Drew", LocalDate.of(1918, 1, 1)));
        testParents.add(new Person(45, "Lacy", "Simmons", LocalDate.of(1918, 7, 6)));
    }

    @Test
    public void testPersonCreation() {
        Person p = new Person(2, "John", "Simmons", LocalDate.of(1966, Month.APRIL, 20));
        assertEquals("John", p.getFirstName());
        assertEquals("Simmons", p.getLastName());
        assertEquals(LocalDate.of(1966, Month.APRIL, 20), p.getBirthDate());
        assertNull(p.getMiddleName());
    }

    @Test
    public void testChangingPersonValues() {
        test.setMiddleName("Bartholomew");
        assertEquals("Bartholomew", test.getMiddleName());

        test.setFirstName("Nathan");
        assertNotEquals("Nancy", test.getFirstName());

        assertEquals("Drew", test.getLastName());
        test.setLastName("Domer");
        assertEquals("Domer", test.getLastName());
    }

    @Test
    public void testParentBehavior() {
        test.addParents(testParents);
        assertEquals(44, test.parents.getFirst().getId());
        assertEquals(45, test.parents.getLast().getId());
    }
}
