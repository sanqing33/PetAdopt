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
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping("/api/login")
    public String postLogin(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        List<User> userList = userMapper.select(username);
        if (userList != null && !userList.isEmpty()) {
            User user = userList.get(0);
            if (user.getPassword().equals(password)) {
                return "登录成功";
            }
        }
        return "登录失败";
    }

    @PostMapping("/api/regist")
    public String postPet(@RequestBody User user) {
        userMapper.insert(user);
        return "注册成功";
    }

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

    @PostMapping("/api/admin/adopt/delete")
    public String postDeleteAdopt(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        userMapper.deleteAdopt(name);
        return "删除成功";
    }

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

    @PostMapping("/api/admin/user/edit")
    public String postEditUser(@RequestBody User user) {
        userMapper.update(user);
        return "编辑成功";
    }

    @PostMapping("/api/admin/user/delete")
    public String postDeleteUser(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        userMapper.delete(name);
        return "删除成功";
    }

    @PostMapping("/api/admin/user/deletes")
    public String postDeleteUsers(@RequestBody List<String> usernames) {
        userMapper.deleteUsers(usernames);
        return "删除成功";
    }

}
