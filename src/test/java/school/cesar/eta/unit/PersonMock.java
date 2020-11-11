package school.cesar.eta.unit;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PersonMock extends Person {

    public List<Person> getFamilyList(){
        Field[] fields = Person.class.getDeclaredFields();
        List<Person> family = new ArrayList<Person>();
        for(Field f: fields){
            f.setAccessible(true);
            if (f.getName().equals("family")){
                try {
                    family = (List)f.get(this);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return family;
    }


}
