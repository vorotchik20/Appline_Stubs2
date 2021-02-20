package ru.appline.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.appline.Logic.CompasModel;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CompasController {
    CompasModel compasModel = CompasModel.getInstance();
    @PostMapping(value = "/create", consumes = "application/json")
    public String create(@RequestBody Map<String, String> body){
        if (body.size() != 8){
            return "Должно быть 8 сторон";
        }else{
            compasModel.create(body);
            return "Создано";
        }
    }

    @GetMapping(value = "/getCompas", consumes = "application/json")
    public Map<String, String> getAll(){
        return compasModel.getModel();
    }

    @GetMapping(value = "/getSide", consumes = "application/json")
    public Map<String, String> getSideByDegree(@RequestBody Map<String, Integer> body){
        String side = compasModel.getSideByDegree(body.get("Degree"));
        Map map = new HashMap<String, String>();
        map.put("Side", side);
        return map;
    }
}
