package org.elastic.search.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.elastic.search.controller.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("#{elasticSearchProperties.getUri()}")
public class ElasticSearchController {

	@Autowired
	private ElasticSearchService elasticSearchService;

	@RequestMapping(value = "**", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.PATCH, RequestMethod.DELETE }, consumes = {
					MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> callElasticSearchApi(HttpServletRequest request) throws IOException {
		return elasticSearchService.callElasticSearchApi(request);
	}

}
