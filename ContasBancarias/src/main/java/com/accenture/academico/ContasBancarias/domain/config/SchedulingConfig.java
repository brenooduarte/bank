package com.accenture.academico.ContasBancarias.domain.config;

import com.accenture.academico.ContasBancarias.domain.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Autowired
    ContaBancariaService contaBancariaService;

    @Scheduled(cron = "0 0 0 1 * ?") // Executa no primeiro dia de cada mês à meia-noite
    public void aplicarJurosPeriodicamente() {
        contaBancariaService.aplicarJuros();
    }
}

