package org.elastic.search.util;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	protected JsonUtils() {
		throw new UnsupportedOperationException("No object for util class");
	}

	public static <T> T fromJson(String src, Class<T> valueType) throws IOException {
		if (Objects.nonNull(src)) {
			return OBJECT_MAPPER.readValue(src, valueType);
		} else {
			return null;
		}
	}

}
