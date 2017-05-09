package com.taotao.service;

import com.taotao.dto.FileInput;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by chenlunwei on 2017/5/8.
 */
public interface FileInputService {

	FileInput uploadFile(MultipartFile multipartFile);

	boolean deleteFile(String filePath,String fileName);
}
