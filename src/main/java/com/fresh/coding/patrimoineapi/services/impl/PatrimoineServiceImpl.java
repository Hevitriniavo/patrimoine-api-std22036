package com.fresh.coding.patrimoineapi.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fresh.coding.patrimoineapi.model.Patrimoine;
import com.fresh.coding.patrimoineapi.services.PatrimoineService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PatrimoineServiceImpl implements PatrimoineService {

    private final String jsonFilePath;
    private final List<Patrimoine> patrimoines = new ArrayList<>();
    private final ObjectMapper objectMapper;

    public PatrimoineServiceImpl(
            @Value("${json.path:src/main/resources/patrimoines.json}")
            String jsonFilePath,
            ObjectMapper objectMapper
    ) {
        this.jsonFilePath = jsonFilePath;
        this.objectMapper = objectMapper;
        loadPatrimoines();
    }

    @SneakyThrows
    private void loadPatrimoines() {
        var jsonFile = new File(jsonFilePath);
        if (jsonFile.exists()) {
            Patrimoine[] existingPatrimoines = objectMapper.readValue(jsonFile, Patrimoine[].class);
            patrimoines.addAll(Arrays.asList(existingPatrimoines));
        }
    }

    @Override
    public Patrimoine findByName(String name) {
        return patrimoines.stream()
                .filter(p -> p.getPossesseur().equals(name))
                .findFirst()
                .orElse(null);
    }

    @SneakyThrows
    @Override
    public Patrimoine save(String name, Patrimoine patrimoine) {
        var existingPatrimoine = findByName(name);
        if (existingPatrimoine != null) {
            existingPatrimoine.setDerniereModification(LocalDateTime.now());
            existingPatrimoine.setPossesseur(patrimoine.getPossesseur());
        } else {
            patrimoine.setPossesseur(name);
            patrimoine.setDerniereModification(LocalDateTime.now());
            patrimoines.add(patrimoine);
        }
        objectMapper.writeValue(new File(jsonFilePath), patrimoines);
        return findByName(patrimoine.getPossesseur());
    }
}
