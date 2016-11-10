package com.bit2016.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.mysite.repository.GalleryDao;
import com.bit2016.mysite.vo.GalleryVo;

@Service
public class GalleryService {

	private static final String SAVE_PATH = "/upload"; 
	private static final String URL = "/gallery/assets/"; 
	
	@Autowired
	private GalleryDao galleryDao;
	
	
	public List<GalleryVo> show(){
		return galleryDao.show();
	}
	
	
	public String restore(MultipartFile multipartFile){
		String url = "";
		try {
			GalleryVo galleryVo=null;
			
			if (multipartFile.isEmpty() == true) {
				return url;
			}
			String originalFileName = multipartFile.getOriginalFilename(); 
			String extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1,originalFileName.length());
			String saveFileName = generateSaveFileName(extName);
			Long fileSize = multipartFile.getSize();

			writeFile(multipartFile, saveFileName);

			url = URL + saveFileName;

		} catch (IOException e) {
			throw new RuntimeException("upload file");
		}
		return url;
	}
	
	
	private String generateSaveFileName(String extName) {

		String fileName = "";
		Calendar calendar = Calendar.getInstance();

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}

	private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException { 
		byte[] fileData = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName); 																		// 만들면
																						
		fos.write(fileData);
	}
	
	public void insert(MultipartFile multipartFile){
		
	}
	
}
