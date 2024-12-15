package com.test.demo.serviceTest;

import com.test.demo.entities.Device;
import com.test.demo.repositories.DeviceRepository;
import com.test.demo.services.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDeviceSuccess() {

        Device device = new Device(1L, "Device1", "Brand1", null);
        when(deviceRepository.save(device)).thenReturn(device);
        Device result = deviceService.addDevice(device);
        assertNotNull(result);
        assertEquals("Device1", result.getName());
        verify(deviceRepository, times(1)).save(device);
    }

    @Test
    void testAddDeviceNullDevice() {

        Device device = null;
        assertThrows(IllegalArgumentException.class, () -> deviceService.addDevice(device));
        verify(deviceRepository, never()).save(any());
    }

    @Test
    void testAddDeviceNullName() {
        Device device = new Device(1L, null, "Brand1", null);
        assertThrows(IllegalArgumentException.class, () -> deviceService.addDevice(device));
        verify(deviceRepository, never()).save(any());
    }

    @Test
    void testGetDeviceById() {

        Device device = new Device(1L, "Device1", "Brand1", null);
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
        Device result = deviceService.getDeviceById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Device1", result.getName());
        verify(deviceRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateDevice() {

        Device existingDevice = new Device(1L, "Device1", "Brand1", null);
        Device updatedDevice = new Device(1L, "Updated Device", "Updated Brand", null);
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(existingDevice));
        when(deviceRepository.save(any(Device.class))).thenReturn(updatedDevice);
        Device result = deviceService.updateDevice(1L, updatedDevice);
        assertNotNull(result);
        assertEquals("Updated Device", result.getName());
        assertEquals("Updated Brand", result.getBrand());
        verify(deviceRepository, times(1)).findById(1L);
        verify(deviceRepository, times(1)).save(existingDevice);
    }

    @Test
    void testDeleteDeviceById_Success() {
        Device existingDevice = new Device(1L, "Device1", "Brand1", null);
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(existingDevice));
        doNothing().when(deviceRepository).delete(existingDevice);
        deviceService.deleteDeviceById(1L);
        verify(deviceRepository, times(1)).findById(1L);
        verify(deviceRepository, times(1)).delete(existingDevice);
    }

}