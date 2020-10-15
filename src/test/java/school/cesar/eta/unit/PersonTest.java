package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class PersonTest {
    Person y;
    @BeforeEach
    public void setup(){
        y = new Person();
    }

    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        y.setName("Jon");
        y.setLastName("Snow");
        Assertions.assertEquals("Jon Snow" , y.getName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        y.setName("Jon");
        Assertions.assertEquals("Jon" , y.getName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        y.setLastName("Snow");
        Assertions.assertEquals("Snow" , y.getName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Assertions.assertThrows(RuntimeException.class,() -> new Person().getName() );
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        ArrayList<String> n = new ArrayList<String>();
        for (int i = 1; i <= 9; i++) {
            n.add(String.valueOf(i));
        }
        n.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        n.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        Random random = new Random();
        int day = random.nextInt(7);
        int month = random.nextInt(7);
        String data = LocalDate.now().getYear() + "-0" + n.get(month) + "-0" +  n.get(day);
        y.setBirthday(LocalDate.parse(data));
        Assertions.assertFalse(y.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        ArrayList<String> n = new ArrayList<String>();
        for (int i = 1; i <= 9; i++) {
            n.add(String.valueOf(i));
        }
        n.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        Random r = new Random();
        int day = r.nextInt(7);
        String data = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-0" +  n.get(day);
        y.setBirthday(LocalDate.parse(data));
        Assertions.assertFalse(y.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        LocalDate data = y.getNow();
        y.setBirthday(data);
        Assertions.assertTrue(y.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        final boolean[] familyNewMember = {false};
        y = new Person(){
            @Override
            public void addToFamily(Person y) {
                familyNewMember[0] = true;
                super.addToFamily(y);
            }
        };
        y.addToFamily(new Person());
        Assertions.assertTrue(familyNewMember[0]);
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person y1 = new Person();
        y.addToFamily(y1);
        Assertions.assertEquals(y.isFamily(y1), y1.isFamily(y));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Assertions.assertFalse(new Person().isFamily(new Person()));
        Person y1 = new Person(){
            @Override
            public boolean isFamily(Person y) {
                return false;
            }
        };
        Assertions.assertFalse(y1.isFamily(new Person()));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person y1 = new Person();
        y.addToFamily(y1);
        Assertions.assertTrue(y.isFamily(y1));
        Person y2 = new Person(){
            @Override
            public boolean isFamily(Person y) {
                return true;
            }
        };
        Assertions.assertTrue(y2.isFamily(y));
    }
}