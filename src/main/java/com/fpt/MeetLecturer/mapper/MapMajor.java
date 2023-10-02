package com.fpt.MeetLecturer.mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapMajor {

    private final ModelMapper modelMapper = new ModelMapper();


    public <S, T> T convertEntityToDTO(S entity, Class<T> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <S, T> List<T> convertEntityListToDTOList(List<S> entityList, Class<T> dtoClass) {
        List<T> dtoList = new ArrayList<>();
        for (S entity : entityList) {
            dtoList.add(convertEntityToDTO(entity, dtoClass));
        }
        return dtoList;
    }

    public <S, T> S convertDTOToEntity(T dto, Class<S> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <S, T> List<S> convertDTOListToEntityList(List<T> dtoList, Class<S> entityClass) {
        List<S> entityList = new ArrayList<>();
        for (T dto : dtoList) {
            entityList.add(convertDTOToEntity(dto, entityClass));
        }
        return entityList;
    }
}
