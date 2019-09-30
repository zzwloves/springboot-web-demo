package com.example.demo.dto;

import com.example.demo.base.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 11:43
 */
@Data
@ApiModel("用户信息")
public class UserDTO {
    @NotBlank(message = "id不能为空")
    private String id;

    @ApiModelProperty(value = "user_Name", name = "user_name", required = true)
    @NotBlank(message = "name不能为空", groups = Group.Add.class)
    private String userName;
}
