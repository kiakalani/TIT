package Todo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        LocalDate today=LocalDate.now();
        LocalDate target=LocalDate.of(2019,7,28);

        System.out.println(Duration.between(today.atStartOfDay(),target.atStartOfDay()).toDays());
    }
}
