package school.cesar.eta.unit;

import java.util.ArrayList;
import java.util.List;

public class PersonMock extends Person {
    private List<PersonMock> family = new ArrayList<PersonMock>();

    public void addToFamily(PersonMock person) {
        this.family.add(person);
        person.family.add(this);
    }

    public List<PersonMock> getFamilyList(){
        return this.family;
    }
}
