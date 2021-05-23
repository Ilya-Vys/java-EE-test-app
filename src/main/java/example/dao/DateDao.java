package example.dao;

import example.entities.RatesDate;

import java.time.LocalDate;

public interface DateDao {

    long getByDate(LocalDate localDate);
    RatesDate save(LocalDate localDate);
}
