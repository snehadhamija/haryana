package com.haryanaindustries.api.dao;

import java.util.List;

import com.haryanaindustries.api.model.MenuCategory;

public interface MenuCategoryDao {

	void save(MenuCategory menuCategory);

	MenuCategory findById(int id);

	List<MenuCategory> findAllMenuCategories(Boolean isActive,Integer menuId);
}
