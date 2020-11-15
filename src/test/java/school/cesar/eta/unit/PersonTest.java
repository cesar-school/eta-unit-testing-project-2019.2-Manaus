package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PersonTest {
    Person person;
    @BeforeEach
    public void setup(){
        person = new Person();
    }

    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        person.setName("Jon");
        person.setLastName("Snow");
        Assertions.assertEquals("Jon Snow" , person.getName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        person.setName("Jon");
        Assertions.assertEquals("Jon" , person.getName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        person.setLastName("Snow");
        Assertions.assertEquals("Snow" , person.getName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Assertions.assertThrows(RuntimeException.class,() -> new Person().getName() );
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {

        person = new StubPerson();
        person.setBirthday(LocalDate.parse("2020-10-18"));
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        person = new StubPerson();
        person.setBirthday(LocalDate.parse("2020-10-15"));
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        person = new StubPerson();
        person.setBirthday(LocalDate.parse("1996-04-17"));
        Assertions.assertTrue(person.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        Person person1 = new Person();
        person.addToFamily(person1);
        Assertions.assertTrue(person.isFamily(person1));
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person person1 = new Person();
        person.addToFamily(person1);
        Assertions.assertTrue(person.isFamily(person1) == true && person1.isFamily(person) == true);
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Assertions.assertFalse(new Person().isFamily(new Person()));
    }

    @Test
    public void isFamily_relativePerson_true() {
        final Person[] people = new Person[2];
        Person person1 = new Person(){
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
        person1.addToFamily(person);
        person1.isFamily(person);
        Assertions.assertTrue(person1.isFamily(person));
        Assertions.assertEquals(people[0], people[1]);


    }

}


