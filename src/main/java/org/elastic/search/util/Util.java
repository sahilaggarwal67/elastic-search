package org.elastic.search.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.elastic.search.config.ElasticSearchProperties;
import org.elastic.search.constant.Constant;
import org.elasticsearch.client.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Util {

	@Autowired
	private ElasticSearchProperties elasticSearchProperties;

	public Request convertToRequestObject(HttpServletRequest servletRequest) throws IOException {
		Request request = new Request(servletRequest.getMethod(), getRequestUri(servletRequest.getRequestURI()));
		Map<String, String> queryParameters = getQueryParameters(servletRequest.getQueryString());
		request.addParameters(queryParameters);
		request.setEntity(new InputStreamEntity(servletRequest.getInputStream(), ContentType.APPLICATION_JSON));
		return request;
	}

	public Map<String, String> getQueryParameters(String queryString) {
		Map<String, String> queryParameters = new HashMap<>();
		if (StringUtils.isNotBlank(queryString)) {
			queryParameters = Arrays.stream(queryString.split(Constant.AMPERSAND)).map(String::valueOf)
					.collect(Collectors.toMap(param -> param.split(Constant.EQUAL_OPERATOR)[Constant.ZERO_INDEX],
							param -> decode(param.split(Constant.EQUAL_OPERATOR)[Constant.ONE_INDEX])));
		}
		return queryParameters;
	}

	public String decode(String value) {
		try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException exception) {
			throw new RuntimeException(exception);
		}
	}

	private String getRequestUri(String requestUri) {
		Validate.notNull(requestUri, "Request uri can't be null");
		return requestUri.replace(elasticSearchProperties.getUri(), Constant.BLANK_STRING);
	}
}
