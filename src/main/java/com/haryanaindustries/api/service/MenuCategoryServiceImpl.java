package com.haryanaindustries.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haryanaindustries.api.dao.MenuCategoryDao;
import com.haryanaindustries.api.model.MenuCategory;

@Service("menuCategoryService")
@Transactional
public class MenuCategoryServiceImpl implements MenuCategoryService {

	@Autowired
	MenuCategoryDao dao;

	@Override
	public void save(MenuCategory menuCategory) {
		dao.save(menuCategory);
	}

	@Override
	public MenuCategory findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<MenuCategory> findAllMenuCategories(Boolean isActive, Integer menuId) {
		return dao.findAllMenuCategories(isActive, menuId);
	}
}
