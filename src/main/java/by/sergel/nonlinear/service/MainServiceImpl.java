package by.sergel.nonlinear.service;

import by.sergel.nonlinear.entity.Entity;
import by.sergel.nonlinear.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private EntityRepository entityRepository;

    public String getData(){
        return entityRepository.getEntity().toString();
    }

    public void setData(Map<String, String> form) {
        Entity entity = this.entityRepository.getEntity();

        entity.setD(Integer.parseInt(form.get("D")));
        entity.setH(Integer.parseInt(form.get("h")));
        entity.setS(Integer.parseInt(form.get("S")));
        List<Integer> price = new ArrayList<>();
        List<Integer> qauntity = new ArrayList<>();
        for(int i = 1; i <= 3; i++){
            price.add(Integer.parseInt(form.get("price" + i)));
            qauntity.add(Integer.parseInt(form.get("count" + i)));
        }
        entity.setPrice(price);
        entity.setQuantity(qauntity);
    }
}
