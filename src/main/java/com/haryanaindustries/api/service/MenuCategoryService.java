package com.haryanaindustries.api.service;

import java.util.List;

import com.haryanaindustries.api.model.MenuCategory;

public interface MenuCategoryService {

	void save(MenuCategory menuCategory);

	MenuCategory findById(int id);

	List<MenuCategory> findAllMenuCategories(Boolean isActive,Integer menuId);
}
