package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.LocationDTO;
import com.fpt.MeetLecturer.repository.LocationRepository;
import com.fpt.MeetLecturer.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @GetMapping("")
    public List<LocationDTO> getAllLocation(){
        return locationService.getAllLocation();
    }
    @GetMapping("/public")
    public List<LocationDTO> getAllPublicLocation(){
        return locationService.getAllLocation();
    }
    @GetMapping("/personal")
    public List<LocationDTO> getAllPersonalLocation(@RequestParam(value = "id") int id){
        return locationService.getAllPersonalLocation(id);
    }
    @DeleteMapping("/delete")
    public boolean deleteLocation(@RequestParam(value = "id")int id){
        return locationService.deleteLocation(id);
    }
    @PostMapping("/new-location")
    @PutMapping("/update")
    public void updateLocation(@RequestBody LocationDTO locationDTO, @PathVariable("id") int id){
        locationDTO.setId(id);
        locationService.updateLocation(locationDTO);
    }

}
