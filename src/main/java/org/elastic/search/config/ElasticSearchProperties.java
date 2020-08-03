package org.elastic.search.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "org.elastic.search")
public class ElasticSearchProperties {

	private String uri = "/elastic-search";

	private List<String> hosts;

	public ElasticSearchProperties() {
		hosts = Arrays.asList("http://localhost:9200");
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<String> getHosts() {
		return hosts;
	}

	public void setHosts(List<String> hosts) {
		this.hosts = hosts;
	}

}
