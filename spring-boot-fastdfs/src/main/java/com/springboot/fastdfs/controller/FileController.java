package com.springboot.fastdfs.controller;

import com.springboot.fastdfs.common.config.FastDFSClient;
import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FastDFSClient fastDFSClient;

    @PostMapping("/upload")
    public String fileUpload(HttpServletRequest request, MultipartFile file) throws IOException, MyException {

        byte[] fileBytes = file.getBytes();
        /*System.out.println(strArray.length);
        System.out.println("groupName:" + strArray[0]);
        System.out.println("remoteFilename:" + strArray[1]);
        for (int i = 0; i < strArray.length; i++) {
            System.out.println(strArray[i]);
        }*/


        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index + 1);
        //注意:第二个参数是扩展名,不是文件名
        String remoteUrl = fastDFSClient.uploadFile(fileBytes, suffix, null);

        StringBuilder fileUrl = new StringBuilder("http://");
        String hostString = fastDFSClient.getTrackerServer().getInetSocketAddress().getHostString();
        fileUrl.append(hostString).append("/").append(remoteUrl);
        return fileUrl.toString();
    }

    @PostMapping("/download")
    public void fileDownload(String groupName, String remoteFilename) throws Exception {
        int index = remoteFilename.lastIndexOf("/");
        String fileName = remoteFilename.substring(index + 1);
        File file = new File("D:\\" + fileName);
        int result = fastDFSClient.downloadFile(groupName, remoteFilename, file);
        System.out.println(result);
    }

}
