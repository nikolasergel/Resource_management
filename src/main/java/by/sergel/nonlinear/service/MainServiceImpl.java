package by.sergel.nonlinear.service;

import by.sergel.nonlinear.dto.Calculations;
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

    public Entity getData(){
        return entityRepository.getEntity();
    }

    public void setData(Map<String, String > form) {
        Entity entity = this.entityRepository.getEntity();

        entity.setD(Integer.parseInt(form.get("D")));
        entity.setH(Double.parseDouble(form.get("h")) / 100);
        entity.setS(Integer.parseInt(form.get("S")));
        List<Double> price = new ArrayList<>();
        List<Integer> qauntity = new ArrayList<>();
        for(int i = 1; i <= 3; i++){
            price.add(Double.parseDouble(form.get("price" + i)));
            qauntity.add(Integer.parseInt(form.get("quantity" + i)));
        }
        entity.setPrice(price);
        entity.setQuantity(qauntity);
    }

    public Calculations getCalculations(){
        Entity entity = entityRepository.getEntity();
        List<Double> price = entity.getPrice();
        List<Integer> quantity = entity.getQuantity();
                Calculations calc = new Calculations();
        for(int i = 0; i < price.size(); i++){
            int EOQ = getEOQ(price.get(i), entity);
            calc.getEOQ().add(EOQ);
            calc.getPrice().add(price.get(i));

            int realQ;
            if(i + 1 < quantity.size() && EOQ > quantity.get(i + 1) - 1){
                realQ = quantity.get(i + 1) - 1;
            }
            else if(EOQ < quantity.get(i)){
                realQ = quantity.get(i);
            }
            else {
                realQ = EOQ;
            }
            calc.getRealQ().add(realQ);

            double orderCount = (double)(entity.getD()) / realQ;
            calc.getOrderCount().add(orderCount);

            int TH = (int)(realQ / 2 * price.get(i) * entity.getH());
            calc.getTH().add(TH);

            int TS = (int)(orderCount * entity.getS());
            calc.getTS().add(TS);

            calc.getT().add(TS + TH);
            calc.getFinalT().add((int)(TS + TH + entity.getD() * price.get(i)));
        }
        return calc;
    }

    private int getEOQ(Double C, Entity entity){
        return (int)Math.sqrt((2*entity.getD()*entity.getS())/(C*entity.getH()));
    }
}
