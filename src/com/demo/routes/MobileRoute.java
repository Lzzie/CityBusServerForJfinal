package com.demo.routes;

import com.demo.controller.ConsigneeController;
import com.demo.controller.OrderController;
import com.demo.controller.UserController;
import com.jfinal.config.Routes;

public class MobileRoute extends Routes {
    @Override
    public void config() {
        add("/user", UserController.class);
        add("/order", OrderController.class);
        add("/consignee", ConsigneeController.class);
    }
}
