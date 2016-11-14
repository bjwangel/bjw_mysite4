package com.bit2016.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.mysite.service.GalleryService;
import com.bit2016.mysite.vo.GalleryVo;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

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
	@Auth
	@RequestMapping("/form")
	public String form(){
		return "gallery/form";
	}
	
	@Auth
	@RequestMapping("/upload")
	public String upload(@RequestParam(value="file")MultipartFile file,
						 @AuthUser UserVo authUser,
						 @ModelAttribute GalleryVo vo,
						 Model model){
		
		vo.setNo(authUser.getNo());
		String url=galleryService.restore(file,vo);
		
		model.addAttribute("url", url);
		return "gallery/index";
	}
}
