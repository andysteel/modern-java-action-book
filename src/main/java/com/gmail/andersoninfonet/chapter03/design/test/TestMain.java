package com.gmail.andersoninfonet.chapter03.design.test;

public class TestMain {

    @Auditoria(login = "anderson.dias", sistema = "PNL")
    public void testaMetodo() {
        System.out.println("executou");
    }

    @Auditoria(login = "luciana.neri", sistema = "EDU")
    public void testaMetodo2() {
        System.out.println("Teste 13");
    }
    
    public static void main(String[] args) {
        var teste = new TestMain();
        // teste.testaMetodo();
        // teste.testaMetodo2();

        var log = new ProcessarAuditoria();
        log.logarAuditoria(teste);
    }
}
