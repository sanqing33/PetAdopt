package com.petadopt.controller;

import com.alibaba.fastjson.JSON;
import com.petadopt.entity.Adopt;
import com.petadopt.entity.Pet;
import com.petadopt.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PetController {

    private final PetMapper petMapper;

    @Autowired
    public PetController(PetMapper petMapper) {
        this.petMapper = petMapper;
    }

    @GetMapping("/api/pet")
    public String getPet() {
        List<Pet> pets = petMapper.select();
        return JSON.toJSONString(pets);
    }

    @PostMapping("/api/adopt")
    public String postPet(@RequestBody Adopt adopt) {
        petMapper.insertAdopt(adopt);
        return "添加成功";
    }

    @GetMapping("/api/admin/pet")
    public String getAdminPet(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Pet> pets = petMapper.selectWithPagination(offset, pageSize);
        int total = petMapper.countPets();
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("data", pets);
        return JSON.toJSONString(result);
    }

    @GetMapping("/api/admin/pet/query")
    public String getAdminQueryPet(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String species,
                                   @RequestParam(required = false) String available,
                                   @RequestParam("page") int page,
                                   @RequestParam("pageSize") int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Pet> pets = petMapper.queryWithPagination(name, species, available, offset, pageSize);
        int total = petMapper.countPetsByQuery(name, species, available);
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("data", pets);
        return JSON.toJSONString(result);
    }

    @PostMapping("/api/admin/pet/insert")
    public String postInsertPet(@RequestBody Pet pet) {
        petMapper.insertPet(pet);
        return "添加成功";
    }

    @PostMapping("/api/admin/pet/edit")
    public String postEditPet(@RequestBody Pet pet) {
        petMapper.update(pet);
        return "编辑成功";
    }

    @PostMapping("/api/admin/pet/delete")
    public String postDeletePet(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        petMapper.delete(name);
        return "删除成功";
    }

    @PostMapping("/api/admin/pet/deletes")
    public String postDeletePets(@RequestBody List<String> names) {
        petMapper.deletePets(names);
        return "删除成功";
    }
}


