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

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import com.a9ski.id.Identifiable;

public class IdentifiableDeltaTest {

	private static class Entity implements Identifiable {

		/**
		 *
		 */
		private static final long serialVersionUID = 4474276555744167122L;

		private long id;

		public Entity(final long id) {
			super();
			this.id = id;
		}

		@Override
		public long getId() {
			return id;
		}

		@Override
		public void setId(final long id) {
			this.id = id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (id ^ (id >>> 32));
			return result;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Entity))
				return false;
			final Entity other = (Entity) obj;
			if (id != other.id)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return String.valueOf(id);
		}
	}

	private Entity e(final int id) {
		return new Entity(id);
	}

	@Test
	public void testDelta() {
		final IdentifiableDelta<Entity, Entity> d = new IdentifiableDelta<>(Arrays.asList(e(1), e(2)), Arrays.asList(e(2), e(3)));
		assertEquals(Arrays.asList(e(1)), d.getRemoved());
		assertEquals(Arrays.asList(Pair.of(e(2), e(2))), d.getIntersection());
		assertEquals(Arrays.asList(e(3)), d.getAdded());
		assertTrue(d.hasDelta());
		assertEquals(Arrays.asList(e(1), e(2)), d.getOldObjects());
		assertEquals(Arrays.asList(e(2), e(3)), d.getNewObjects());
	}

	@Test
	public void testNoDelta() {
		final IdentifiableDelta<Entity, Entity> d = new IdentifiableDelta<>(Arrays.asList(e(1), e(2)), Arrays.asList(e(2), e(1)));
		assertTrue(d.getRemoved().isEmpty());
		assertEquals(Arrays.asList(Pair.of(e(1), e(1)), Pair.of(e(2), e(2))), d.getIntersection());
		assertTrue(d.getAdded().isEmpty());
		assertFalse(d.hasDelta());
	}

	@Test
	public void testNull() {
		final IdentifiableDelta<Entity, Entity> d = new IdentifiableDelta<>(null, null);
		assertTrue(d.getRemoved().isEmpty());
		assertTrue(d.getIntersection().isEmpty());
		assertTrue(d.getAdded().isEmpty());
		assertFalse(d.hasDelta());
	}

}
