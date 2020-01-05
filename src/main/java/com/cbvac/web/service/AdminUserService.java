package com.cbvac.web.service;

import com.cbvac.web.bean.AdminUser;
import com.cbvac.web.dao.AdminUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Tiger
 * @date : 2020-01-05 19:48
 */

@Service
public class AdminUserService {
    private static final Logger logger = LoggerFactory.getLogger(AdminUserService.class);

    @Autowired
    private AdminUserDao adminUserDao;

    public AdminUser findByLoginName(String loginName) {
        return adminUserDao.findByLoginName(loginName);
    }
}
