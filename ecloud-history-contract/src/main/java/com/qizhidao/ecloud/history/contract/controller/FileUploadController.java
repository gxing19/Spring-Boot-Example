/**
 * Copyright © 2018, ZhiYun Network Co.,Ltd. All rights reserved. ZhiYun PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package com.qizhidao.ecloud.history.contract.controller;

import com.qizhidao.ecloud.history.contract.entity.CurrentUser;
import com.qizhidao.ecloud.history.contract.service.IContractService;
import com.qizhidao.ecloud.history.contract.service.ICopyrightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.io.IOException;

@RestController
@RequestMapping(path = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FileUploadController {


    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);


    @Resource
    private Validator validator;

    @Resource
    private IContractService contractService;
    @Resource
    private ICopyrightService copyrightService;



    @PostMapping(path = "excel")
    public void fileUpload(@RequestPart("file") MultipartFile file) {
        CurrentUser user = new CurrentUser();
        try {
            copyrightService.importCopyright(file, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
