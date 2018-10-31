/**
 * 
 */
package com.cjl.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjl.product.mapper.ProductMapper;
import com.cjl.product.model.ProductModel;
import com.cjl.product.service.ProductService;
import com.cjl.product.vo.ProductVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 @author： fu    @time：2018年10月28日 下午8:27:41 
 @说明： 一份耕耘，一份收获
**/
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public void insertProduct(ProductModel productModel) {
		productMapper.insert(productModel);
		
	}
	
	@Override
	public ProductModel selectProductById(Long id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteProductById(Long id) {
		productMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void updateProductById(ProductModel productModel) {
		productMapper.updateByPrimaryKey(productModel);
	}

	@Override
	public List<ProductModel> queryByKeyWords(String keyWords) {
		Example example = new Example(ProductModel.class);
		Criteria criteria = example.createCriteria();
		criteria.andLike("productName","%"+keyWords+"%");
		return productMapper.selectByExample(example);
	}

	@Override
	public java.util.List<ProductModel> findAll() {
		return productMapper.selectAll();
	}

	@Override
	public PageInfo<ProductModel> findPageInfo(ProductVO productVO) {
		
		PageHelper.startPage(productVO.getPageNum(), productVO.getPageSize());
		List<ProductModel> productList = productMapper.findProductList(productVO);
		return new PageInfo<>(productList);
	}



	
}
