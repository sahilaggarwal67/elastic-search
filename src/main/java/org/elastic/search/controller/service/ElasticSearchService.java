package org.elastic.search.controller.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface ElasticSearchService {

	public ResponseEntity<?> callElasticSearchApi(HttpServletRequest servletRequest) throws IOException;

}
