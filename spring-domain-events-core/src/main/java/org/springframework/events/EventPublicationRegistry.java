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
package org.springframework.events;

import java.util.Collection;

import org.springframework.context.ApplicationListener;

/**
 * A registry to capture event publications to {@link ApplicationListener}s. Allows to register those publications, mark
 * them as completed and lookup incomplete publications.
 * 
 * @author Oliver Gierke
 */
public interface EventPublicationRegistry {

	/**
	 * Stores {@link EventPublication}s for the given event and {@link ApplicationListener}s.
	 * 
	 * @param event must not be {@literal null}.
	 * @param listeners must not be {@literal null}.
	 */
	void store(Object event, Collection<ApplicationListener<?>> listeners);

	/**
	 * Marks the publication for the given event and {@link PublicationTargetIdentifier} as completed.
	 * 
	 * @param event must not be {@literal null}.
	 * @param listener must not be {@literal null}.
	 */
	void markCompleted(Object event, PublicationTargetIdentifier listener);

	/**
	 * Returns all {@link EventPublication}s that have not been completed yet.
	 * 
	 * @return will never be {@literal null}.
	 */
	Iterable<EventPublication> findIncompletePublications();
}
