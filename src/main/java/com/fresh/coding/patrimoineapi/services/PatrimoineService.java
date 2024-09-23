package com.fresh.coding.patrimoineapi.services;

import com.fresh.coding.patrimoineapi.model.Patrimoine;



public interface PatrimoineService {
    Patrimoine findByName(String name);

    Patrimoine save(String name, Patrimoine patrimoine);
}
