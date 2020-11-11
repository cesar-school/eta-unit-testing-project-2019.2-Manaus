package school.cesar.eta.unit;

import java.time.LocalDate;

public class PersonStub extends Person {
    @Override
    public LocalDate getNow() {
        LocalDate data = LocalDate.parse("2020-05-03");
        return data;
    }
}
