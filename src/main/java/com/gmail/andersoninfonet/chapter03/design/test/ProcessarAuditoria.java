package com.gmail.andersoninfonet.chapter03.design.test;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessarAuditoria {

    private Logger logger = Logger.getLogger("ProcessarAuditoria");

    ProcessarAuditoria() {
        System.out.println("construiu");
    }
    
    void logarAuditoria(Object handler) {
        //boolean ehAuditoria = handler.getClass().isAnnotationPresent(Auditoria.class);

        for(Method method : handler.getClass().getDeclaredMethods()) {
            if(method.isAnnotationPresent(Auditoria.class)) {
                logger.log(Level.INFO, () -> "To logando");
                logger.logp(Level.INFO, method.getClass().getName(), method.getName(), "To logando aqui");
                var auditoria =  method.getAnnotation(Auditoria.class);
                System.out.println("Logando " + auditoria.login() + " no sistema " + auditoria.sistema() + " acao " + method.getName());
            }
        }
    }
}
