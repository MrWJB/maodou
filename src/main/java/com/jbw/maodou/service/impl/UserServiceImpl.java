package com.jbw.maodou.service.impl;

import com.jbw.maodou.entity.User;
import com.jbw.maodou.mapper.UserMapper;
import com.jbw.maodou.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangjibin
 * @since 2021-04-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
