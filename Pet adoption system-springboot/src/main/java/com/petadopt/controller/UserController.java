package com.petadopt.controller;

import com.alibaba.fastjson.JSON;
import com.petadopt.entity.Adopt;
import com.petadopt.entity.Adoption;
import com.petadopt.entity.User;
import com.petadopt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 后台用户管理页面
    @GetMapping("/api/admin/user")
    public String getAdminUser(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        int offset = (page - 1) * pageSize;
        List<User> users = userMapper.selectUser(offset, pageSize);
        int total = userMapper.countUsers();
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("data", users);
        return JSON.toJSONString(result);
    }

    // 后台领养申请管理页面
    @GetMapping("/api/admin/adopt")
    public String getAdminAdopt(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Adopt> adopts = userMapper.selectAdopt(offset, pageSize);
        int total = userMapper.countAdopt();
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("data", adopts);
        return JSON.toJSONString(result);
    }

    // 后台领养申请管理页面 删除 功能
    @PostMapping("/api/admin/adopt/delete")
    public String postDeleteAdopt(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        userMapper.deleteAdopt(name);
        return "删除成功";
    }

    // 后台领养情况管理页面
    @GetMapping("/api/admin/adoption")
    public String getAdminAdoption(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Adoption> adoptions = userMapper.selectAdoption(offset, pageSize);
        int total = userMapper.countAdoption();
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("data", adoptions);
        return JSON.toJSONString(result);
    }

    // 后台用户管理页面 搜索 功能
    @GetMapping("/api/admin/user/query")
    public String getAdminQueryUser(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) String username,
                                    @RequestParam(required = false) String email,
                                    @RequestParam("page") int page,
                                    @RequestParam("pageSize") int pageSize) {
        int offset = (page - 1) * pageSize;
        List<User> users = userMapper.queryWithPagination(name, username, email, offset, pageSize);
        int total = userMapper.countUserByQuery(name, username, name);
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("data", users);
        return JSON.toJSONString(result);
    }

    // 后台用户管理页面 编辑 功能
    @PostMapping("/api/admin/user/edit")
    public String postEditUser(@RequestBody User user) {
        userMapper.update(user);
        return "编辑成功";
    }

    // 后台用户管理页面 删除 功能
    @PostMapping("/api/admin/user/delete")
    public String postDeleteUser(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        userMapper.delete(name);
        return "删除成功";
    }

    // 后台用户管理页面 批量删除 功能
    @PostMapping("/api/admin/user/deletes")
    public String postDeleteUsers(@RequestBody List<String> usernames) {
        userMapper.deleteUsers(usernames);
        return "删除成功";
    }

}
