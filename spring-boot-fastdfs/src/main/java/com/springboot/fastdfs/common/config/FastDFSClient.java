package com.springboot.fastdfs.common.config;

import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.csource.fastdfs.pool.Connection;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * FastDFS 客户端
 */
@Configuration
public class FastDFSClient {

    public static final String CONFIG_PROPERTIES = "fastdfs-client.properties";
    public static final String GROUP_NAME = "group1";
    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient1 storageClient = null;

    static {
        try {
            ClientGlobal.initByProperties(CONFIG_PROPERTIES);
            System.out.println(ClientGlobal.configInfo());
            trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            trackerServer = trackerClient.getTrackerServer();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient = new StorageClient1(trackerServer, storageServer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化配置
     *
     * @throws IOException
     * @throws MyException
     */
    /*@PostConstruct
    public void clientGlobal() throws IOException, MyException {
        ClientGlobal.initByProperties(CONFIG_PROPERTIES);
        //System.out.println(ClientGlobal.configInfo());
        this.trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
        this.trackerServer = this.trackerClient.getTrackerServer();
        this.storageServer = this.trackerClient.getStoreStorage(this.trackerServer);
        this.storageClient = new StorageClient1(trackerServer, storageServer);
    }*/

    /**
     * 文件上传
     *
     * @param file 文件
     * @param fileExtName 扩展名
     * @param metaList 无数据
     * @return
     * @throws IOException
     * @throws MyException
     */
    public String uploadFile(File file, String fileExtName, Map<String, String> metaList) throws IOException, MyException {
        byte[] buff = IOUtils.toByteArray(new FileInputStream(file));
        return uploadFile(buff, fileExtName, metaList);
    }

    public String uploadFile(byte[] buff, String fileExtName, Map<String, String> metaList) throws IOException, MyException {
        NameValuePair[] nameValuePairs = null;
        if (metaList != null) {
            nameValuePairs = new NameValuePair[metaList.size()];
            int index = 0;
            for (Iterator<Map.Entry<String, String>> iterator = metaList.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, String> entry = iterator.next();
                String name = entry.getKey();
                String value = entry.getValue();
                nameValuePairs[index++] = new NameValuePair(name, value);
            }
        }
        return storageClient.upload_file1(GROUP_NAME, buff, fileExtName, nameValuePairs);
    }

    /**
     * 获取文件元数据
     *
     * @param groupName
     * @param fileId
     * @return
     */
    public Map<String, String> getFileMetadata(String groupName, String fileId) {
        try {
            if (StringUtils.isEmpty(groupName)) {
                groupName = GROUP_NAME;
            }
            NameValuePair[] metaList = storageClient.get_metadata(groupName, fileId);
            if (metaList != null) {
                HashMap<String, String> map = new HashMap<String, String>();
                for (NameValuePair metaItem : metaList) {
                    map.put(metaItem.getName(), metaItem.getValue());
                }
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载文件
     *
     * @param groupName
     * @param remoteFilename
     * @param outFile
     * @return
     */
    public int downloadFile(String groupName, String remoteFilename, File outFile) {
        FileOutputStream fos = null;
        try {
            if (StringUtils.isEmpty(groupName)) {
                groupName = GROUP_NAME;
            }
            byte[] content = storageClient.download_file(groupName, remoteFilename);
            fos = new FileOutputStream(outFile);
            InputStream ips = new ByteArrayInputStream(content);
            IOUtils.copy(ips, fos);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    /**
     * 删除文件
     *
     * @param groupName
     * @param remoteFilename
     * @return
     */
    public int deleteFile(String groupName, String remoteFilename) {
        try {
            return storageClient.delete_file(groupName, remoteFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public TrackerClient getTrackerClient() {
        return trackerClient;
    }

    public TrackerServer getTrackerServer() {
        return trackerServer;
    }

    public StorageServer getStorageServer() {
        return storageServer;
    }

    public StorageClient getStorageClient() {
        return storageClient;
    }
}
