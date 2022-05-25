package com.tanhua.sso.service;

import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FunctionConfiguration;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.enums.ImageFormat;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.sso.service.FaceEngineService
 * @Date: 2022年05月24日 22:01
 */
@Service
public class FaceEngineService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaceEngineService.class);

    @Value("${arcsoft.appid}")
    private String appId;
    @Value("${arcsoft.sdkKey}")
    private String sdkKey;
    @Value("${arcsoft.libPath}")
    private String libPath;

    private FaceEngine faceEngine;

    @PostConstruct
    public void init() {
        FaceEngine faceEngine = new FaceEngine(libPath);
        int activeCode = faceEngine.activeOnline(appId, sdkKey);
        if (activeCode != ErrorInfo.MOK.getValue() && activeCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            LOGGER.error("引擎激活失败");
            throw new RuntimeException("引擎激活失败");
        }
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        //人脸检测角度，逆时针0度
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);

        engineConfiguration.setFunctionConfiguration(functionConfiguration);
        //init引擎
        int initCode = faceEngine.init(engineConfiguration);
        if (initCode != ErrorInfo.MOK.getValue()) {
            LOGGER.error("初始化引擎出错!");
            throw new RuntimeException("初始化引擎出错!");
        }
        this.faceEngine = faceEngine;
    }

    public boolean checkIsPortrait(ImageInfo imageInfo) {
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        faceEngine.detectFaces(
                imageInfo.getImageData(),
                imageInfo.getWidth(),
                imageInfo.getHeight(),
                ImageFormat.CP_PAF_BGR24, faceInfoList);
        return !faceInfoList.isEmpty();
    }

    public boolean checkIsPortrait(byte[] imageData) {
        return this.checkIsPortrait(ImageFactory.getRGBData(imageData));
    }

    public boolean checkIsPortrait(File file) {
        return this.checkIsPortrait(ImageFactory.getRGBData(file));
    }
}


