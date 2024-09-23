package com.fresh.coding.patrimoineapi.controllers;

import com.fresh.coding.patrimoineapi.model.Patrimoine;
import com.fresh.coding.patrimoineapi.services.PatrimoineService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patrimoines")
public class PatrimoineController {

    private final PatrimoineService patrimoineService;

    public PatrimoineController(PatrimoineService patrimoineService) {
        this.patrimoineService = patrimoineService;
    }

    @PutMapping("/{name}")
    public Patrimoine updatePatrimoine(
            @PathVariable String name,
            @RequestBody Patrimoine patrimoine
    ) {
        return patrimoineService.save(name, patrimoine);
    }

    @GetMapping("/{name}")
    public Patrimoine findPatrimoineByName(@PathVariable String name)  {
        return patrimoineService.findByName(name);
    }
}
