package com.superloop.interview.todo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public static Date parse(String dateStr) throws ParseException{
    	if(StringUtils.isEmpty(dateStr)){
    		return null;
    	}else{
    		return format.parse(dateStr);
    	}
    }
}
