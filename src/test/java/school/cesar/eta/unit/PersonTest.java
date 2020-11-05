package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class PersonTest {
    Person x;
    @BeforeEach
    public void setup(){
        x = new Person();
    }

    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        x.setName("Jon");
        x.setLastName("Snow");

        Assertions.assertEquals("Jon Snow" , x.getName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        x.setName("Jon");

        Assertions.assertEquals("Jon" , x.getName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        x.setLastName("Snow");

        Assertions.assertEquals("Snow" , x.getName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Assertions.assertThrows(RuntimeException.class,() -> new Person().getName() );
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        x = new Person() {
            public LocalDate getNow() {
                return LocalDate.parse("2020-10-05");
            }
        };
        LocalDate birthday = LocalDate.parse("1985-04-12");
        x.setBirthday(birthday);

        Assertions.assertFalse(x.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        x = new Person() {
            public LocalDate getNow() {
                return LocalDate.parse("2020-04-05");
            }
        };
        LocalDate birthday = LocalDate.parse("1985-04-12");
        x.setBirthday(birthday);

        Assertions.assertFalse(x.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        x = new Person() {
            public LocalDate getNow() {
                return LocalDate.parse("2020-04-12");
            }
        };
        LocalDate birthday = LocalDate.parse("1985-04-12");
        x.setBirthday(birthday);

        Assertions.assertTrue(x.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        x = new Person() {
            @Override
            public void addToFamily(Person person) {
                super.addToFamily(person);
            }
        };
        Person x1 = new Person();
        x.addToFamily(x1);

        Assertions.assertTrue(x.isFamily(x1));
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person x1 = new Person();
        x.addToFamily(x1);

        Assertions.assertTrue(x.isFamily(x1));
        Assertions.assertTrue(x1.isFamily(x));
        Assertions.assertEquals(x.isFamily(x1), x1.isFamily(x));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person x1 = new Person() {
            public boolean isFamily(Person person) {
                return false;
            }
        };
        Person x2 = new Person();
        x.addToFamily(x2);
        x1.addToFamily(x2);

        Assertions.assertTrue(x.isFamily(x2));
        Assertions.assertFalse(x1.isFamily(x2));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person x1 = new Person() {
            public boolean isFamily(Person person) {
                return true;
            }
        };

        Assertions.assertTrue(x1.isFamily(x));
    }
}