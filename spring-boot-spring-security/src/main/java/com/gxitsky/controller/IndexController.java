package com.gxitsky.controller;

import com.alibaba.fastjson.JSON;
import com.gxitsky.entity.SysUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Name: IndexController
 * @Desc: TODO
 * @User: gxing
 * @Date: 2018-09-05 18:16
 **/
@Controller
public class IndexController {

    private final Logger logger = LogManager.getRootLogger();

    @RequestMapping("/index")
    public String indexPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("authentication:{}", JSON.toJSONString(authentication));
        //返回的是个用户实体
        SysUser sysUser = (SysUser) authentication.getPrincipal();
//        Object principal = authentication.getPrincipal();
//        Map<String, String> stringMap = ObjectToMapUtil.obj2Map(principal);
        model.addAttribute("username", sysUser.getUsername());
        return "index";
    }
}