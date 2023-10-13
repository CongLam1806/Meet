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

import java.util.Optional;
@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MapLocation mapLocation;

    private final ModelMapper modelMapper = new ModelMapper();
     public ResponseDTO getAllLocation(){
         return new ResponseDTO(HttpStatus.OK, "Get all Locations:",  mapLocation.tolocationDTOList(locationRepository.findAll()));
     }
    public ResponseDTO getAllPublicLocation(){
        return new ResponseDTO(HttpStatus.OK,"Get all public Locations:",mapLocation.tolocationDTOList(locationRepository.findPublicLocation()));
    }
    public ResponseDTO getAllPersonalLocation(int id){
         return new ResponseDTO(HttpStatus.OK, "Personal locations:", mapLocation.tolocationDTOList(locationRepository.findPersonalLocation(id)));
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

//    public void updateLocation(LocationDTO newLocation){
//        Location location;
//        if (newLocation.getId() == 0) {
//            location = new Location();
//        } else {
//            location = locationRepository.findById(newLocation.getId()).orElseThrow();
//
//        }
//        modelMapper.map(newLocation, location);
//
//        locationRepository.save(location);
//    }


}
