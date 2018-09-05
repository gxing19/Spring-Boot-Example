package com.springboot.websecurity.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.websecurity.common.utils.ObjectToMapUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Name: IndexController
 * @Desc: TODO
 * @User: gxing
 * @Date: 2018-09-05 18:16
 **/
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String indexPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication:{}" + JSON.toJSONString(authentication));
        Object principal = authentication.getPrincipal();
        Map<String, String> stringMap = ObjectToMapUtil.obj2Map(principal);
        model.addAttribute("username", stringMap.get("username"));
        return "index";
    }
}