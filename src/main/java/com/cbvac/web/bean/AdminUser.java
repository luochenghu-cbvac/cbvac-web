package com.cbvac.web.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Tiger
 * @date : 2020-01-05 19:48
 */

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class AdminUser {
    private long userId;

    private String userName;

    private String userNo;

    private String loginName;

    private String passWord;

    private String createTime;
}
