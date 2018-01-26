package com.dredh.mapper;

import com.dredh.model.KeyGenerator;
import org.apache.ibatis.annotations.Param;

public interface KeyGeneratorMapper {

    KeyGenerator select(int id);

    void insert(KeyGenerator generator);

    void increase(@Param("id") int id,@Param("number") int number);
}
