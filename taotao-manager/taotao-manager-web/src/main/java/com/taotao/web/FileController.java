package com.taotao.web;

import com.taotao.dto.ExecuteJsonResult;
import com.taotao.dto.FileInput;
import com.taotao.service.FileInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by chenlunwei on 2017/5/8.
 */

@RequestMapping("/taotao/manager")
@Controller
public class FileController {
	@Autowired
	private FileInputService fileInputService;

	@RequestMapping(value = "/picture/upload",method = RequestMethod.POST)
	@ResponseBody
	public FileInput uploadFile(MultipartFile imageInputFile){
		FileInput fileInput = fileInputService.uploadFile(imageInputFile);
		return fileInput;
	}

	@RequestMapping(value = "/picture/delete",method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteFile(String key,String baseroot){
		return fileInputService.deleteFile(baseroot,key);
	}

	@RequestMapping(value = "/HTMLEditor/Add/Image",method = RequestMethod.POST)
	@ResponseBody
	public ExecuteJsonResult<Map<String,String>> uploadEditorFile(MultipartFile EditorImage){
		return fileInputService.uploadFileByEditor(EditorImage);
	}
}
