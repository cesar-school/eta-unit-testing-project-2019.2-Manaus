package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

public class PersonTest {
    Person a;
    @BeforeEach
    public void setup(){
        a = new Person();
    }

    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        fail();
        a.setName("Jon");
        a.setLastName("Snow");

        Assertions.assertEquals("Jon Snow" , a.getName());
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        fail();
        a.setName("Jon");

        Assertions.assertEquals("Jon" , a.getName());
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        fail();
        a.setLastName("Snow");

        Assertions.assertEquals("Snow", a.getName());
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        fail();

        Assertions.assertThrows(RuntimeException.class,() -> new Person().getName() );
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() { fail(); }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        fail();
        ArrayList<String> listNum = new ArrayList<String>();
        for (int x = 1; x <= 9; x++) {
            listNum.add(String.valueOf(x));
        }

        listNum.remove(String.valueOf(LocalDate.now().getDayOfMonth()));
        Random y = new Random();
        int day = y.nextInt(7);

        //LocalDate dataManipulacao = LocalDate.now();
        //dataManipulacao.plusDays(5).plusMonths(1);

        String date = LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-0" +  listNum.get(day);
        a.setBirthday(LocalDate.parse(date));

        Assertions.assertFalse(a.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        fail();
        a.setBirthday(LocalDate.now());

        Assertions.assertTrue(a.isBirthdayToday());
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        fail();
        final boolean[] newMember = {false};
        a = new Person(){
            @Override
            public void addToFamily(Person a1) {
                newMember[0] = true;
                super.addToFamily(a1);
            }
        };
        a.addToFamily(new Person());

        Assertions.assertTrue(newMember[0]);
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        fail();
        Person a1 = new Person();
        a.addToFamily(a1);

        Assertions.assertEquals(a.isFamily(a1), a1.isFamily(a));
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        fail();

        Assertions.assertFalse(new Person().isFamily(new Person()));

        Person a1 = new Person(){
            @Override
            public boolean isFamily(Person person) {
                return false;
            }
        };

        Assertions.assertFalse(a1.isFamily(new Person()));
    }

    @Test
    public void isFamily_relativePerson_true() {
        fail();
        Person a1 = new Person();
        a.addToFamily(a1);

        Assertions.assertTrue(a.isFamily(a1));

        Person a2 = new Person(){
            @Override
            public boolean isFamily(Person person) {
                return true;
            }
        };

        Assertions.assertTrue(a2.isFamily(a));
    }
}