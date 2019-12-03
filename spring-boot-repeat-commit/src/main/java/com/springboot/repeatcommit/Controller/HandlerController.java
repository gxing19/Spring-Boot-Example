package com.springboot.repeatcommit.Controller;

import com.springboot.repeatcommit.common.init.SpringBootInitData;
import com.springboot.repeatcommit.handler.VerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/handler")
public class HandlerController {

    @Autowired
    private SpringBootInitData bootInitData;

    @RequestMapping("/verify")
    public void verifyData() {

        VerifyHandler handler = new VerifyHandler(bootInitData);

        handler.printUser();
        System.out.println();

    }
}
