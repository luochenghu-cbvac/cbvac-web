package com.cbvac.web.dao;

import com.cbvac.web.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author shaojieyue
 * Created at 2020-01-04 20:44
 */
@Slf4j
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate masterJdbcTemplate;

    @Autowired
    private JdbcTemplate slaveJdbcTemplate;

    public User findById(long id) {
        String sql = "select * from user where id=?";
        Object[] params = {
                id
        };
        final List<User> users = masterJdbcTemplate.query(sql, params, new UserRowMapper());
        final List<User> users1 = slaveJdbcTemplate.query(sql, params, new UserRowMapper());
        System.out.println(users1);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return User.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .build();
        }
    }

}
