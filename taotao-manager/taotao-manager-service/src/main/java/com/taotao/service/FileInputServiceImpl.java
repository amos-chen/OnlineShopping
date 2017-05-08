package com.taotao.service;

import com.taotao.dto.FileInput;
import com.taotao.utils.IDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by chenlunwei on 2017/5/8.
 */
@Service
public class FileInputServiceImpl implements FileInputService{
	@Value("${FTP.HOST}")
	private String FTP_HOST;
	@Value("${FTP.PORT}")
	private String FTP_PORT;
	@Value("${FTP.USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP.PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP.BASWPATH}")
	private String FTP_BASWPATH;


	@Override
	public FileInput uploadFile(MultipartFile multipartFile) {
		//获取上传文件的名称
		String oldName = multipartFile.getOriginalFilename();
		//根据时间获取一个随机字符串
		String base = IDUtils.genImageName();
		//拼接图片新的名称
		String newName = base + oldName.substring(oldName.lastIndexOf("."));
		//上传图片到服务器
//		FtpUtil.uploadFile();


		return null;
	}
}
