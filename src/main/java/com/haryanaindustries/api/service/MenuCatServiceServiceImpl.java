package com.haryanaindustries.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haryanaindustries.api.dao.MenuCatServiceDao;
import com.haryanaindustries.api.model.MenuCatService;

@Service("menuCatServiceService")
@Transactional
public class MenuCatServiceServiceImpl implements MenuCatServiceService {

	@Autowired
	MenuCatServiceDao dao;

	@Override
	public void save(MenuCatService menuCatService) {
		dao.save(menuCatService);
	}

	@Override
	public MenuCatService findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<MenuCatService> findAllMenuCatServices(Boolean isActive, Integer menuCategoryId) {
		return dao.findAllMenuCatServices(isActive, menuCategoryId);
	}
}
