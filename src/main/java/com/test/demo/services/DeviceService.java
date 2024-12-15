package com.test.demo.services;


import com.test.demo.entities.Device;
import com.test.demo.repositories.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceService {


    private final DeviceRepository deviceRepository;


    public Device addDevice(Device device) {
        try {
            if (device == null || device.getName() == null || device.getBrand() == null) {
                throw new IllegalArgumentException("Device, name, and brand cannot be null");
            }
            return deviceRepository.save(device);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while adding the device", e);
        }
    }


    public Device getDeviceById(Long id) {
        try {
            return deviceRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Device not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving the device", e);
        }
    }

    public List<Device> listAllDevices() {
        try {
            return deviceRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while listing all devices", e);
        }
    }


    public Device updateDevice(Long id, Device updatedDevice) {
        try {
            if (id == null || updatedDevice == null) {
                throw new IllegalArgumentException("ID and updated device cannot be null");
            }
            Device existingDevice = deviceRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Device not found with id: " + id));
            if (updatedDevice.getName() != null) {
                existingDevice.setName(updatedDevice.getName());
            }
            if (updatedDevice.getBrand() != null) {
                existingDevice.setBrand(updatedDevice.getBrand());
            }

            return deviceRepository.save(existingDevice);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the device", e);
        }
    }


    public List<Device> findDeviceByBrand(String brand) {
        try {
            if (brand == null || brand.isEmpty()) {
                throw new IllegalArgumentException("Brand cannot be null or empty");
            }
            return deviceRepository.findByBrand(brand);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving devices by brand", e);
        }
    }


    public void deleteDeviceById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be null");
            }
            Device device = deviceRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Device not found with id: " + id));
            deviceRepository.delete(device);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the device", e);
        }


    }
}
