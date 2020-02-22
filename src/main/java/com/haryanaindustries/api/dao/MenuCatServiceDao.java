package com.haryanaindustries.api.dao;

import java.util.List;

import com.haryanaindustries.api.model.MenuCatService;

public interface MenuCatServiceDao {

	void save(MenuCatService menuCatService);

	MenuCatService findById(int id);

	List<MenuCatService> findAllMenuCatServices(Boolean isActive, Integer menuCategoryId);
}
