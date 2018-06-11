package com.linn.home.controller;

import com.github.pagehelper.PageInfo;
import com.linn.frame.controller.BaseController;
import com.linn.frame.entity.ResultBean;
import com.linn.frame.entity.ResultTable;
import com.linn.frame.util.SysContent;
import com.linn.home.entity.Link;
import com.linn.home.entity.User;
import com.linn.home.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户
 * Created by Administrator on 2018-03-08.
 */
@Controller
public class UserController extends BaseController{

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("admin/getUserListData")
    public ResultTable getUserListData(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "limit") Integer limit) {

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(limit);
        pageInfo =  userService.findUserList(pageInfo);
        return new ResultTable(String.valueOf(SysContent.SUCCESS), "", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 根据id查找用户
     * @param userId
     * @param model
     * @return
     */
    public String toUserForm(@RequestParam(value = "userId", required = false) Integer userId
                        , Model model){
        if (!StringUtils.isEmpty(userId)) {
            User user = userService.findUserById(userId);
            model.addAttribute("user", user);
        }
        return "admin/userForm";

    }

    /**
     * 添加或修改用户
     * @param user
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("admin/addOrUpdateUser")
    public ResultBean addOrUpdateUser(User user) {
        Md5Hash hash = new Md5Hash(user.getPassWord(),user.getUserName());
        user.setPassWord(hash.toString());
        if(StringUtils.isEmpty(user.getId())) {
            if(userService.findUserByName(user)!=null) {
                return new ResultBean(SysContent.ERROR,"用户名不能重复！");
            }
            //添加
            user.setGmtCreate(new Date());
            user.setGmtModified(new Date());
            int ret = userService.addUser(user);
        }else {
            //更新
            user.setGmtModified(new Date());
            int ret = userService.updateUserByUserName(user);
        }
        return new ResultBean(SysContent.SUCCESS,"操作成功");
    }

    /**
     * 删除用户
     * @param ids
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("admin/delUser")
    public ResultBean delUser(int[] ids) {
        if(ids!=null && ids.length > 0){
            for (int id: ids) {
                int ret = userService.deleteUserById(id);
            }
        }
        return new ResultBean(SysContent.SUCCESS,"删除成功");
    }

    /**
     * 修改密码
     * @param map
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("admin/changePwd")
    public ResultBean changePwd(@RequestBody Map<String, String> map) {

        User user = new User();
        //用户名
        if(map.containsKey("userName") && !StringUtils.isEmpty(map.get("userName"))){
            String userName = map.get("userName").trim();
            user.setUserName(userName);
        }
        //老密码
        if(map.containsKey("passWord") && !StringUtils.isEmpty(map.get("passWord"))){
            String passWord = map.get("passWord").trim();
            Md5Hash hash = new Md5Hash(passWord,user.getUserName());
            user.setPassWord(hash.toString());
        }

        user = userService.findUserByNameAndPwd(user);
        if(user == null){
            return new ResultBean(SysContent.ERROR,"密码错误!");
        }

        //新密码
        if(map.containsKey("newPwd") && !StringUtils.isEmpty(map.get("newPwd"))){
            if(StringUtils.isEmpty(map.get("newPwd"))){
                return new ResultBean(SysContent.ERROR,"新密码不能为空!");
            }
            String newPwd = map.get("newPwd").trim();
            Md5Hash hash = new Md5Hash(newPwd,user.getUserName());
            user.setPassWord(hash.toString());
        }
        //更新
        user.setGmtModified(new Date());
        int ret = userService.updateUserByUserName(user);
        return new ResultBean(SysContent.SUCCESS,"修改成功");
    }

}
