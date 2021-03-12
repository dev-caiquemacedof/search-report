package com.targetdatasmart.reportbatch.service;


import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

public interface RegistroConsultaService {

    String generateReport(Integer costumerId, String startDate, String endDate) throws IOException;
    String generateReportCPF(Integer costumerId, String startDate, String endDate) throws IOException;
}
