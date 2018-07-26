package com.sakura.dao;

import com.sakura.entity.Sex;
import org.springframework.stereotype.Repository;

@Repository
public interface SexDao {

    void updateSex(Sex sex);

    Sex findByKey(Integer key);
}
