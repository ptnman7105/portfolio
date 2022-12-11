package com.example.linebot.repository;

import com.example.linebot.value.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.DataClassRowMapper;
import java.time.LocalTime;
import java.util.List;

@Repository
public class WeatherRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public WeatherRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    public void deletePreviousItems(WeatherDeleteItem item) {
        String sql = "delete from weather_item " +
                "where push_text = ? and estimate_number = ? ";
         WeatherDeleteSlot slot=item.getSlot();
         String text = slot.getPushText();
         float number = slot.getPushEstimateNumber();

        jdbc.update(sql,text,number);

    }

    public List<WeatherTuple> findAllItems() {
        String sql = "select user_id, push_text, estimate_number " +
                "from weather_item ";

        List<WeatherTuple> list = jdbc.query(sql,new DataClassRowMapper(WeatherTuple.class));

        return list;
    }

    public void insert(WeatherItem item) {
        String sql = "insert into weather_item "
                + "(user_id, push_text, estimate_number) "
                + "values (?, ?, ?)";

        String userId = item.getUserId();
        WeatherSlot slot = item.getSlot();
        jdbc.update(sql,userId,slot.getPushText(),slot.getPushEstimateNumber());
    }
}
