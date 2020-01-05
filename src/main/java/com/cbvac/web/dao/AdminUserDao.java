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
 * @author : Tiger
 * @date : 2020-01-05 19:48
 */
@Slf4j
@Repository
public class AdminUserDao {
    @Autowired
    private JdbcTemplate masterJdbcTemplate;

    @Autowired
    private JdbcTemplate slaveJdbcTemplate;

    public AdminUser findByLoginName(String loginName) {
        String sql = "select * from admin_users where login_name =?";
        Object[] params = {
                loginName
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
                    .userId(resultSet.getLong("id"))
                    .userNo(resultSet.getString("user_no"))
                    .userName(resultSet.getString("user_name"))
                    .loginName(resultSet.getString("login_name"))
                    .createTime(resultSet.getString("create_time"))
                    .passWord(resultSet.getString("pass_word"))
                    .build();
        }
    }

}
