package com.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.ProductAttr;
import com.model.ProductAttrValue;
import com.service.ProductAttrService;
import com.service.ProductAttrValueService;

@RequestMapping("/ProductAttr")
@Controller
public class ProductAttrController {
     @Autowired
	private ProductAttrService productAttrService;
     @Autowired
     private ProductAttrValueService productAttrValueService;
     
     //��ʾȫ�� ��ҳ��
	@RequestMapping("/toProductAttr")
	public String toProductAttr(ProductAttr productAttr,ModelMap modelMap) {
		modelMap.addAttribute("PaList", productAttrService.selectAll(productAttr));
		return "/attr/list-attr";
	}
	
	//���ݻ���
	@RequestMapping("/toAdd")
	public String toAdd(ProductAttr productAttr,ModelMap modelMap){
		if(productAttr.getId()!=null){
			modelMap.addAttribute("Pas",productAttrService.selectAll(productAttr).get(0));
		}
		return "/attr/edit-attr";
	}
	//����
	@RequestMapping("/add")
	public String add(ProductAttr productAttr){
			if(productAttr.getId()!=null){
				productAttrService.updateMain(productAttr);
			}
			else{
			productAttrService.add(productAttr);
			productAttrValueService.insertList(productAttr);
			}
			return "redirect:/ProductAttr/toProductAttr";
	}
	@ResponseBody
	@RequestMapping("/del")
	public String del(Integer id){
		if(productAttrService.delete(id)){
			return "success";
		}
		else
			return "error";
	}
	
}

