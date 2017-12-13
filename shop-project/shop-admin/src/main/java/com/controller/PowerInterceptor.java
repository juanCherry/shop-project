package com.controller;

import java.util.List;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.model.AdminUser;
import com.model.Power;
import com.model.PowerRoleRelation;
import com.model.Role;

/**
 * ����������ʵ����������������Ĺ��ܣ��ж��Ѿ���¼���û��Ƿ��и������Ȩ�� ���û�е�¼�� ���ص�¼ҳ�� ���û��Ȩ�޷��ص�ǰҳ��
 * 
 * @author Administrator
 *
 */
public class PowerInterceptor implements HandlerInterceptor {

	/**
	 * ��ȡ��ǰ������������жϣ�����з���Ȩ�޲����Ѿ���¼�򷵻�true
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		String requestURI = request.getRequestURI();// ��ȡ����·��
		String contentPath = request.getContextPath();
		String url = requestURI.substring(contentPath.length());// ����������·��
		System.out.println("---------" + url + "--preHandle----------");
		AdminUser loginUser = (AdminUser) request.getSession().getAttribute("loginUser");
		if (loginUser != null) {
			Role r = (Role) request.getSession().getAttribute("role");
			for (PowerRoleRelation prr : r.getPowerRoleRelation()) {
				if (prr.getPower() != null) {
					for (Power p : prr.getPower().getSubPowers()) {
						if (p != null && p.getPowerUrl().contains(url)) {
							return true;
						}
					}

					if (prr.getPower().getPowerUrl().contains(url)) {
						return true;
					}
				}

			}

		}

		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("-----------postHandle----------");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("-----------afterCompletion----------");

	}

}
