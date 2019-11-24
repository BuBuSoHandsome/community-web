package com.example.demo.controller;

import com.example.demo.commonTool.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RedisTestController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/setString")
    @ResponseBody
    public boolean setString(@RequestParam(name = "key") String key,@RequestParam(name = "value") String value){
        return redisUtil.set(key,value);
    }

    @RequestMapping("/getString")
    @ResponseBody
    public Object getString(@RequestParam(name = "key") String key){
        return redisUtil.get(key);
    }


    @RequestMapping("/setMap")
    @ResponseBody
    public boolean setMap(@RequestParam(name = "key") String key,@RequestParam(name = "mapKey")String mapKey,@RequestParam(name = "mapValue")String mapValue){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(mapKey, mapValue);
        return redisUtil.hmset(key, map);
    }


    @RequestMapping("/getMap")
    @ResponseBody
    public Object getMap(@RequestParam(name = "key") String key,@RequestParam(name = "mapKey")String mapKey){
        return redisUtil.hget(key, mapKey);
    }





}
