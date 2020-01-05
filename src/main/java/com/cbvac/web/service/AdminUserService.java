package com.cbvac.web.service;

import com.cbvac.web.bean.AdminUser;
import com.cbvac.web.dao.AdminUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shaojieyue
 * Created at 2019-07-18 14:54
 */

@Service
public class AdminUserService {
    private static final Logger logger = LoggerFactory.getLogger(AdminUserService.class);

    @Autowired
    private AdminUserDao adminUserDao;

    public AdminUser findById(String name) {
        return adminUserDao.findById(name);
    }
}
