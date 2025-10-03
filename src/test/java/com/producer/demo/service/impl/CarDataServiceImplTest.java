package com.producer.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.demo.dto.CarDto;
import com.producer.demo.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarDataServiceImplTest {

    @InjectMocks
    private CarDataServiceImpl carDataService;

    @Mock
    private StringRedisTemplate template;

    @Test
    @DisplayName("get car data")
    void whenGetCarDataReturnSuccessfully() {
        // Implement test logic here
        ListOperations<String, String> listOperations = Mockito.mock(ListOperations.class);
        when(template.opsForList()).thenReturn(listOperations);
        when(listOperations.range(anyString(),anyLong(),anyLong()))
                .thenReturn(TestUtil.REDIS_CAR_LIST);

        CarDto carDto = carDataService.getCarData("cars:list",35L);
        assertNotNull(carDto);
        assertEquals(35L, carDto.getId());
    }


    @Test
    @DisplayName("get car data not found")
    void whenGetCarDataReturnNotFound() {
        // Implement test logic here
        ListOperations<String, String> listOperations = Mockito.mock(ListOperations.class);
        when(template.opsForList()).thenReturn(listOperations);
        when(listOperations.range(anyString(),anyLong(),anyLong()))
                .thenReturn(TestUtil.REDIS_CAR_LIST);

        CarDto carDto = carDataService.getCarData("cars:list",99L);
        assertNull(carDto);
    }


    @Test
    @DisplayName("save car data en Redis")
    void whenSaveCarDataReturnSuccessfully() throws Exception {
        CarDto carDto = CarDto.builder().id(20L).brand("Honda").year(2000).build();
        ListOperations<String, String> listOperations = Mockito.mock(ListOperations.class);
        when(template.opsForList()).thenReturn(listOperations);

        carDataService.saveCarData("cars:list", carDto);

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(carDto);
        Mockito.verify(listOperations).rightPush("cars:list", expectedJson);
    }

}
