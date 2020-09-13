package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;


/**
 * 文件上传service
 *
 * @author legend
 */
@Service
public class UploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    /**
     * 定义一个文件服务器地址
     */
    private static final String HOST_IMAGE_URL = "/user/legend/images";

    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * 存储所有合法的类型
     */
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif","image/jpeg");

    public String uploadImage(MultipartFile file) {
        //校验文件类型
        String originalFilename = file.getOriginalFilename();
        //第一种获取文件类型
        //originalFilename.split(".")
        //第二种
        //后面的最后一个字符串
        StringUtils.substringAfterLast(originalFilename,".");
        //第三种
        try {
            String contentType = file.getContentType();
            if (!CONTENT_TYPES.contains(contentType)) {
                LOGGER.info("文件类型不合法...{}",originalFilename);
                //非法
                return null;
            }
            //校验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                LOGGER.info("文件内容不合法...{}",originalFilename);
                return null;
            }

            //保存到服务器
            //file.transferTo(new File(HOST_IMAGE_URL));

            //返回url进行会写
            //return "http://image.leyou.com/" + originalFilename;

            //获取文件的扩展名
            String fileNameExt = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), fileNameExt, null);
            return "http://image.leyou.com/" + storePath;
        } catch (Exception e) {
            LOGGER.info("服务器内部错误.....{}",originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
