package com.example.demo;


import com.alibaba.fastjson.JSON;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeStampTest {

    public static void main(String[] args) {
        Date date = new java.util.Date();
        Timestamp createDate = new Timestamp(date.getTime());
        System.out.println(createDate);
    }





}
