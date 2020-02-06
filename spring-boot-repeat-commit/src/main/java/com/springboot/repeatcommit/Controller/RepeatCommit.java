package com.springboot.repeatcommit.Controller;

import com.alibaba.fastjson.JSON;
import com.springboot.repeatcommit.common.config.AvoidRepeatableCommit;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/repeat")
public class RepeatCommit {

    //重复提交测试
    @RequestMapping(value = "formCommit")
    @AvoidRepeatableCommit(timeout = 3)
    public String testCommit(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("success", false);
        }
        return JSON.toJSONString(resultMap);
    }
}
