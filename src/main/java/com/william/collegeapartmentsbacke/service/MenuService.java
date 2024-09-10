package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.mapper.MenuMapper;
import com.william.collegeapartmentsbacke.pojo.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {

    public List<Menu> getAllMenus();

    public Menu getMenuById(Long id);

}
