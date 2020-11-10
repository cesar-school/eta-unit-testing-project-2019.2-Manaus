package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class PersonTest {
    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        Person person = new Person();
        person.setName("Jon");
        person.setLastName("Snow");
        String expected = "Jon Snow";
        String actual = person.getName();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person person = new Person();
        person.setName("Jon");
        String expected = "Jon";
        String actual = person.getName();
        Assertions.assertEquals(expected,actual);
        
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person person = new Person();
        String expected = "Snow";
        person.setLastName("Snow");
        String actual = person.getName();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person person = new Person();
        String expected = "Name must be filled";
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> person.getName(), "Name must be filled");
        Assertions.assertEquals(expected, exception.getMessage());
    }


    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        Person person = new PersonStub();
        person.setBirthday(LocalDate.parse("1994-01-10"));
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        Person person = new PersonStub();
        person.setBirthday(LocalDate.parse("1994-09-22"));
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        Person person = new PersonStub();
        person.setBirthday(LocalDate.parse("1996-09-20"));
        Assertions.assertTrue(person.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        PersonFake personFake1 = new PersonFake();
        PersonFake personFake2 = new PersonFake();

        personFake1.setName("Maria");
        personFake1.setLastName("Santos");
        personFake1.setBirthday(LocalDate.parse("2010-09-01"));

        personFake2.setName("José");
        personFake2.setLastName("Santos");
        personFake2.setBirthday(LocalDate.parse("2008-01-01"));

        personFake1.addToFamilyFake(personFake2);

        int expected = personFake1.getFamilyList().size();
        int actual = 1;
        Assertions.assertEquals(actual,expected);
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        PersonFake personFake1 = new PersonFake();
        PersonFake personFake2 = new PersonFake();

        personFake1.setName("Maria");
        personFake1.setLastName("Santos");
        personFake1.setBirthday(LocalDate.parse("2010-09-01"));

        personFake2.setName("José");
        personFake2.setLastName("Santos");
        personFake2.setBirthday(LocalDate.parse("2008-01-01"));

        personFake1.addToFamilyFake(personFake2);

        int expected = personFake1.getFamilyList().size();
        int actual = 1;
        Assertions.assertEquals(actual,expected);
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        PersonFake personFake1 = new PersonFake();
        Person person = new Person();

        personFake1.setName("Maria");
        personFake1.setLastName("Santos");
        personFake1.setBirthday(LocalDate.parse("2010-09-01"));
        Assertions.assertFalse(person.isFamily(personFake1));
    }

    @Test
    public void isFamily_relativePerson_true() {
        PersonFake personFake1 = new PersonFake();
        Person person = new Person();

        personFake1.setName("Maria");
        personFake1.setLastName("Santos");
        personFake1.setBirthday(LocalDate.parse("2010-09-01"));
        person.addToFamily(personFake1);
        Assertions.assertTrue(person.isFamily(personFake1));
    }
}
