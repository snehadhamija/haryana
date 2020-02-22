package com.haryanaindustries.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haryanaindustries.api.dao.MenuDao;
import com.haryanaindustries.api.model.Menu;

@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuDao dao;

	@Override
	public void save(Menu menu) {
		dao.save(menu);
	}

	@Override
	public Menu findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Menu> findAllMenus(Boolean isActive) {
		return dao.findAllMenus(isActive);
	}
}
