package com.targetdatasmart.reportbatch.repository;

import com.targetdatasmart.reportbatch.model.RegistroConsulta;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface RegistroConsultaRepository extends ElasticsearchRepository<RegistroConsulta, String>, RegistroConsultaRepositoryCustom {

    @Query("{\"match_all\": {}}")
    List<RegistroConsulta> matchAll();
}