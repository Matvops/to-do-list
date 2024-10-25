package com.cadenassi.to_do_list.enums;

import com.cadenassi.to_do_list.exceptions.ResourceNotFoundException;

public enum PriorityEnum {

    BAIXA(1),
    MODERADA(2),
    ALTA(3);

    private int i;

    private PriorityEnum(int i) {
        this.i = i;
    }

    public int getPriorityCode(){
        return this.i;
    }

    public PriorityEnum getPriorityEnum(int i){
        PriorityEnum[] priorities = PriorityEnum.values();

        for(PriorityEnum priority: priorities)
            if (priority.getPriorityCode() == i)
                return priority;

        throw new ResourceNotFoundException("PRIORITY NOT FOUND!");
    }

}
