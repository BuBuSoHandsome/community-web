package com.example.demo.CommonTool;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class TimeTool {

    /**
     * 获取timestamp类型的系统当前时间
     * @return
     */
    public Timestamp getSystemTime(){
        Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
}
