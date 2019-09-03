package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 10:00
 */
@Mapper
@Repository
public interface UserMapper {

    User selectById(@Param("id") String id);
}
