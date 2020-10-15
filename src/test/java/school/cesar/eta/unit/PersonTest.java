package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

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
        ArrayList<String> numbers = new ArrayList<String>();
        for (int a = 1; a <= 9; a++) {
            numbers.add(String.valueOf(a));
        }
        numbers.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        numbers.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        Random random = new Random();
        int day = random.nextInt(7);
        int month = random.nextInt(7);
        String birthDate = LocalDate.now().getYear() + "-0" + numbers.get(month) + "-0" +  numbers.get(day);
        person.setBirthday(LocalDate.parse(birthDate));
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        ArrayList<String> numbers = new ArrayList<String>();
        for (int a = 1; a <= 9; a++) {
            numbers.add(String.valueOf(a));
        }
        numbers.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        Random random = new Random();
        int day = random.nextInt(7);
        String birthDate = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-0" +  numbers.get(day);
        person.setBirthday(LocalDate.parse(birthDate));
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        LocalDate birthDate = person.getNow();
        person.setBirthday(birthDate);
        Assertions.assertTrue(person.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        final boolean[] familyNewMember = {false};
        person = new Person(){
            @Override
            public void addToFamily(Person person) {
                familyNewMember[0] = true;
                super.addToFamily(person);
            }
        };
        person.addToFamily(new Person());
        Assertions.assertTrue(familyNewMember[0]);
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person person1 = new Person();
        person.addToFamily(person1);
        Assertions.assertEquals(person.isFamily(person1), person1.isFamily(person));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Assertions.assertFalse(new Person().isFamily(new Person()));
        Person person1 = new Person(){
            @Override
            public boolean isFamily(Person person) {
                return false;
            }
        };
        Assertions.assertFalse(person1.isFamily(new Person()));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person person1 = new Person();
        person.addToFamily(person1);
        Assertions.assertTrue(person.isFamily(person1));
        Person person2 = new Person(){
            @Override
            public boolean isFamily(Person person) {
                return true;
            }
        };
        Assertions.assertTrue(person2.isFamily(person));
    }
}