package com.test.demo.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.test.demo.services.DeviceService;
import com.test.demo.entities.Device;

/**
 * REST controller for handling device-related operations.
 * Provides endpoints for managing devices such as creating a new device.
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Device API", description = "Endpoints for managing devices")
public class DeviceController {

   


    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/devices")
    public ResponseEntity<?> listAllDevices() {
        try {
            return ResponseEntity.ok(deviceService.listAllDevices());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/device")
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        try {
            Device savedDevice = deviceService.addDevice(device);
            return ResponseEntity.ok(savedDevice);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    
    @GetMapping("/device/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable Long id) {
        try {
            Device device = deviceService.getDeviceById(id);
            return ResponseEntity.ok(device);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    
    @PutMapping("/device/{id}")
    public ResponseEntity<?> updateDevice(@PathVariable Long id, @RequestBody Device updatedDevice) {
        try {
            Device device = deviceService.updateDevice(id, updatedDevice);
            return ResponseEntity.ok(device);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    
    @DeleteMapping("/device/{id}")
    public ResponseEntity<?> deleteDeviceById(@PathVariable Long id) {
        try {
            deviceService.deleteDeviceById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @GetMapping("/device/brand/{brand}")
    public ResponseEntity<?> findDeviceByBrand(@PathVariable String brand) {
        try {
            return ResponseEntity.ok(deviceService.findDeviceByBrand(brand));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    
    
    
    

}
