package com.lifetea.service;

import com.lifetea.mapper.UserMapper;
import com.lifetea.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifetea on 2017/4/25.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int register(User user){

        return userMapper.insert(user);

    }

    public User updateInfo(User user){
        user.setPassword(null);
        user.setMobile(null);
        user.setLevel(null);
        int index = userMapper.updateByPrimaryKeySelective(user);
        return userMapper.selectByPrimaryKey(user);
    }


    public User login(User user){
        return userMapper.selectOne(user);
    }


    private boolean checkExist(User user){
        Boolean bool = userMapper.selectCount(user) == 0 ? true : false;
        return  bool;
    }


}
