package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.ProductAttr;
import com.model.ProductAttrValue;
import com.service.ProductAttrValueService;
//��Ʒ����ֵ
@RequestMapping("/ProductAttrValue")
@Controller
public class ProductAttrValueController {
	@Autowired
	private ProductAttrValueService productAttrValueService;
	//ajax����
    @ResponseBody
	@RequestMapping("/insert")
	private String insert(ProductAttrValue productAttrValue) {
		if (productAttrValueService.insert(productAttrValue)) {
			return "success";
		}
		return "error";
	}
    //ajax����ȫ��
    @ResponseBody
    @RequestMapping("/selectAll")
    public List<ProductAttrValue> selectAll(ProductAttr productAttr){
    	return productAttrValueService.selectAll(productAttr);
    }
    //
    @ResponseBody
    @RequestMapping("/dele")
    public String dele(Integer id){
    	if(productAttrValueService.delete(id))
    	return "success";
    	return "error";
    }
}
