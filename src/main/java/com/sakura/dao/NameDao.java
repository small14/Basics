package com.sakura.dao;

import com.sakura.entity.Name;
import org.springframework.stereotype.Repository;

@Repository
public interface NameDao {
    void updateName(Name name);
}
