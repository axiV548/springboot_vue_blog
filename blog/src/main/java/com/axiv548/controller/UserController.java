package com.axiv548.controller;

import com.axiv548.service.UserService;
import com.axiv548.util.AjaxResponse;
import com.axiv548.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * UserController
 *
 * @date 2021/2/26 13:49
 */

@RestController
@RequestMapping("login")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public AjaxResponse login(@RequestBody Map<String, String> name_password) {

        String md5 = MD5Util.code(name_password.get("user_password"));
        name_password.put("user_password", md5);

        Map<String, Object> map = userService.login(name_password);

        AjaxResponse ajaxResponse;
        if (!map.isEmpty()) {
            ajaxResponse = new AjaxResponse().success(map, "登录成功");
        } else {
            ajaxResponse = new AjaxResponse().success(401, "登录失败");
        }
        return ajaxResponse;
    }


}
