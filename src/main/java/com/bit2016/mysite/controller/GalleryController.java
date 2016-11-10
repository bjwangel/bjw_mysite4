package com.bit2016.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.mysite.service.GalleryService;
import com.bit2016.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model){
		List<GalleryVo> list=galleryService.show();
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("list", list);
		
		model.addAttribute("showAll", map);
		return "gallery/index";
	}
		
	@RequestMapping("/form")
	public String form(){
		return "gallery/form";
	}
	
	public String upload(@RequestParam(value="file")MultipartFile file,Model model){
		String url=galleryService.restore(file);
		
		model.addAttribute("url", url);
		return "gallery/index";
	}
}
