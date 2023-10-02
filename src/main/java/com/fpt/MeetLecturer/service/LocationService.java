package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.LocationDTO;
import com.fpt.MeetLecturer.entity.Location;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.exception.CustomException;
import com.fpt.MeetLecturer.mapper.MapLocation;
import com.fpt.MeetLecturer.repository.LocationRepository;
import org.modelmapper.ModelMapper;
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

    private ModelMapper modelMapper = new ModelMapper();
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
        if(location.isEmpty()){
            //throw new CustomException("Id: " + id + " not found!!!");
            return false;
        }
        else {
            locationRepository.delete(location.get());
            return true;
        }
    }
//    public void updateLocation(LocationDTO locationDTO){
//         Optional<Location> location = locationRepository.findById(locationDTO.getId());
//         if(location.isPresent()){
//             Location ExistLocation = location.get();
//             ExistLocation.setName(locationDTO.getName());
//             ExistLocation.setAddress(locationDTO.getAddress());
//             ExistLocation.setStatus(locationDTO.isStatus());
//             locationRepository.save(ExistLocation);
//
//         }else{
//             Location location1 = mapLocation.toLocationEntity(locationDTO);
//             locationRepository.save(location1);
//
//         }
//    }

    public void updateLocation(LocationDTO newLocation){
        Location location;
        if (newLocation.getId() == 0) {
            location = new Location();
        } else {
            location = locationRepository.findById(newLocation.getId()).orElseThrow();

        }
        modelMapper.map(newLocation, location);

        locationRepository.save(location);
    }


}
