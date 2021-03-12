package com.targetdatasmart.reportbatch.repository.impl;

import com.targetdatasmart.reportbatch.repository.RegistroConsultaRepositoryCustom;
import com.targetdatasmart.reportbatch.service.impl.RegistroConsultaServiceImpl;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class RegistroConsultaRepositoryCustomImpl implements RegistroConsultaRepositoryCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistroConsultaServiceImpl.class);

    private final RestHighLevelClient client;

    @Autowired
    RegistroConsultaRepositoryCustomImpl(RestHighLevelClient client) {
        this.client = client;
    }

    @Override
    public SearchResponse findAllWithScrollByCostumerIdAndCreatedAtBetweenAndKeyCPF(Integer costumerId, String startDate, String endDate) {
        SearchRequest searchRequest = new SearchRequest();
        SearchResponse search = null;
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder qbMust = QueryBuilders.boolQuery();
        BoolQueryBuilder qbShould = QueryBuilders.boolQuery();

        // SHOULD
        qbShould.should(QueryBuilders.matchQuery("chave", "CPF").operator(Operator.OR));
        qbShould.should(QueryBuilders.termQuery("metodo.keyword", "CPF"));
        // MUST
        qbMust.must(QueryBuilders.termQuery("cliente_id", costumerId));
        qbMust.must(QueryBuilders.rangeQuery("created_at").gte(startDate).lte(endDate));
        qbMust.must(qbShould);

        searchSourceBuilder.query(qbMust);
        searchSourceBuilder.size(800);
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));

        try {
            search = client.search(searchRequest, RequestOptions.DEFAULT);
            LOGGER.info("SEARCH RESPONSE HITS LENGTH {}", search.getHits().getHits().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return search;
    }

    @Override
    public SearchResponse findAll(String scrollId) {
        SearchResponse scroll = null;
        try {
            LOGGER.info("SCROLL ID {}", scrollId);
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
            searchScrollRequest.scroll(TimeValue.timeValueMinutes(1L));

            scroll = client.scroll(searchScrollRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scroll;
    }

    @Override
    public SearchResponse findAllWithScrollByCostumerIdAndCreatedAtBetween(Integer costumerId, String startDate, String endDate) {
        SearchRequest searchRequest = new SearchRequest();
        SearchResponse search = null;
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder qbMust = QueryBuilders.boolQuery();
        BoolQueryBuilder qbShould = QueryBuilders.boolQuery();

        // MUST
        qbMust.must(QueryBuilders.termQuery("cliente_id", costumerId));
        qbMust.must(QueryBuilders.rangeQuery("created_at").gte(startDate).lte(endDate));
        qbMust.must(qbShould);

        searchSourceBuilder.query(qbMust);
        searchSourceBuilder.size(800);
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));

        try {
            search = client.search(searchRequest, RequestOptions.DEFAULT);
            LOGGER.info("SEARCH RESPONSE HITS LENGTH {}", search.getHits().getHits().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return search;
    }
}
