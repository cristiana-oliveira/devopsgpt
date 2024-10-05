package com.atu.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertNotNull;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DevOpsGptControllerTest {


    @InjectMocks
    private DevOpsGptController controller;

    @Test
    public void home(){
      assertNotNull(controller.home());
    }
}
