package com.qcl.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 官网给的测试类
 * https://github.com/happyfish100/fastdfs-client-java/blob/master/src/test/java/org/csource/fastdfs/Test.java
 *
 * @author legend
 * @version 1.0
 * @date 2020/9/15
 */
public class TestFastDFS {

    /**
     * 测试文件上传
     * <p>
     * 控制台的上传结果：
     * network_timeout=30000ms
     * charset=UTF-8
     * upload success. file id is: group1/M00/00/00/wKgAbF9gu1uAAuSfADp79xj7d4M434.jpg
     */
    @Test
    public void testUpload() {
        try {
            //加载fastDFS客户端的配置文件
            //ClientGlobal.init(conf_filename);
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);

            //创建tracker客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            //文件元信息
            NameValuePair[] metaList = new NameValuePair[1];
            //metaList[0] = new NameValuePair("fileName", local_filename);
            metaList[0] = new NameValuePair("fileName", "6.jpg");

            //执行上传
            //String fileId = client.upload_file1(local_filename, null, metaList);
            String fileId = client.upload_file1("D:\\WorkCode\\uploadimages\\6.jpg", "jpg", metaList);
            System.out.println("upload success. file id is: " + fileId);

            //关闭tracker服务的连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 查询文件信息
     * <p>
     * 控制台输出结果：
     * network_timeout=30000ms
     * charset=UTF-8
     * 第一种查询文件的方法:source_ip_addr = 192.168.0.108, file_size = 3832823, create_timestamp = 2020-09-15 21:02:19, crc32 = 419133315
     * 第二种查询文件的方法:source_ip_addr = 192.168.0.108, file_size = 3832823, create_timestamp = 2020-09-15 21:02:19, crc32 = 419133315
     */
    @Test
    public void testQuery() {
        try {
            //加载fastDFS客户端的配置文件
            //ClientGlobal.init(conf_filename);
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);

            //创建tracker客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            //第一种查询文件的方法
            FileInfo queryFileInfo = client.query_file_info("group1", "M00/00/00/wKgAbF9gu1uAAuSfADp79xj7d4M434.jpg");
            System.out.println("第一种查询文件的方法:" + queryFileInfo);

            //第二种查询文件的方法
            FileInfo queryFileInfo1 = client.query_file_info1("group1/M00/00/00/wKgAbF9gu1uAAuSfADp79xj7d4M434.jpg");
            System.out.println("第二种查询文件的方法:" + queryFileInfo1);


            //查询文件元信息
            NameValuePair[] metadata1 = client.get_metadata1("group1/M00/00/00/wKgAbF9gu1uAAuSfADp79xj7d4M434.jpg");
            System.out.println("查询文件元信息:" + metadata1);

            //关闭tracker服务的连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 下载文件
     * <p>
     * 控制台输出结果：
     * network_timeout=30000ms
     * charset=UTF-8
     * 第一种查询文件的方法:source_ip_addr = 192.168.0.108, file_size = 3832823, create_timestamp = 2020-09-15 21:02:19, crc32 = 419133315
     * 第二种查询文件的方法:source_ip_addr = 192.168.0.108, file_size = 3832823, create_timestamp = 2020-09-15 21:02:19, crc32 = 419133315
     */
    @Test
    public void testDownload() {
        try {
            //加载fastDFS客户端的配置文件
            //ClientGlobal.init(conf_filename);
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);

            //创建tracker客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            //第一种下载文件的方法
            byte[] downloadFile = client.download_file("group1", "M00/00/00/wKgAbF9gu1uAAuSfADp79xj7d4M434.jpg");
            File file = new File("D:\\WorkCode\\uploadimages\\download_file-2020-09-15.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(downloadFile);
            fileOutputStream.close();
            System.out.println("第一种下载文件方法结束:");

            //第二种下载文件的方法
            byte[] downloadFile1 = client.download_file1("group1/M00/00/00/wKgAbF9gu1uAAuSfADp79xj7d4M434.jpg");
            File file1 = new File("D:\\WorkCode\\uploadimages\\download_file1-2020-09-15.jpg");
            FileOutputStream fileOutputStream1 = new FileOutputStream(file1);
            fileOutputStream1.write(downloadFile1);
            fileOutputStream1.close();
            System.out.println("第二种下载文件方法结束:");


            //关闭tracker服务的连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}