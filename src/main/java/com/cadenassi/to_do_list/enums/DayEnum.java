package com.cadenassi.to_do_list.enums;

import com.cadenassi.to_do_list.exceptions.ResourceNotFoundException;

/**
 * @author Matheus
 */

public enum DayEnum {

    DOMINGO(1),
    SEGUNDA(2),
    TERCA(3),
    QUARTA(4),
    QUINTA(5),
    SEXTA(6),
    SABADO(7);

    private int i;

    private DayEnum(int i){
        this.i = i;
    }

    public int getDayCode(){
        return this.i;
    }

    public static DayEnum getDay(int i) {
        DayEnum[] days = DayEnum.values();

        for(DayEnum day: days)
            if(day.getDayCode() == i)
                return day;

        throw new ResourceNotFoundException("DAY NOT FOUND!");
    }
}
