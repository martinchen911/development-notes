package com.design.behavior.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体中介者
 * @Author chen
 * @Date 2019/11/20
 */
class ConcreteMediator extends Mediator {
    private List<Colleague> colleagues=new ArrayList<Colleague>();
    @Override
    public void register(Colleague colleague) {
        if(!colleagues.contains(colleague)) {
            colleagues.add(colleague);
            colleague.setMedium(this);
        }
    }
    @Override
    public void relay(Colleague cl) {
        colleagues.stream()
                .filter(ob -> !ob.equals(cl))
                .forEach(ob -> ob.receive());
    }
}
