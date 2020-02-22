package com.haryanaindustries.api.dao;

import java.util.List;

import com.haryanaindustries.api.model.Menu;

public interface MenuDao {

	void save(Menu menu);

	Menu findById(int id);

	List<Menu> findAllMenus(Boolean isActive);
}
