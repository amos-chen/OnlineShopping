package com.taotao.web;

import com.taotao.dto.FileInput;
import com.taotao.service.FileInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by chenlunwei on 2017/5/8.
 */

@RequestMapping("/taotao/manager")
@Controller
public class FileController {
	@Autowired
	private FileInputService fileInputService;

	@RequestMapping("/picture/upload")
	@ResponseBody
	public FileInput uploadFile(MultipartFile imageInputFile){
		FileInput fileInput = fileInputService.uploadFile(imageInputFile);
		return fileInput;
	}

	@RequestMapping("/picture/delete")
	@ResponseBody
	public boolean deleteFile(String key,String baseroot){
		return fileInputService.deleteFile(baseroot,key);
	}
}
