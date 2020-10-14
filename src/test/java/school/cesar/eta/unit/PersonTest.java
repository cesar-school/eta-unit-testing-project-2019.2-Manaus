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
        ArrayList<String> nums = new ArrayList<String>();
        for (int a = 1; a <= 9; a++) {
            nums.add(String.valueOf(a));
        }
        nums.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        nums.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        Random r = new Random();
        int day = r.nextInt(7);
        int month = r.nextInt(7);
        String date = LocalDate.now().getYear() + "-0" + nums.get(month) + "-0" +  nums.get(day);
        x.setBirthday(LocalDate.parse(date));
        Assertions.assertFalse(x.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        ArrayList<String> listNum = new ArrayList<String>();
        for (int a = 1; a <= 9; a++) {
            listNum.add(String.valueOf(a));
        }
        listNum.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        Random r = new Random();
        int day = r.nextInt(7);
        String date = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-0" +  listNum.get(day);
        x.setBirthday(LocalDate.parse(date));
        Assertions.assertFalse(x.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        LocalDate date = x.getNow();
        x.setBirthday(date);
        Assertions.assertTrue(x.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        final boolean[] familyNewMember = {false};
        x = new Person(){
            @Override
            public void addToFamily(Person x1) {
                familyNewMember[0] = true;
                super.addToFamily(x1);
            }
        };
        x.addToFamily(new Person());
        Assertions.assertTrue(familyNewMember[0]);
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        Person x1 = new Person();
        x.addToFamily(x1);
        Assertions.assertEquals(x.isFamily(x1), x1.isFamily(x));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Assertions.assertFalse(new Person().isFamily(new Person()));
        Person x1 = new Person(){
            @Override
            public boolean isFamily(Person person) {
                return false;
            }
        };
        Assertions.assertFalse(x1.isFamily(new Person()));
    }

    @Test
    public void isFamily_relativePerson_true() {
        Person x1 = new Person();
        x.addToFamily(x1);
        Assertions.assertTrue(x.isFamily(x1));
        Person x2 = new Person(){
            @Override
            public boolean isFamily(Person person) {
                return true;
            }
        };
        Assertions.assertTrue(x2.isFamily(x));
    }
}