package com.dn.springboot03.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("启动了MyListener=======");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
