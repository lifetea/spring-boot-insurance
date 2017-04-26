package com.lifetea.controller;

import com.lifetea.exception.UserException;
import com.lifetea.model.BaseResult;
import com.lifetea.model.User;
import com.lifetea.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by lifetea on 2017/4/25.
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "用户注册", notes = "", httpMethod = "POST",produces = "application/json;charset=utf-8" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public BaseResult register(@ApiIgnore User user) throws UserException{
        try{
            BaseResult result = new BaseResult(200,"ok");
            ModelMap data     = (ModelMap) result.getData();
            data.put("detail",userService.register(user));
            return  result;
        }catch (Exception e){
            throw new UserException("手机号已存在");
        }
    }


    @ApiOperation(value = "用户登录", notes = "", httpMethod = "POST",produces = "application/json;charset=utf-8" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "渺茫", required = false, dataType = "String", paramType = "query"),
    })
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResult login(@ApiIgnore User user) throws UserException{
        try{
            User info = userService.login(user);
            if (info == null)
                throw new UserException("帐号不存在");
//            if (info.getPassword() != user.getPassword())
//                throw new UserException("密码错误");
            BaseResult result = new BaseResult(200,"ok",info);
            return  result;
        }catch (Exception e){
            throw new UserException("登录失败");
        }
    }



    @ApiOperation(value = "用户信息修改", notes = "", httpMethod = "POST",produces = "application/json;charset=utf-8" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "序号", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "姓名", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "address", value = "地址", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "carNo", value = "车牌", required = false, dataType = "String", paramType = "query"),
    })
    @ResponseBody
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    public BaseResult updateInfo(@ApiIgnore User user) throws UserException{
        if(user.getId() == null)
            throw new UserException("未提交ID");
        try{
            BaseResult result = new BaseResult(200,"ok",userService.updateInfo(user));
            return  result;
        }catch (Exception e){
            throw new UserException("信息修改失败");
        }
    }

}
