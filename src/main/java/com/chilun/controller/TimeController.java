package com.chilun.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 齿轮
 * @date 2023-12-04-9:19
 */
@RestController
@RequestMapping("/")
public class TimeController {

    @RequestMapping("/time")
    public String getCurrentTime(@RequestParam(required = false) String zone, HttpServletResponse response) {
        //格式化
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //默认情况输出默认UTC时区数据，否则输出指定时期
        if (zone != null) {
            date.setTimeZone(TimeZone.getTimeZone(zone));
        }
        String format = date.format(Calendar.getInstance().getTime());
        System.out.println(format);
        response.setHeader("ChilunAPISpace-cost","-0.5");
        return format;
    }

//    @GetMapping("/**")
//    public String getCurrentTime2(@RequestParam(required = false) String zone) {
//        //格式化
//        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //默认情况输出默认UTC时区数据，否则输出指定时期
//        if (zone != null) {
//            date.setTimeZone(TimeZone.getTimeZone(zone));
//        }
//        return date.format(Calendar.getInstance().getTime());
//    }
}
