package com.example.demo.entity;

import com.example.demo.base.Group;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 11:43
 */
@Data
public class User {
    private String id;
    private String userName;
}
