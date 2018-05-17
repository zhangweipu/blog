package com.wp.weipu.mapper;

import com.wp.weipu.common.base.BaseMapper;
import com.wp.weipu.entity.Demo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

public interface DemoMapper extends BaseMapper<Demo>{
    /**
     *  this function is using to test SQL which is a
     *  function in mybatis 3.0+
     * @param sqlStr
     * @return
     */
    String selectName(@Param(value = "sqlStr") String sqlStr);
}