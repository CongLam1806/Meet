package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.LocationDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.entity.Location;
import com.fpt.MeetLecturer.mapper.MapLocation;
import com.fpt.MeetLecturer.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MapLocation mapLocation;

    private final ModelMapper modelMapper = new ModelMapper();
     public ResponseDTO getAllLocation(){
         List<LocationDTO> output = mapLocation.tolocationDTOList(locationRepository.findAll());
         return new ResponseDTO(HttpStatus.OK, "Get all Locations:", output );
     }
    public ResponseDTO getAllPublicLocation(){
        List<LocationDTO> output = mapLocation.tolocationDTOList(locationRepository.findPublicLocation());
        return new ResponseDTO(HttpStatus.OK,"Get all public Locations:", output);
    }
    public ResponseDTO getAllPersonalLocation(String id){
         List<LocationDTO> output = mapLocation.tolocationDTOList(locationRepository.findPersonalLocation(id));
         return new ResponseDTO(HttpStatus.OK, "Personal locations:", output);
    }
    public ResponseDTO deleteLocation(int id){
        Optional<Location> location = locationRepository.findById(id);
        if(location.isEmpty()){
            return new ResponseDTO(HttpStatus.NOT_FOUND, "Location not found!", "");
        }
        else {
            locationRepository.delete(location.get());
            return new ResponseDTO(HttpStatus.OK, "Location deleted!", "");
        }
    }
    public ResponseDTO updateLocation1(LocationDTO locationDTO){
            Location location;
         if(locationDTO.getId() == 0){
             location = new Location();
         }else{
             location = locationRepository.findById(locationDTO.getId()).orElseThrow();
         }
         modelMapper.map(locationDTO, location);
         locationRepository.save(location);
         return new ResponseDTO(HttpStatus.OK, "Updated!", "");
    }
    public ResponseDTO createLocation(LocationDTO locationDTO){
         Location location = new Location();
         modelMapper.map(locationDTO, location);
         locationRepository.save(location);
         return  new ResponseDTO(HttpStatus.OK, "Created!","");
    }
}
