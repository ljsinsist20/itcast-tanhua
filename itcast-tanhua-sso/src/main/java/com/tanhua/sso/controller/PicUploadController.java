package com.tanhua.sso.controller;

import com.tanhua.sso.service.PicUploadService;
import com.tanhua.sso.vo.PicUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.sso.controller.PicUploadController
 * @Date: 2022年05月22日 15:45
 */
@RequestMapping("pic/upload")
@RestController
public class PicUploadController {

    @Autowired
    private PicUploadService picUploadService;

    @PostMapping
    public PicUploadResult upload(@RequestParam("file") MultipartFile multipartFile) {
        return picUploadService.upload(multipartFile);
    }
}
