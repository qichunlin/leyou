package com.qcl.fastdfs.controller;

import com.qcl.fastdfs.entity.FileSystem;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLDecoder;
import java.util.UUID;

/**
 * 文件系统上传Controller
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2020/9/15
 */
@Slf4j
@Controller
@RequestMapping("/fastdfs")
public class FileSystemController {

    /**
     * 临时文件上传路径
     */
    @Value("${qcl-fastdfs.upload-location}")
    private String fastDFSUploadLocation;

    @PostMapping("/upload")
    @ResponseBody
    public FileSystem upload(@RequestParam("multipartFile") MultipartFile multipartFile) throws Exception {
        System.out.println("hello");
        //将文件先存储在web服务器上(本机),再调用fastDFS的client将文件上传到fastDFS服务器
        FileSystem fileSystem = new FileSystem();

        //获取文件的原始名字
        String originalFilename = multipartFile.getOriginalFilename();
        log.info("获取到的文件名字:{}", originalFilename);

        //获取文件的扩展名
        String extendName = StringUtils.substringAfterLast(originalFilename, ".");
        //String extendName2 = originalFilename.substring(originalFilename.lastIndexOf("."));

        //定义File,使用File存储上传的文件
        String fileNameTempNew = "fastdfs" + UUID.randomUUID() + "." + extendName;
        log.info("新生成临时文件的图片名字:{}", fileNameTempNew);
        File file = new File(fastDFSUploadLocation + fileNameTempNew);
        //File file = new File("D:\\WorkCode\\uploadimages\\" + fileNameTempNew);
        multipartFile.transferTo(file);

        //获取新上传的图片文件地址
        String fileNameTempPath = file.getAbsolutePath();
        try {
            //加载fastDFS客户端的配置文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            log.info("network_timeout= {} ms", ClientGlobal.g_network_timeout);
            log.info("charset= {}", ClientGlobal.g_charset);

            //创建tracker客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            //文件元信息
            NameValuePair[] metaList = new NameValuePair[1];
            metaList[0] = new NameValuePair("fileName", originalFilename);

            //执行上传
            String fileId = client.upload_file1(fileNameTempPath, extendName, metaList);
            log.info("upload success. file id is: {}", fileId);

            //设置文件系统对象对应的属性
            fileSystem.setFileId(fileId);
            fileSystem.setFilePath(fileId);
            fileSystem.setFileName(originalFilename);
            fileSystem.setFileAddress(client.get_file_info1(fileId).getSourceIpAddr());
            fileSystem.setFileSize(client.get_file_info1(fileId).getFileSize());
            fileSystem.setFileName(client.get_metadata1(fileId)[0].getValue());
            fileSystem.setFileType(extendName);

            //TODO 通过调用service几dao将文件存储到数据库中

            log.info("完整的FileSystem对象信息:{}", fileSystem);
            //关闭tracker服务的连接
            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fileSystem;
    }

    /**
     * 跳转到upload上传页面
     *
     * @return
     */
    @GetMapping("/show-upload")
    public String method() {
        System.out.println("hello");
        return "upload";
    }
}
