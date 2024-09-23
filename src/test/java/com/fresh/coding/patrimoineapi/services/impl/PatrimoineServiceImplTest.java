package com.fresh.coding.patrimoineapi.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fresh.coding.patrimoineapi.model.Patrimoine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatrimoineServiceImplTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PatrimoineServiceImpl patrimoineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patrimoineService = new PatrimoineServiceImpl("",objectMapper);
    }

    @Test
    void findByName_ExistingPatrimoine_ShouldReturnPatrimoine() {
        String name = "John Doe";
        Patrimoine patrimoine = new Patrimoine(name, LocalDateTime.now());
        patrimoineService.save(name);

        Patrimoine foundPatrimoine = patrimoineService.findByName(name);

        assertNotNull(foundPatrimoine);
        assertEquals(name, foundPatrimoine.getPossesseur());
    }

    @Test
    void findByName_NonExistentPatrimoine_ShouldReturnNull() {
        String name = "Non Existent";

        Patrimoine foundPatrimoine = patrimoineService.findByName(name);

        assertNull(foundPatrimoine);
    }

    @Test
    void save_NewPatrimoine_ShouldAddToList() throws Exception {
        String name = "John Doe";
        when(objectMapper.readValue(any(File.class), eq(Patrimoine[].class))).thenReturn(new Patrimoine[0]);
        doNothing().when(objectMapper).writeValue(any(File.class), anyList());

        Patrimoine savedPatrimoine = patrimoineService.save(name);

        assertNotNull(savedPatrimoine);
        assertEquals(name, savedPatrimoine.getPossesseur());
        verify(objectMapper, times(1)).writeValue(any(File.class), anyList());
    }

    @Test
    void save_ExistingPatrimoine_ShouldUpdateAndPersist() throws Exception {
        String name = "John Doe";
        Patrimoine existingPatrimoine = new Patrimoine(name, LocalDateTime.now().minusDays(1));
        when(objectMapper.readValue(any(File.class), eq(Patrimoine[].class))).thenReturn(new Patrimoine[]{existingPatrimoine});
        doNothing().when(objectMapper).writeValue(any(File.class), anyList());

        Patrimoine updatedPatrimoine = patrimoineService.save(name);

        assertNotNull(updatedPatrimoine);
        assertEquals(name, updatedPatrimoine.getPossesseur());
        assertNotEquals(existingPatrimoine.getDerniereModification(), updatedPatrimoine.getDerniereModification());
        verify(objectMapper, times(1)).writeValue(any(File.class), anyList());
    }
}
