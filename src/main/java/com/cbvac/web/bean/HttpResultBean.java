package com.cbvac.web.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author : Tiger
 * @date : 2020-01-05 19:48
 */

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class HttpResultBean {

    private String status;
    private String message;
    private List dataList;
    private Map<String, Object> data;
}
