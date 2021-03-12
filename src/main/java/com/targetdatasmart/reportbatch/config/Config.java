package com.targetdatasmart.reportbatch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
public class Config {

    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration
            = ClientConfiguration.builder()
                .connectedTo("3.89.250.141:9200")
                .withBasicAuth("td_app_ts","t-#n?1HEdn[(f^:")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}