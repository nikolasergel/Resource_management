package by.sergel.nonlinear.service;

import by.sergel.nonlinear.dto.Calculation;
import by.sergel.nonlinear.dto.Result;
import by.sergel.nonlinear.entity.Entity;
import by.sergel.nonlinear.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        for(int i = 1; i <= (form.keySet().size() - 3) / 2; i++){
            price.add(Double.parseDouble(form.get("price" + i)));
            qauntity.add(Integer.parseInt(form.get("quantity" + i)));
        }
        entity.setPrice(price);
        entity.setQuantity(qauntity);
    }

    public List<Calculation> getCalculations(){
        Entity entity = entityRepository.getEntity();
        List<Double> price = entity.getPrice();
        List<Integer> quantity = entity.getQuantity();
        List<Calculation> calc = new ArrayList<>();
        for(int i = 0; i < price.size(); i++){
            Calculation buff = new Calculation();
            int EOQ = getEOQ(price.get(i), entity);
            buff.setEOQ(EOQ);
            buff.setPrice(price.get(i));

            int realQ;
            buff.setMin(quantity.get(i));
            buff.setMax(i + 1 < quantity.size() ? quantity.get(i + 1) + 1 : 1000000);
            if(i + 1 < quantity.size() && EOQ > quantity.get(i + 1) - 1){
                realQ = quantity.get(i + 1) - 1;
            }
            else if(EOQ < quantity.get(i)){
                realQ = quantity.get(i);
            }
            else {
                realQ = EOQ;
            }
            buff.setRealQ(realQ);

            double orderCount = (double)(entity.getD()) / realQ;
            buff.setOrderCount(orderCount);

            int TH = getTH(realQ, price.get(i));
            buff.setTH(TH);

            int TS = getTS(orderCount);
            buff.setTS(TS);

            buff.setT(TS + TH);
            buff.setFinalT((int)(TS + TH + entity.getD() * price.get(i)));
            
            calc.add(buff);
        }
        return calc;
    }

    private int getTH(int realQ, double price){
        return (int)Math.round(price * entityRepository.getEntity().getH() * realQ / 2);
    }

    private int getTS(double orderCount){
        return (int)Math.round(orderCount * entityRepository.getEntity().getS());
    }

    private int getFinalT(int T, double price, int orderCount, int realQ){
        return (int)Math.round(price * realQ * orderCount + T);
    }

    private int getEOQ(Double C, Entity entity){
        return (int)Math.sqrt((2*entity.getD()*entity.getS())/(C*entity.getH()));
    }

    public List<Result> getResultsList(List<Calculation> calc){
        List<Result> list = new ArrayList<>();
        if(calc != null && calc.size() > 0){
            Collections.sort(calc);
            int d = entityRepository.getEntity().getD();
            for(int i = 0; d > 0; i++){
                Result result = new Result();
                Calculation buff = calc.get(i);
                if(d > buff.getRealQ()) {
                    result.setGoodsNumber(buff.getRealQ());
                }
                else if(d >= buff.getMin() && d <= buff.getMax()){
                    result.setGoodsNumber(d);
                }
                else{
                    continue;
                }
                result.setPrice(buff.getPrice());
                result.setRealQ(buff.getRealQ());
                result.setOrderCount(d / result.getGoodsNumber());
                d = d % result.getGoodsNumber();
                list.add(result);
            }
            Collections.sort(calc, new Comparator<Calculation>()
            {
                public int compare(Calculation c1, Calculation c2)
                {
                    return c1.compareTo(c2) * -1;
                }
            });
        }
        return list;
    }

    public Integer getResultNumber(List<Result> results){
        int ret = 0;
        for(Result r : results){
            int T = getTH(r.getGoodsNumber(), r.getPrice()) + getTS(r.getOrderCount());
            ret += getFinalT(T, r.getPrice(), r.getOrderCount(), r.getGoodsNumber());
        }
        return ret;
    }
}