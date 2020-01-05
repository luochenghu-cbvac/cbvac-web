package com.cbvac.web.controller;

import com.cbvac.common.Constant;
import com.cbvac.web.bean.AdminUser;
import com.cbvac.web.bean.HttpResultBean;
import com.cbvac.web.service.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Tiger
 * @date : 2020-01-05 19:48
 */

@RestController
@RequestMapping("/api/v1/admin")
public class AdminUserController {
    private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("login")
    public Object adminLogin(@RequestParam String loginName, @RequestParam String passWord) {

        HttpResultBean httpResultBean = new HttpResultBean();
        AdminUser adminUser = adminUserService.findByLoginName(loginName);

        if (adminUser == null) {
            httpResultBean.setStatus(Constant.httpStatusFail);
            httpResultBean.setMessage("用户名无效");
        }

        if (passWord == null || !passWord.equals(adminUser.getPassWord())) {
            httpResultBean.setStatus(Constant.httpStatusFail);
            httpResultBean.setMessage("密码错误");
        }

        httpResultBean.setStatus(Constant.httpStatusSuccess);
        httpResultBean.setMessage("登录成功");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userInfo", adminUser);

        httpResultBean.setData(map);


        return httpResultBean;
    }

}
