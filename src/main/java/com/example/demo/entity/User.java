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
    @NotBlank(message = "id不能为空", groups = {Group.Post.class, Group.Put.class, Group.Delete.class})
    private String id;

    @NotBlank(message = "name不能为空", groups = Group.Post.class)
    private String userName;
}
