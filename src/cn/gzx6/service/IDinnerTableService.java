package cn.gzx6.service;

import cn.gzx6.entity.DinnerTable;

import java.util.List;

public interface IDinnerTableService {

    void add(DinnerTable dt);

    void delete(int id);

    void updata(DinnerTable dt);

    List<DinnerTable> query();

    DinnerTable findById(int id);

    List<DinnerTable> query(String keyword);

    DinnerTable changeState(int id);

    void quitTable(int id);
}
