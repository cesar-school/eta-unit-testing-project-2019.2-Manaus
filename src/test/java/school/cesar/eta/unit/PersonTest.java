package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;

public class PersonTest {
     Person person;

    @BeforeEach
    public void setup(){
        person = new Person();
    }


    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        person.setName("jon");
        person.setLastName("Snow");

        Assertions.assertEquals("jon Snow", person.getName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        person.setName("jon");

        Assertions.assertEquals("jon", person.getName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        person.setLastName("Snow");

        Assertions.assertEquals("Snow", person.getName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {

        Assertions.assertThrows(RuntimeException.class, ()-> person.getName());
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        LocalDate date_old = LocalDate.parse("2016-02-06");

        /* Stub to save getNow result */
        person = new Person() {
            public LocalDate getNow() {
                return LocalDate.parse("2020-11-15");
            }
        };

        person.setBirthday(date_old);
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        LocalDate date_old = LocalDate.parse("2020-10-06");

        /* Stub to save getNow result */
        person = new Person() {
            public LocalDate getNow() {
                return LocalDate.parse("2020-11-15");
            }
        };

        person.setBirthday(date_old);
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        /* Force a date */
        LocalDate date_now = LocalDate.parse("2020-11-15");

        /* Stub to save getNow result */
        person = new Person() {
            public LocalDate getNow() {
                return LocalDate.parse("2020-11-15");
            }
        };

        person.setBirthday(date_now);

        Assertions.assertTrue(person.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        final boolean[] member = {false};
        Person person_new = new Person(){
            @Override
            public void addToFamily(Person person) {
                member[0] = true;
                super.addToFamily(person);
            }
        };

        Person person = new Person();
        person_new.addToFamily(person);

        Assertions.assertTrue(member[0]);
        /* Check if person_new has the member that was added */
        Assertions.assertTrue(person_new.isFamily(person));
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person person_new = new Person();
        person.addToFamily(person_new);

        Assertions.assertEquals(person.isFamily(person_new), person_new.isFamily(person));
        /* More coverage test */
        Assertions.assertTrue(person_new.isFamily(person));
        Assertions.assertTrue(person.isFamily(person_new));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Assertions.assertFalse(new Person().isFamily(new Person()));
    }

    @Test
    public void isFamily_relativePerson_true() {
        final boolean[] member = {false};
        /* Isolate addToFamily method */
        Person person_new = new Person() {
            @Override
            public void addToFamily(Person person) {
                member[0] = true;
                super.addToFamily(person);
            }
        };

        person.addToFamily(person_new);
        Assertions.assertTrue(person.isFamily(person_new));
    }
}
