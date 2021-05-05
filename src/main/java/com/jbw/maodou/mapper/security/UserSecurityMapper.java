package com.jbw.maodou.mapper.security;

import com.jbw.maodou.entity.security.Role;
import com.jbw.maodou.entity.security.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserSecurityMapper {
//    @Select("select * from user where username=#{username}")
    User loadUserByUsername(@Param("username") String username);
    List<Role> getUserRolesByUid(Integer id);
}
