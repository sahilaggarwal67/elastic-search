package org.elastic.search.controller.service;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.elastic.search.util.JsonUtils;
import org.elastic.search.util.Util;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

	@Autowired
	private RestClient restClient;

	@Autowired
	private Util util;

	@Override
	public ResponseEntity<?> callElasticSearchApi(HttpServletRequest servletRequest) throws IOException {
		Request request = util.convertToRequestObject(servletRequest);
		Response response = restClient.performRequest(request);
		return getResponseEntity(response);
	}

	private ResponseEntity<?> getResponseEntity(Response response) throws ParseException, IOException {
		final Header[] headers = response.getHeaders();
		final HttpHeaders headerMap = new HttpHeaders();
		for (final Header header : headers) {
			headerMap.add(header.getName(), header.getValue());
		}
		Object body = null;
		if (Objects.nonNull(response.getEntity())) {
			body = JsonUtils.fromJson(EntityUtils.toString(response.getEntity()), Map.class);
		}
		ResponseEntity<?> responseEntity = new ResponseEntity<>(body, headerMap,
				HttpStatus.resolve(response.getStatusLine().getStatusCode()));
		return responseEntity;
	}

}
