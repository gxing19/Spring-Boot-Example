package com.gxitsky.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/map")
public class HashMapController {

    @RequestMapping("/hashMap")
    public void hashMap() {
        HashMap<String, String> hashMap = new HashMap<>(17);


        System.out.println(JSON.toJSONString(hashMap));
    }
}
