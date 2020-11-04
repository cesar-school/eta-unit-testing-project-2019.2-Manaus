package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

public class PersonTest {
    Person p;
    @BeforeEach
    public void setup(){
        p = new Person();
    }

    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        p.setName("Jon");
        p.setLastName("Snow");
        Assertions.assertEquals("Jon Snow" , p.getName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        p.setName("Jon");
        Assertions.assertEquals("Jon" , p.getName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        p.setLastName("Snow");
        Assertions.assertEquals("Snow" , p.getName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Assertions.assertThrows(RuntimeException.class,() -> new Person().getName() );
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {

        p = new StubPerson();
        p.setBirthday(LocalDate.parse("1996-05-18"));
        Assertions.assertFalse(p.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        p = new StubPerson();
        p.setBirthday(LocalDate.parse("1996-04-18"));
        Assertions.assertFalse(p.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        p = new StubPerson();
        p.setBirthday(LocalDate.parse("1996-04-17"));
        Assertions.assertTrue(p.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person p1 = new Person();
        p.addToFamily(p1);
        Assertions.assertTrue(p.isFamily(p1));
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person p1 = new Person();
        p.addToFamily(p1);
        Assertions.assertTrue(p.isFamily(p1) == true && p1.isFamily(p) == true);
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Assertions.assertFalse(new Person().isFamily(new Person()));
    }

    @Test
    public void isFamily_relativePerson_true() {
//        Person p1 = new Person();
//        p.addToFamily(p1);
//        Assertions.assertTrue(p.isFamily(p1));
        final Person[] people = new Person[2];
        Person p1 = new Person(){
            @Override
            public void addToFamily(Person person) {
                people[0] = person;
                super.addToFamily(person);
            }

            @Override
            public boolean isFamily(Person person) {
                people[1] = person;
                return super.isFamily(person);
            }
        };
        p1.addToFamily(p);
        p1.isFamily(p);
        Assertions.assertTrue(p1.isFamily(p));
        Assertions.assertEquals(people[0], people[1]);


    }

}


