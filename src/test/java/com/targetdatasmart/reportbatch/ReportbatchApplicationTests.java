package com.targetdatasmart.reportbatch;

import com.targetdatasmart.reportbatch.model.RegistroConsulta;
import com.targetdatasmart.reportbatch.repository.RegistroConsultaRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;


@SpringBootTest
class ReportbatchApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportbatchApplicationTests.class);

    @Autowired
    RegistroConsultaRepository registroConsultaRepository;

    @Test
    void matchAll() {
        List<RegistroConsulta> registroConsultas = registroConsultaRepository.matchAll();

        for (RegistroConsulta registroConsulta : registroConsultas) {
            LOGGER.info("ID {}", registroConsulta.getCostumerId());
        }
    }

    @Test
    void findAll() {
        Page<RegistroConsulta> all = registroConsultaRepository.findAll(PageRequest.of(0, 100));

        for (RegistroConsulta registroConsulta : all) {
            LOGGER.info("ID {} METHOD {}", registroConsulta.getCostumerId(), registroConsulta.getMethod());
        }
    }

}
