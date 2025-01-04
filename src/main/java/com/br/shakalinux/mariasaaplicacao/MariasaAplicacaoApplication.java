package com.br.shakalinux.mariasaaplicacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MariasaAplicacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MariasaAplicacaoApplication.class, args);
    }

}
