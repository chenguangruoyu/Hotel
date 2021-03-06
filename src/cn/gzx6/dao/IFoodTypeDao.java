package cn.gzx6.dao;

import cn.gzx6.entity.FoodType;

import java.util.List;

/**
 * 菜系模块，dao接口
 */
public interface IFoodTypeDao {
    /**
     * 添加
     */
    void save(FoodType foodType);

    /**
     * 更新
     */
    void update(FoodType foodType);

    /**
     * 删除
     */
    void delete(int id);

    /**
     * 查询全部
     */
    List<FoodType> getAll();

    /**
     * 根据菜系名称查询
     */
    List<FoodType> getAll(String typeName);

    /**
     * 根据主键查询
     */
    FoodType findById(int id);

    List<FoodType> query(String keyword);

    List<FoodType> query();

}
