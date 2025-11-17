package org.example.Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    static Person test;

    @BeforeAll
    public static void before() {
        test = new Person(1, "Nancy", "Claire", "Drew", LocalDate.of(1954, Month.MAY,
                19));
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
}
