package ru.appline.Controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.Logic.Pet;
import ru.appline.Logic.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    public static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger counter = new AtomicInteger(1);

    @PostMapping(value = "/add", consumes = "application/json")
    public String createPet(@RequestBody Pet pet){
        petModel.add(counter.getAndIncrement(), pet);
        return "Питомец по имени " + pet.getName() + " добавлен";
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll(){
        return petModel.getAll();
    }

    @GetMapping(value = "/get", consumes = "application/json")
    public Pet getPetById(@RequestBody Map<String, Integer> body){
        return petModel.getPetById(body.get("id"));
    }

    @DeleteMapping(value = "/delete", consumes = "application/json")
    public String deleteById(@RequestBody Map<String, Integer> body){
        if (petModel.getPetById(body.get("id")) != null){
            petModel.deletePetById(body.get("id"));
            return "Питомец удалён";
        }else {
            return "Питомец не найден";
        }
    }

    @PutMapping(value = "/update", consumes = "application/json")
    public String updateById(@RequestBody Map<String, Object> body){
        if (petModel.getPetById((Integer) body.get("id")) != null){
            Pet pet = new Pet((String) body.get("name"), (String) body.get("type"), (Integer) body.get("age"));
            petModel.updatePetById((Integer) body.get("id"), pet);
            return "Питомец обновлён";
        }else {
            return "Питомец не найден";
        }
    }
}
