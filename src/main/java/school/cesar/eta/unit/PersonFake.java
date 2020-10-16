package school.cesar.eta.unit;

import java.util.List;
import java.util.ArrayList;

public class PersonFake extends Person {
    private List<PersonFake> family = new ArrayList<>();

    public void addToFamilyFake (PersonFake personFake) {
        this.family.add(personFake);
        personFake.family.add(this);
    }

    public List<PersonFake> getFamilyList() { return this.family;}

}