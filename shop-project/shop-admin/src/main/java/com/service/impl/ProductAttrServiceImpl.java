package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductAttrMapper;
import com.dao.ProductAttrValueMapper;
import com.model.ProductAttr;
import com.model.ProductAttrValue;
import com.service.ProductAttrService;
import com.service.ProductAttrValueService;

@Service
public class ProductAttrServiceImpl implements ProductAttrService {
	@Autowired
	private ProductAttrMapper ProductAttrMapper;
	@Autowired
	private ProductAttrValueService productAttrValueService;

	@Override
	public List<ProductAttr> selectAll(ProductAttr productAttr) {

		return ProductAttrMapper.selectAll(productAttr);
	}

	@Override
	public boolean add(ProductAttr productAttr) {

		return ProductAttrMapper.insertProductAttr(productAttr);
	}

	@Override
	@Transactional
	public boolean update(ProductAttr productAttr) {
		if (ProductAttrMapper.updateByPrimaryKey(productAttr) > 0) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public boolean delete(Integer id) {
		if (ProductAttrMapper.deleteByPrimaryKey(id) >0 && productAttrValueService.del(id)) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public boolean updateMain(ProductAttr productAttr) {
		// ����id����
		ProductAttrMapper.updateByPrimaryKey(productAttr);
		// ���¾�Ԫ��
		productAttrValueService.updateList(productAttr);
		// �ж��Ƿ�����Ԫ��
		for (ProductAttrValue productAttrValue : productAttr.getProductAttrValues()) {
			if (productAttrValue.getId() == null) {
				productAttrValueService.insertList(productAttr);
				break;
			}
		}
		return false;
	}

}
