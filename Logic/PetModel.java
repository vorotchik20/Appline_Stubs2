package ru.appline.Logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetModel implements Serializable {
    private static final PetModel instance = new PetModel();
    private final Map<Integer, Pet> model;

    public PetModel(){
        model = new HashMap<>();
    }

    public static PetModel getInstance(){
        return instance;
    }

    public void add(int id, Pet pet){
        model.put(id, pet);
    }

    public Pet getPetById(int id){
        return model.get(id);
    }

    public Map<Integer, Pet> getAll(){
        return model;
    }

    public Pet deletePetById(int id){
        return model.remove(id);
    }

    public Pet updatePetById(Integer id, Pet pet) {
        return model.put(id, pet);
    }
}
