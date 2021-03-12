package com.targetdatasmart.reportbatch.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.targetdatasmart.reportbatch.dto.RegistroConsultaDTO;
import com.targetdatasmart.reportbatch.repository.RegistroConsultaRepository;
import com.targetdatasmart.reportbatch.service.RegistroConsultaService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

@Service
public class RegistroConsultaServiceImpl implements RegistroConsultaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistroConsultaServiceImpl.class);

    private final RegistroConsultaRepository registroConsultaRepository;

    @Autowired
    RegistroConsultaServiceImpl(RegistroConsultaRepository registroConsultaRepository) {
        this.registroConsultaRepository = registroConsultaRepository;
    }

    @Override
    public String generateReport(Integer costumerId, String startDate, String endDate) throws IOException {
        File file = getFile(costumerId);

        writeResponseIntoFile(file,
                registroConsultaRepository.findAllWithScrollByCostumerIdAndCreatedAtBetween(
                        costumerId, startDate, endDate));

        return file.getAbsolutePath();
    }

    @Override
    public String generateReportCPF(Integer costumerId, String startDate, String endDate) throws IOException {

        File file = getFile(costumerId);

        writeResponseIntoFile(file,
                registroConsultaRepository.findAllWithScrollByCostumerIdAndCreatedAtBetweenAndKeyCPF(
                        costumerId, startDate, endDate)
        );

        return file.getAbsolutePath();
    }

    private void writeResponseIntoFile(File file, SearchResponse response) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, StandardCharsets.ISO_8859_1));

        // ADICIONANDO UM HEADER AO ARQUIVO.
        bufferedWriter.write(String.join(RegistroConsultaDTO.SEPARATOR, RegistroConsultaDTO.HEADER) + "\n");

        int lengthHits;
        do {
            for (SearchHit hit : response.getHits()) {
                RegistroConsultaDTO registroConsulta = new ObjectMapper().convertValue(hit.getSourceAsMap(), RegistroConsultaDTO.class);

                registroConsulta.setId(hit.getId());
                bufferedWriter.write(registroConsulta.toString() + "\n");
            }
            response = registroConsultaRepository.findAll(response.getScrollId());
            lengthHits = response.getHits().getHits().length;

            LOGGER.info("LENGTH NEXT HITS {}", lengthHits);
        } while( lengthHits > 0 );// Enquanto houver o proximo resultado da consulta, eu permaneço no loop

        bufferedWriter.close();
    }

    private File getFile(Integer costumerId) {
        String fileName = new Timestamp(System.currentTimeMillis()).getTime() + "__" + costumerId + "__relatorio_search_registro.csv";
        return new File("src/main/resources/output/" + fileName);
    }
}
