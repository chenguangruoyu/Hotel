package cn.gzx6.dao;

import cn.gzx6.entity.Food;
import cn.gzx6.utils.PageBean;

import java.util.List;

public interface IFoodDao {

    /**
     * 分页且按条件查询所有的菜品
     */
    void getAll(PageBean<Food> pb);

    /**
     * 按条件统计菜品的总记录数
     */
    int getTotalCount(PageBean<Food> pb);

    /**
     * 根据id查询
     */
    Food findById(int id);

    void add(Food food);

    void delete(int id);

    void update(Food food);

    List<Food> query();

    List<Food> query(String keyword);

    List<Food> findByType(int type);
}
