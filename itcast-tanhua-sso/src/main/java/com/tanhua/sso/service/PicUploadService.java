package com.tanhua.sso.service;

import com.aliyun.oss.OSSClient;
import com.tanhua.sso.config.AliyunConfig;
import com.tanhua.sso.vo.PicUploadResult;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.sso.service.PicUploadService
 * @Date: 2022年05月22日 12:59
 */
@Service
public class PicUploadService {

    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private AliyunConfig aliyunConfig;

    public PicUploadResult upload(MultipartFile uploadFile) {
        PicUploadResult fileUploadResult = new PicUploadResult();
        //验证文件类型
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }
        if (!isLegal) {
            fileUploadResult.setStatus("error");
            return fileUploadResult;
        }
        //文件路径
        String fileName = uploadFile.getOriginalFilename();
        String filePath = getFilePath(fileName);

        // 开始上传
        try {
            ossClient.putObject(aliyunConfig.getBucketName(), filePath, new ByteArrayInputStream(uploadFile.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            //上传失败
            fileUploadResult.setStatus("error");
        }
        //上传成功
        fileUploadResult.setStatus("done");
        fileUploadResult.setName(aliyunConfig.getUrlPrefix() + filePath);
        fileUploadResult.setUid(String.valueOf(System.currentTimeMillis()));
        return fileUploadResult;
    }

    private String getFilePath(String fileName) {
        DateTime dateTime = new DateTime();
        return "images/"
                + dateTime.toString("yyyy") + "/"
                + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/"
                + System.currentTimeMillis()
                + RandomUtils.nextInt(100, 9999) + "."
                + StringUtils.substringAfterLast(fileName, ".");

    }
}
