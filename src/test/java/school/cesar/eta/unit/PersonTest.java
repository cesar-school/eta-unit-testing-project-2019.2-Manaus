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
        ArrayList<String> listNum = new ArrayList<String>();
        for (int i = 1; i <= 9; i++) {
            listNum.add(String.valueOf(i));
        }
        listNum.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        listNum.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        Random rand = new Random();
        int int_random = rand.nextInt(7);
        int int_random2 = rand.nextInt(7);
        String date = LocalDate.now().getYear() + "-0" + listNum.get(int_random2) + "-0" +  listNum.get(int_random);
        p.setBirthday(LocalDate.parse(date));
        Assertions.assertFalse(p.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        ArrayList<String> listNum = new ArrayList<String>();
        for (int i = 1; i <= 9; i++) {
            listNum.add(String.valueOf(i));
        }
        listNum.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        Random rand = new Random();
        int int_random = rand.nextInt(7);
        String date = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-0" +  listNum.get(int_random);
        p.setBirthday(LocalDate.parse(date));
        Assertions.assertFalse(p.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        p.setBirthday(LocalDate.now());
        Assertions.assertTrue(p.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        final boolean[] newMember = {false};
        p = new Person(){
            @Override
            public void addToFamily(Person person) {
                newMember[0] = true;
                super.addToFamily(person);
            }
        };
        p.addToFamily(new Person());
        Assertions.assertTrue(newMember[0]);
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person p1 = new Person();
        p.addToFamily(p1);
        Assertions.assertEquals(p.isFamily(p1), p1.isFamily(p));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Assertions.assertFalse(new Person().isFamily(new Person()));
        // OR
        Person p1 = new Person(){
            @Override
            public boolean isFamily(Person person) {
                return false;
            }
        };
        Assertions.assertFalse(p1.isFamily(new Person()));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person p1 = new Person();
        p.addToFamily(p1);
        Assertions.assertTrue(p.isFamily(p1));
        // OR
        Person p2 = new Person(){
            @Override
            public boolean isFamily(Person person) {
                return true;
            }
        };
        Assertions.assertTrue(p2.isFamily(p));
    }
}
