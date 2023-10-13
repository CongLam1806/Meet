package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.LocationDTO;
import com.fpt.MeetLecturer.entity.Location;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MapLocation {

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        TypeMap<Location, LocationDTO> toDTO = modelMapper.createTypeMap(Location.class, LocationDTO.class)
                .addMapping(src -> src.getLecturer().getId(), LocationDTO::setId);
    }

    public LocationDTO toLocationDTO(Location location){
        return modelMapper.map(location, LocationDTO.class);
    }
    public Location toLocationEntity(LocationDTO locationDTO){
        return modelMapper.map(locationDTO, Location.class);
    }
    public List<LocationDTO> tolocationDTOList(List<Location> locations){
        List<LocationDTO> list = new ArrayList<>();
        locations.forEach(location -> list.add(toLocationDTO(location)));
        return list;
    }
    public List<Location> tolocationList(List<LocationDTO> locationsDTO){
        List<Location> list = new ArrayList<>();
        locationsDTO.forEach(locationDTOS -> list.add(toLocationEntity(locationDTOS)));
        return list;
    }
}
