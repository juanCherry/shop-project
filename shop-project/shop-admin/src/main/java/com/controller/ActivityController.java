package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Activity;
import com.model.AvtivityCouponRelation;
import com.model.Coupon;
import com.service.ActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;

	/**
	 * ��ת���ҳ��
	 * @param activity
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/toactivity")
	public String toactivity(Activity activity,ModelMap modelMap){
		modelMap.addAttribute("list", activityService.selectActivity(activity));		
		return "/activity/list-activity";
		
	}
	/**
	 * ���ȫ�����Ż�ȯ������jspҳ������
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectCoupon")
	public List<Coupon> selectCoupon(){
		List<Coupon> list=activityService.selectCoupon();
		return list;
	}
	
	/**
	 * �жϵ�ǰ�Ƿ������ڽ��еĻ
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isCurrent")
	public String isCurrent(){

		if (activityService.isCurrent()) {		
			return "success";
		}
		return "fail";
	}
	
	/**
	 * ��ת������ҳ��
	 * @param activity
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/toupdate")
	public String toupdate(Activity activity,ModelMap modelMap){
		
		if (activity.getId()!=null) {			
			modelMap.addAttribute("activity", activityService.selectActivity(activity).get(0));			
		}
		return "/activity/edit-activity";		
	}
	
	
	/**
	 * �����Ż�ȯ
	 * @param activity
	 * @param cid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addCoupon")
	public String addCoupon(Activity activity,@RequestParam( name="cid")Integer cid){
		
		if (activityService.addCoupon(activity, cid)) {		
			return "success";
		}
		return "fail";
	}
	
	/**
	 * �޸Ļ
	 * @param activity
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public String update(Activity activity,ModelMap modelMap){
		if (activity.getId()!=null) {
			if(activityService.updateActivity(activity))
			return "success";		
		}else{
			if(activityService.insertActivity(activity))
				return "success";	
		}
		
		return "fail";		
	}
	
	/**
	 * ɾ���Ż�ȯ���ڵ�����ٰ�ťʱ�������0����ɾ�������Ż�ȯ
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteCouponById")
	public String deleteCouponById(Integer id){
		
		if (activityService.deleteCouponById(id)) {
			return "success";
		}
		return "fail";
	}
	
	/**
	 * ����ɾ���
	 * @param activity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delActivity")
	public String delActivity(Activity activity){
		
		if (activityService.delActivity(activity)) {
			return "success";
		}
		return "fail";
	}
	
	
}