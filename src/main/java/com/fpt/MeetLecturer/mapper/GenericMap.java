package com.fpt.MeetLecturer.mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenericMap {

    private final ModelMapper modelMapper = new ModelMapper();


    public <S, T> T ToDTO(S entity, Class<T> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <S, T> List<T> ToDTOList(List<S> entityList, Class<T> dtoClass) {
        List<T> dtoList = new ArrayList<>();
        for (S entity : entityList) {
            dtoList.add(ToDTO(entity, dtoClass));
        }
        return dtoList;
    }

    public <S, T> S ToEntity(T dto, Class<S> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <S, T> List<S> convertDTOListToEntityList(List<T> dtoList, Class<S> entityClass) {
        List<S> entityList = new ArrayList<>();
        for (T dto : dtoList) {
            entityList.add(ToEntity(dto, entityClass));
        }
        return entityList;
    }
}
