package com.legend.user.service;

import com.legend.user.mapper.UserMapper;
import com.legend.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * userService层
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/14
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    public User queryById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        this.userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<User> queryAll() {
        return userMapper.selectAll();
    }
}
