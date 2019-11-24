package com.example.demo.controller;

import com.example.demo.commonTool.RedisUtil;
import com.example.demo.commonTool.TimeTool;
import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.GithubUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.url}")
    private String redirectUrl;

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TimeTool timeTool;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_url(redirectUrl);
        accessTokenDto.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (null != githubUser) {
            //不为空，登陆成功
            System.out.println("登录成功，" + githubUser.getName() + " 欢迎回家");

            //需要判断该用户是否新用户，是则插入user表，并且添加到redis缓存中管理，否则更新redis缓存的用户信息即可。
            User existUser = userMapper.queryUserByAccountId(String.valueOf(githubUser.getId()));
            if (null == existUser) {
                User user = new User(githubUser.getName(), String.valueOf(githubUser.getId()), timeTool.getSystemTime(), timeTool.getSystemTime());
                userMapper.insert(user);
            }
            //把登录状态的token放入redis 以及 cookie中
            String loginToken = UUID.randomUUID().toString().replaceAll("-", "");
            //以GitHub帐号信息和token存入Redis
            Map<String,Object> userMap = new HashMap<>();
            userMap.put("token", loginToken);
            userMap.put("name",githubUser.getName());
            redisUtil.hmset("UserMSG:"+githubUser.getId(),userMap);
            response.addCookie(new Cookie("token", "UserMSG:"+githubUser.getId()+"|"+loginToken));
            //重定向回主页
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }

}
