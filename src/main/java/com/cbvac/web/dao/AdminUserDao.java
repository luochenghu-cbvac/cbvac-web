package com.cbvac.web.dao;

import com.cbvac.web.bean.AdminUser;
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
public class AdminUserDao {
    @Autowired
    private JdbcTemplate masterJdbcTemplate;

    @Autowired
    private JdbcTemplate slaveJdbcTemplate;

    public AdminUser findById(String name) {
        String sql = "select * from admin_users where name =?";
        Object[] params = {
                name
        };
        final List<AdminUser> adminUsers = masterJdbcTemplate.query(sql, params, new UserRowMapper());
        final List<AdminUser> users1 = slaveJdbcTemplate.query(sql, params, new UserRowMapper());
        System.out.println(users1);
        if (CollectionUtils.isEmpty(adminUsers)) {
            return null;
        }
        return adminUsers.get(0);
    }

    static class UserRowMapper implements RowMapper<AdminUser> {
        @Override
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            return AdminUser.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .createTime(resultSet.getString("create_time"))
                    .passWord(resultSet.getString("pass_word"))
                    .build();
        }
    }

}
