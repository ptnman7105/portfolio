package com.example.linebot.repository;

import com.example.linebot.value.ReminderItem;
import com.example.linebot.value.ReminderSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.linebot.value.ReminderTuple;
import org.springframework.jdbc.core.DataClassRowMapper;
import java.time.LocalTime;
import java.util.List;

@Repository
public class ReminderRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public ReminderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    public void deletePreviousItems() {
        String sql = "delete from reminder_item " +
                "where push_at <= ? ";

        LocalTime now = LocalTime.now();
        jdbc.update(sql,now);
    }

    public List<ReminderTuple> findPreviousItems() {
        String sql = "select user_id, push_at, push_text " +
                "from reminder_item " +
                "where push_at <= ? ";

        LocalTime now = LocalTime.now();
        List<ReminderTuple> list = jdbc.query(sql,new DataClassRowMapper(ReminderTuple.class),now);
        return list;
    }

    public void insert(ReminderItem item) {
        String sql = "insert into reminder_item "
                +"(user_id,push_at,push_text) "
                +"values (?,?,?)";

        String userId = item.getUserId();
        ReminderSlot slot = item.getSlot();
        jdbc.update(sql,userId,slot.getPushAt(),slot.getPushText());
    }
}