package com.targetdatasmart.reportbatch.repository;

import org.elasticsearch.action.search.SearchResponse;

public interface RegistroConsultaRepositoryCustom {

    SearchResponse findAllWithScrollByCostumerIdAndCreatedAtBetweenAndKeyCPF(Integer costumerId, String startDate, String endDate);
    SearchResponse findAll(String scrollId);
    SearchResponse findAllWithScrollByCostumerIdAndCreatedAtBetween(Integer costumerId, String startDate, String endDate);
}
