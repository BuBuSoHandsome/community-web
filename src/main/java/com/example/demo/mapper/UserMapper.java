package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,gmt_create,gmt_modified) values (#{account_id},#{name},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where account_id = '${account_id}'")
    User queryUserByAccountId(@Param("account_id") String account_id);
}
