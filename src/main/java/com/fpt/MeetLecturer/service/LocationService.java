package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.LocationDTO;
import com.fpt.MeetLecturer.entity.Location;
import com.fpt.MeetLecturer.mapper.MapLocation;
import com.fpt.MeetLecturer.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MapLocation mapLocation;
     public List<LocationDTO> getAllLocation(){
         return mapLocation.tolocationDTOList(locationRepository.findAll());
     }
    public List<LocationDTO> getAllPublicLocation(){
        return mapLocation.tolocationDTOList(locationRepository.findByStatus());
    }
    public List<LocationDTO> getAllPersonalLocation(int id){
         return mapLocation.tolocationDTOList(locationRepository.findPersonalLocation(id));
    }
    public boolean deleteLocation(int id){
        Optional<Location> location = locationRepository.findById(id);
        if(location.isEmpty()) return false;
        else {
            locationRepository.delete(location.get());
            return true;
        }
    }
    public LocationDTO updateLocation(LocationDTO locationDTO){
         Optional<Location> location = locationRepository.findById(locationDTO.getId());
         if(location.isPresent()){
             Location ExistLocation = location.get();
             ExistLocation.setName(locationDTO.getName());
             ExistLocation.setAddress(locationDTO.getAddress());
             ExistLocation.setStatus(locationDTO.isStatus());
             locationRepository.save(ExistLocation);
             return mapLocation.toLocationDTO(ExistLocation);
         }else{
             Location location1 = mapLocation.toLocationEntity(locationDTO);
             return mapLocation.toLocationDTO(location1);
         }
    }

}
