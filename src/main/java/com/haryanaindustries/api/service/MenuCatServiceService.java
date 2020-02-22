package com.haryanaindustries.api.service;

import java.util.List;

import com.haryanaindustries.api.model.MenuCatService;

public interface MenuCatServiceService {

	void save(MenuCatService menuCatService);

	MenuCatService findById(int id);

	List<MenuCatService> findAllMenuCatServices(Boolean isActive, Integer menuCategoryId);
}
