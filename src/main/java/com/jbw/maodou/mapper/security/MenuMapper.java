package com.jbw.maodou.mapper.security;

import com.jbw.maodou.entity.security.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getAllMenus();
}
