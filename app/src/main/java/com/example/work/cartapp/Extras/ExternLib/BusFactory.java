package com.example.work.cartapp.Extras.ExternLib;

import com.squareup.otto.Bus;

/**
 * Created by work on 10/4/2017.
 */

public class BusFactory {

    public static final Bus bus = new Bus();

    public static Bus getBus(){
        return bus;
    }
}
