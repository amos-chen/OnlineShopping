package com.taotao.service;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.dto.FileConfiguration;
import com.taotao.dto.FileInput;
import com.taotao.utils.FtpUtil;
import com.taotao.utils.IDUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlunwei on 2017/5/8.
 */
@Service
public class FileInputServiceImpl implements FileInputService {
    @Value("${FTP.HOST}")
    private String FTP_HOST;
    @Value("${FTP.PORT}")
    private Integer FTP_PORT;
    @Value("${FTP.USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP.PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP.BASEPATH}")
    private String FTP_BASEPATH;
    @Value("${IMAGE_URL}")
    private String IMAGE_URL;
    @Value("${UPLOAD_FAIL}")
    private String UPLOAD_FAIL;
    @Value("${DELETE_URL}")
    private String DELETE_URL;


    @Override
    public FileInput uploadFile(MultipartFile multipartFile) {
        String imagePath = new DateTime().toString("/yyyy/MM/dd/");

        //获取上传文件的名称
        String oldName = multipartFile.getOriginalFilename();
        //根据时间获取一个随机字符串
        String base = IDUtils.genImageName();
        //拼接图片新的名称
        String newName = base + oldName.substring(oldName.lastIndexOf("."));
        //上传图片到服务器
        try {
            boolean result = FtpUtil.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH,
                    imagePath, newName, multipartFile.getInputStream());
            if (result) {
                String deleteURL = DELETE_URL;
                String Captain = newName;
                String initialPreview = "<img src='" + IMAGE_URL + imagePath + newName + "' class='file-preview-image' alt='" + newName + "' title='" + newName + "'>";
                FileConfiguration fileConfiguration = new FileConfiguration();
                fileConfiguration.setCaption(newName);
                fileConfiguration.setUrl(deleteURL);
                Map<String, String> extra = new HashMap<>();
                extra.put("baseroot", FTP_BASEPATH + imagePath);
                extra.put("imageURL", IMAGE_URL + imagePath + newName);
                fileConfiguration.setExtra(extra);
                fileConfiguration.setKey(newName);
                fileConfiguration.setWidth("160px");
                List<FileConfiguration> initialPreviewConfig = new ArrayList<>();
                initialPreviewConfig.add(fileConfiguration);
                return new FileInput(initialPreview, initialPreviewConfig);
            } else {
                String error = "文件上传失败!";
                FileConfiguration fileConfiguration = new FileConfiguration();
                fileConfiguration.setCaption(error);
                fileConfiguration.setWidth("160px");
                List<FileConfiguration> initialPreviewConfig = new ArrayList<>();
                initialPreviewConfig.add(fileConfiguration);
                return new FileInput(error, UPLOAD_FAIL, initialPreviewConfig);
            }
        } catch (IOException e) {
            e.printStackTrace();
            String error = "文件上传出现错误!";
            FileConfiguration fileConfiguration = new FileConfiguration();
            fileConfiguration.setCaption(error);
            fileConfiguration.setWidth("160px");
            List<FileConfiguration> initialPreviewConfig = new ArrayList<>();
            initialPreviewConfig.add(fileConfiguration);
            return new FileInput(error, UPLOAD_FAIL, initialPreviewConfig);
        }
    }

    @Override
    public boolean deleteFile(String extra, String key) {
        boolean flag = FtpUtil.deleteFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
                extra, key);
        return flag;
    }

    @Override
    public ExecuteJsonResult<Map<String, String>> uploadFileByEditor(MultipartFile imageFile) {
        String imagePath = new DateTime().toString("/yyyy/MM/dd/");

        //获取上传文件的名称
        String oldName = imageFile.getOriginalFilename();
        //根据时间获取一个随机字符串
        String base = IDUtils.genImageName();
        //拼接图片新的名称
        String newName = base + oldName.substring(oldName.lastIndexOf("."));
        //上传图片到服务器
        ExecuteJsonResult<Map<String, String>> result;
        try {
            boolean flag = FtpUtil.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH,
                    imagePath, newName, imageFile.getInputStream());
            if (flag) {
                Map<String, String> resultMap = new HashMap<>();
                String url = IMAGE_URL + imagePath + newName;
                resultMap.put("url",url);
                resultMap.put("filename",newName);
                resultMap.put("extra",FTP_BASEPATH);
                result = new ExecuteJsonResult<Map<String, String>>(true,resultMap);
            }else{
                result = new ExecuteJsonResult<Map<String, String>>(false,"图片上传失败");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ExecuteJsonResult<Map<String, String>>(false,"图片上传出现异常");
        }

    }
}
