package com.zwh.firstsc.auth.controller;

import com.zwh.firstsc.auth.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("token")
    public String getToken(@RequestParam("name") String name, @RequestParam("age") String age) {
        if (StringUtils.isBlank(name)) {
            return "name empty";
        }
        if (StringUtils.isBlank(age)) {
            return "age empty";
        }
        System.out.println("=================================");
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        String token = JwtUtil.createToken(map);
        System.out.println(token);
        return token;
    }
}
