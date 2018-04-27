package cn.gzx6.dao;

import cn.gzx6.entity.DinnerTable;

import java.util.List;

public interface IDinnerTableDao {

    void add(DinnerTable dt);

    void delete(int id);

    void updata(DinnerTable dt);

    List<DinnerTable> query();

    DinnerTable findById(int id);

    List<DinnerTable> query(String keyword);

    void quitTable(int id);

}
