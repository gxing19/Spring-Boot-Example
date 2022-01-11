package com.gxitsky.Controller;

import com.gxitsky.common.init.SpringBootInitData;
import com.gxitsky.handler.VerifyHandler;
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
