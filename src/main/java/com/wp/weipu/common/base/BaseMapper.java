package com.wp.weipu.common.base;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.example.DeleteByExampleMapper;

/**
 * @author zwp
 */

public interface BaseMapper<T> extends Mapper<T>,IdsMapper<T>,SelectAllMapper<T>,DeleteByExampleMapper<T>{
}
