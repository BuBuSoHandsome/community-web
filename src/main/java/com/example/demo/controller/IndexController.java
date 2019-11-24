package com.example.demo.controller;

        import com.example.demo.commonTool.RedisUtil;
        import com.example.demo.mapper.UserMapper;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;

        import javax.servlet.http.Cookie;
        import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie [] cookies = request.getCookies();
        if(null!=cookies&&cookies.length>0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String [] redisToken = (cookie.getValue()).split("\\|");
                    /**
                     *   从cookie取出来的token 与 redis中存放的用户token对比
                     *   如果一致则返回用户名称
                     */
                    if(redisUtil.hget(redisToken[0],"token").equals(redisToken[1])){
                        request.getSession().setAttribute("username", redisUtil.hget(redisToken[0],"name"));
                    }
                    break;
                }
            }
        }
        return "index";
    }

}
