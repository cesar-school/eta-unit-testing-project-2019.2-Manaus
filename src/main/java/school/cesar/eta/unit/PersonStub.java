package school.cesar.eta.unit;

import java.time.LocalDate;

public class PersonStub extends Person {

    @Override
    public LocalDate getNow() {
        return LocalDate.parse("2020-09-20");
    }
}