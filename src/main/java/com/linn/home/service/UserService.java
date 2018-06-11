package com.linn.home.service;

import com.github.pagehelper.PageInfo;
import com.linn.home.entity.User;

import java.util.List;

/**
 * 用户service层
 * Created by Administrator on 2018-03-06.
 */
public interface UserService {

    User findUserByName(User user);

    User findUserByNameAndPwd(User user);

    PageInfo findUserList(PageInfo pageInfo);

    int addUser(User user);

    int updateUserByUserName(User user);

    int deleteUserById(int id);

    List<String> getRolesByUsername(String username);

    User getPasswordByUsername(String username);

    List<String> getPermisionByUsername(String username);

    User findUserById(Integer userId);
}
