package by.sergel.nonlinear.repository;

import by.sergel.nonlinear.entity.Entity;
import org.springframework.stereotype.Repository;

@Repository
public class EntityRepository {
    private Entity entity;

    private EntityRepository(Entity entity){
        this.entity = entity;
    }

    public  Entity getEntity(){
        if(this.entity == null){
            this.entity = new Entity();
        }
        return entity;
    }
}
