package example.entities;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"date"})
@ToString
public class RatesDate {

    private long id;
    private LocalDate date;

    public RatesDate(LocalDate date) {
        this.date = date;
    }
}
