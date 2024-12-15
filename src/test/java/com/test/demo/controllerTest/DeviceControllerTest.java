package com.test.demo.controllerTest;


import com.test.demo.controllers.DeviceController;
import com.test.demo.entities.Device;
import com.test.demo.services.DeviceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @MockBean
    private DeviceService deviceService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDeviceById() throws Exception {
        Device mockDevice = new Device();
        mockDevice.setId(1L);
        mockDevice.setName("test device ok");
        
        Mockito.when(deviceService.getDeviceById(1L)).thenReturn(mockDevice);
        
        mockMvc.perform(get("/api/device/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("test device ok"));
    }


}
