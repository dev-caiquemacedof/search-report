package com.targetdatasmart.reportbatch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.targetdatasmart.reportbatch.dto.RegistroConsultaDTO;
import com.targetdatasmart.reportbatch.service.RegistroConsultaService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;


@RestController
@RequestMapping("registro-consulta")
public class RegistroConsultaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistroConsultaController.class);

	private final RegistroConsultaService registroConsultaService;

	@Autowired
	RegistroConsultaController(RegistroConsultaService registroConsultaService) {
		this.registroConsultaService = registroConsultaService;
	}

	@GetMapping("report")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String generate(@RequestParam(name = "costumer_id") Integer costumerId,
								 @RequestParam(name = "start_date") String startDate,
								 @RequestParam(name = "end_date") String endDate) throws IOException {
		return registroConsultaService.generateReport(costumerId, startDate, endDate);
	}

	@GetMapping("report-cpf")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String generateCPF(@RequestParam(name = "costumer_id") Integer costumerId,
											 @RequestParam(name = "start_date") String startDate,
											 @RequestParam(name = "end_date") String endDate) throws IOException {

		return registroConsultaService.generateReportCPF(costumerId, startDate, endDate);
	}
}