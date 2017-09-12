/*-
 * #%L
 * id
 * %%
 * Copyright (C) 2017 Kiril Arabadzhiyski
 * %%
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
 * #L%
 */
package com.a9ski.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class IdDeltaTest {

	@Test
	public void testDelta() {
		final IdDelta d = new IdDelta(Arrays.asList(1L, 2L, 3L, 4L), Arrays.asList(3L, 4L, 5L, 6L));
		assertEquals(Arrays.asList(1L, 2L), d.getRemovedIds());
		assertEquals(Arrays.asList(3L, 4L), d.getIntersectionIds());
		assertEquals(Arrays.asList(5L, 6L), d.getAddedIds());
		assertTrue(d.hasDelta());
	}

	@Test
	public void testNoDelta() {
		final IdDelta d = new IdDelta(Arrays.asList(1L, 2L, 3L, 4L), Arrays.asList(3L, 4L, 1L, 2L));
		assertTrue(d.getRemovedIds().isEmpty());
		assertEquals(Arrays.asList(1L, 2L, 3L, 4L), d.getIntersectionIds());
		assertTrue(d.getAddedIds().isEmpty());
		assertFalse(d.hasDelta());
	}

	@Test
	public void testNullOld() {
		final IdDelta d = new IdDelta(null, Arrays.asList(3L, 4L, 1L, 2L));
		assertTrue(d.getRemovedIds().isEmpty());
		assertTrue(d.getIntersectionIds().isEmpty());
		assertEquals(Arrays.asList(1L, 2L, 3L, 4L), d.getAddedIds());
		assertTrue(d.hasDelta());
	}

	@Test
	public void testNullNew() {
		final IdDelta d = new IdDelta(Arrays.asList(3L, 4L, 1L, 2L), null);
		assertTrue(d.getAddedIds().isEmpty());
		assertTrue(d.getIntersectionIds().isEmpty());
		assertEquals(Arrays.asList(1L, 2L, 3L, 4L), d.getRemovedIds());
		assertTrue(d.hasDelta());
	}

	@Test
	public void testNull() {
		final IdDelta d = new IdDelta(null, null);
		assertTrue(d.getAddedIds().isEmpty());
		assertTrue(d.getIntersectionIds().isEmpty());
		assertTrue(d.getRemovedIds().isEmpty());
		assertFalse(d.hasDelta());
	}
}
