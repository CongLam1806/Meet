package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapSubject {
    private static final ModelMapper modelMapper = new ModelMapper();
//    public SubjectDTO convertSubjectToSubjectDTO(Subject subject){
//        return modelMapper.map(subject, SubjectDTO.class);
//    }
//
//
//    public List<SubjectDTO> convertListToSubjectDTO(List<Subject> subjects){
//        List<SubjectDTO> list = new ArrayList<>();
//        if(subjects == null){
//            return null;
//        }
//        subjects.forEach(subject -> list.add(convertSubjectToSubjectDTO(subject)));
//        return list;
//    }

//    public <S, T> T convertEntityToDTO(S entity, Class<T> dtoClass) {
//        return modelMapper.map(entity, dtoClass);
//    }
//
//    public <S, T> List<T> convertEntityListToDTOList(List<S> entityList, Class<T> dtoClass) {
//        List<T> dtoList = new ArrayList<>();
//        for (S entity : entityList) {
//            dtoList.add(convertEntityToDTO(entity, dtoClass));
//        }
//        return dtoList;
//    }
//
//    public <S, T> S convertDTOToEntity(T dto, Class<S> entityClass) {
//        return modelMapper.map(dto, entityClass);
//    }
//
//    public <S, T> List<S> convertDTOListToEntityList(List<T> dtoList, Class<S> entityClass) {
//        List<S> entityList = new ArrayList<>();
//        for (T dto : dtoList) {
//            entityList.add(convertDTOToEntity(dto, entityClass));
//        }
//        return entityList;
//    }

    public SubjectDTO convertEntitytoDTO(Subject subject){
        return modelMapper.map(subject, SubjectDTO.class);
    }

    public List<SubjectDTO> convertListToSubjectDto(List<Subject> subjects){
        List<SubjectDTO> list = new ArrayList<>();
        subjects.forEach(subject -> list.add(convertEntitytoDTO(subject)));
        return list;
    }

    public Subject convertBookingDTOtoBooking(SubjectDTO subjectDTO){
        return  modelMapper.map(subjectDTO, Subject.class);
    }

    public List<Subject> convertListToLecturer(List<SubjectDTO> subjectDTOS){
        List<Subject> list = new ArrayList<>();
        subjectDTOS.forEach(subjectDTO -> list.add(convertBookingDTOtoBooking(subjectDTO)));
        return list;
    }

    public static Subject convertSubjectDTOToSubject(SubjectDTO subjectDTO){
        return modelMapper.map(subjectDTO, Subject.class);
    }
}
