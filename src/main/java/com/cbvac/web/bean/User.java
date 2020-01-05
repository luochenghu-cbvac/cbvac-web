package com.cbvac.web.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shaojieyue
 * Created at 2019-07-18 14:55
 */

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class User {
    private long id;

    private String name;
}
