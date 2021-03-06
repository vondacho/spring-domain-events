/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.events.jackson;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.events.EventSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Oliver Gierke
 */
@RequiredArgsConstructor
public class JacksonEventSerializer implements EventSerializer {

	private final ObjectFactory<ObjectMapper> mapper;

	/* 
	 * (non-Javadoc)
	 * @see de.olivergierke.events.EventSerializer#serialize(java.lang.Object)
	 */
	@Override
	public Object serialize(Object event) {

		try {
			return mapper.getObject().writeValueAsString(event);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see de.olivergierke.events.EventSerializer#deserialize(java.lang.Object, java.lang.Class)
	 */
	@Override
	public Object deserialize(Object serialized, Class<?> type) {

		try {
			return mapper.getObject().readerFor(type).readValue(serialized.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
