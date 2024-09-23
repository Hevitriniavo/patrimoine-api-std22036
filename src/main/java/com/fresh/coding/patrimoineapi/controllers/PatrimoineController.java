package com.fresh.coding.patrimoineapi.controllers;

import com.fresh.coding.patrimoineapi.model.Patrimoine;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@NoArgsConstructor
@RequestMapping("/patrimoines")
public class PatrimoineController {

    @PutMapping("/{id}")
    public List<Patrimoine> createOrUpdatePatrimoine(
            @PathVariable(required = false, name = "id") String name,
            @RequestBody Patrimoine patrimoine
    ) {
        return List.of();
    }

    @GetMapping
    public Patrimoine findById(
            @RequestBody Patrimoine patrimoine
    ) {
        return null;
    }
}
