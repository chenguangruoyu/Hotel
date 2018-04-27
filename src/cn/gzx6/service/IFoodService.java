package cn.gzx6.service;

import cn.gzx6.entity.Food;
import cn.gzx6.utils.PageBean;

import java.util.List;

public interface IFoodService {

    /**
     * 主键查询
     */
    Food findById(int id);

    /**
     * 分页查询
     */
    void getAll(PageBean<Food> pb);

    void delete(int id);

    void update(Food food);

    List<Food> query();

    List<Food> query(String keyword);

    List<Food> findByType(int type);

    void add(Food food);
}
