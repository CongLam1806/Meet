package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.LocationDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(path="/api/v1/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @GetMapping("")
    public ResponseEntity<ResponseDTO> getAllLocation(){
        return ResponseEntity.ok().body(locationService.getAllLocation());
    }
    @GetMapping("/public")
    public ResponseEntity<ResponseDTO> getAllPublicLocation(){
        return ResponseEntity.ok().body(locationService.getAllPublicLocation());
    }
    @GetMapping("/personal")
    public ResponseEntity<ResponseDTO> getAllPersonalLocation(@RequestParam(value = "Lecturer-id") String id){
        return ResponseEntity.ok().body(locationService.getAllPersonalLocation(id));
    }
    @GetMapping("/my-location")
    public ResponseEntity<ResponseDTO> getLecturerLocation(@RequestParam(value = "Lecturer-id") String id){
        return ResponseEntity.ok().body(locationService.getAllPrivateLocation(id));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLocation(@RequestParam(value = "id")int id){
        return ResponseEntity.ok().body(locationService.deleteLocation(id));
    }
    @PostMapping("/new-location")
    public ResponseEntity<ResponseDTO> createLocation(@Valid @RequestBody LocationDTO locationDTO){
       return ResponseEntity.ok().body(locationService.createLocation(locationDTO));
    }

    @PutMapping("/mod/{id}")
    public  ResponseEntity<ResponseDTO> editLocation(@Valid @RequestBody LocationDTO locationDTO, @PathVariable("id") int id){
        //return ResponseEntity.ok().body(locationService.updateLocation(locationDTO));
        return ResponseEntity.ok(locationService.updateLocation(locationDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> UpdateLocation(@Valid @RequestBody LocationDTO locationDTO, @PathVariable("id") int id){
        return locationService.EditLocation(locationDTO, id);
    }

}
