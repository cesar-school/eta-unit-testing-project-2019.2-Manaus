package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Assertions.*;
import sun.util.calendar.LocalGregorianCalendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PersonTest {
    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        Person person = new Person();
        person.setName("Jon");
        person.setLastName("Snow");
        String expected = "Jon Snow";
        String result = person.getName();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        Person person = new Person();
        person.setName("Jon");
        String expected = "Jon";
        String result = person.getName();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        Person person = new Person();
        person.setLastName("Snow");
        String expected = "Snow";
        String result = person.getName();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Person person = new Person();
        String expected = "Name must be filled";
        try {
            person.getName();
        }catch (RuntimeException ex){
            Assertions.assertTrue(ex.getMessage().contains(expected));
        }
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        Person person = new Person();
        LocalDate data = LocalDate.parse("2020-05-03");
        person.setBirthday(data);

        Assertions.assertFalse(person.isBirthdayToday());

    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        Person person = new Person();
        LocalDate data = LocalDate.parse("2020-10-01");
        person.setBirthday(data);

        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        Person person = new Person();
        LocalDate data = LocalDate.now();
        person.setBirthday(data);

        Assertions.assertTrue(person.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        PersonMock person1 = new PersonMock();
        person1.setName("Antonio");
        person1.setLastName("Fonseca");
        person1.setBirthday(LocalDate.parse("1990-07-17"));

        PersonMock person2 = new PersonMock();
        person2.setName("Bob");
        person2.setLastName("Fonseca");
        person2.setBirthday(LocalDate.parse("2013-01-25"));

        person1.addToFamily(person2);

        int result = person1.getFamilyList().size();

        Assertions.assertEquals(1,result);


    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        PersonMock person1 = new PersonMock();
        person1.setName("Antonio");
        person1.setLastName("Fonseca");
        person1.setBirthday(LocalDate.parse("1990-07-17"));

        PersonMock person2 = new PersonMock();
        person2.setName("Bob");
        person2.setLastName("Fonseca");
        person2.setBirthday(LocalDate.parse("2013-01-25"));

        person1.addToFamily(person2);

        int result = person2.getFamilyList().size();

        Assertions.assertEquals(1,result);
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person person = new Person();

        Person person1 = new Person();
        person1.setName("Sheila");
        person1.setLastName("Colares");
        person1.setBirthday(LocalDate.parse("1962-07-25"));


        Assertions.assertFalse(person.isFamily(person1));

    }

    @Test
    public void isFamily_relativePerson_true() {
        Person person = new Person();

        Person person1 = new Person();
        person1.setName("Sheila");
        person1.setLastName("Colares");
        person1.setBirthday(LocalDate.parse("1962-07-25"));

        person.addToFamily(person1);

        Assertions.assertTrue(person.isFamily(person1));

    }
}
