package com.haryanaindustries.api.service;

import java.util.List;

import com.haryanaindustries.api.model.Menu;

public interface MenuService {

	void save(Menu menu);

	Menu findById(int id);

	List<Menu> findAllMenus(Boolean isActive);
}
