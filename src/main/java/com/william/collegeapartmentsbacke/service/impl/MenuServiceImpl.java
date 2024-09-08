package com.william.collegeapartmentsbacke.service.impl;
import com.william.collegeapartmentsbacke.mapper.MenuMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Menu;
import com.william.collegeapartmentsbacke.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.selectList(null);
    }

    @Override
    public Menu getMenuById(Long id)  {
        return menuMapper.selectById(id);
    }
}
