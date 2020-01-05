package com.cbvac.web.controller;

import com.cbvac.web.service.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaojieyue
 * Created at 2019-07-17 20:44
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
    public Object adminLogin(@RequestParam String name) {
        return adminUserService.findById(name);
    }

}
