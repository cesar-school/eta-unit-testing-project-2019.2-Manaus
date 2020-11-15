package school.cesar.eta.unit;

import java.time.LocalDate;

public class StubPerson extends Person {

    @Override
    public LocalDate getNow() {
        return LocalDate.parse("2020-04-17");
    }
}
