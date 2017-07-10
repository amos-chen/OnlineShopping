package com.taotao.service;

import com.taotao.dto.FileInput;
import com.taotao.dto.ExecuteJsonResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by chenlunwei on 2017/5/8.
 */
public interface FileInputService {

	FileInput uploadFile(MultipartFile multipartFile);

	boolean deleteFile(String filePath,String fileName);

	ExecuteJsonResult<Map<String,String>> uploadFileByEditor(MultipartFile multipartFile);
}
