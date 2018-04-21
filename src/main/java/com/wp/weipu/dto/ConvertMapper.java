package com.wp.weipu.dto;

import com.wp.weipu.entity.Demo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Map;

/**
 * @author zwp
 */
@Mapper(componentModel = "spring")
public interface ConvertMapper {
    ConvertMapper INSTANCE = Mappers.getMapper(ConvertMapper.class);

    Demo DtoToDemo(DemoDto demoDto);

}
