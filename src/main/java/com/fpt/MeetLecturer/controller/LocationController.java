package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.LocationDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @GetMapping("")
    public ResponseDTO getAllLocation(){
        return locationService.getAllLocation();
    }
    @GetMapping("/public")
    public ResponseDTO getAllPublicLocation(){
        return locationService.getAllLocation();
    }
    @GetMapping("/personal")
    public ResponseDTO getAllPersonalLocation(@RequestParam(value = "Lecturer-id") int id){
        return locationService.getAllPersonalLocation(id);
    }
    @DeleteMapping("/delete")
    public ResponseDTO deleteLocation(@RequestParam(value = "id")int id){
        return locationService.deleteLocation(id);
    }
    @PostMapping("/new-location")
    @PutMapping("/update/{id}")
    public ResponseDTO updateLocation(@Valid @RequestBody LocationDTO locationDTO, @PathVariable("id") int id){
        /* locationDTO.setId(id); */
       return locationService.updateLocation1(locationDTO);
    }

}
