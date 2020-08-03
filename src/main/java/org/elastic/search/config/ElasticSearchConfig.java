package org.elastic.search.config;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

	@Bean
	public ElasticSearchProperties elasticSearchProperties() {
		return new ElasticSearchProperties();
	}

	@Bean
	public RestClient highLevelClient(ElasticSearchProperties elasticSearchProperties) {
		List<HttpHost> hosts = elasticSearchProperties.getHosts().stream().map(HttpHost::create)
				.collect(Collectors.toList());
		RestClient restClient = RestClient.builder(hosts.toArray(new HttpHost[hosts.size()])).build();
		return restClient;
	}
}
