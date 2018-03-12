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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.a9ski.id.Identifiable;
import com.a9ski.id.MutableIdentifiable;

public class IdentifiableUtilsTest {

	private static class Entity implements MutableIdentifiable {

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
	public void testGetIds() {
		assertEquals(ExtCollectionUtils.toSet(1L, 2L, 3L, null), IdentifiableUtils.getIds(e(1), e(2), e(3), e(1), null));
	}

	@Test
	public void testGetIdsList() {
		assertEquals(ExtCollectionUtils.toList(1L, 2L, 3L, 1L, null), IdentifiableUtils.getIdsList(e(1), e(2), e(3), e(1), null));
	}

	@Test
	public void testGetId() {
		assertEquals(Long.valueOf(1), IdentifiableUtils.getId(e(1)));
		assertNull(IdentifiableUtils.getId(null));
	}

	@Test
	public void testEqualsId() {
		assertTrue(IdentifiableUtils.equalsId(e(1), 1L));
		assertTrue(IdentifiableUtils.equalsId(null, null));
		assertFalse(IdentifiableUtils.equalsId(null, 0L));
		assertFalse(IdentifiableUtils.equalsId(e(1), null));
		assertFalse(IdentifiableUtils.equalsId(e(1), 2L));
	}

	@Test
	public void testNotEqualsId() {
		assertFalse(IdentifiableUtils.notEqualsId(e(1), 1L));
		assertFalse(IdentifiableUtils.notEqualsId(null, null));
		assertTrue(IdentifiableUtils.notEqualsId(null, 0L));
	}

	@Test
	public void testGetById() {
		assertEquals(e(1), IdentifiableUtils.getById(Arrays.asList(e(1), e(2)), e(1)));
		assertNull(IdentifiableUtils.getById(Arrays.asList(e(1), e(2)), (Identifiable) null));
	}

	@Test
	public void testContains() {
		assertTrue(IdentifiableUtils.contains(Arrays.asList(e(1), e(2)), e(1)));
		assertFalse(IdentifiableUtils.contains(Arrays.asList(e(1), e(2)), e(3)));
	}

	@Test
	public void testGetByIds() {
		assertEquals(Arrays.asList(e(1)), IdentifiableUtils.getByIds(Arrays.asList(e(1), e(2)), 1L));
		assertTrue(IdentifiableUtils.getByIds(Arrays.asList(e(1), e(2)), (Long) null).isEmpty());
		assertTrue(IdentifiableUtils.getByIds(null, 1L).isEmpty());
		assertTrue(IdentifiableUtils.getByIds(null, (Long) null).isEmpty());
	}

	@Test
	public void testRemoveByIds() {
		assertEquals(Arrays.asList(e(2), null), IdentifiableUtils.removeByIds(ExtCollectionUtils.toList(e(1), e(2), null), 1L));
		assertEquals(Arrays.asList(e(2)), IdentifiableUtils.removeByIds(ExtCollectionUtils.toList(e(1), e(2), null), Arrays.asList(null, 1L)));
		assertNull(IdentifiableUtils.removeByIds(null, 1L));
		assertNull(IdentifiableUtils.removeByIds(null, (long[]) null));
	}

	@Test
	public void testSameIds() {
		assertTrue(IdentifiableUtils.sameIds(e(1), e(1)));
		assertFalse(IdentifiableUtils.sameIds(e(1), e(2)));
		assertFalse(IdentifiableUtils.sameIds(null, e(2)));
		assertFalse(IdentifiableUtils.sameIds(e(1), null));
		assertTrue(IdentifiableUtils.sameIds((Identifiable) null, null));

		assertTrue(IdentifiableUtils.sameIds(Arrays.asList(e(1), e(2)), Arrays.asList(e(2), e(1))));
		assertFalse(IdentifiableUtils.sameIds(Arrays.asList(e(1), e(2)), null));
		assertFalse(IdentifiableUtils.sameIds(null, Arrays.asList(e(2), e(1))));

		assertTrue(IdentifiableUtils.sameIds((Collection<Identifiable>) null, null));
	}

	@Test
	public void testNotSameIds() {
		assertFalse(IdentifiableUtils.notSameIds(e(1), e(1)));
		assertTrue(IdentifiableUtils.notSameIds(e(1), e(2)));
		assertTrue(IdentifiableUtils.notSameIds(null, e(2)));
		assertTrue(IdentifiableUtils.notSameIds(e(1), null));
		assertFalse(IdentifiableUtils.notSameIds((Identifiable) null, null));

		assertFalse(IdentifiableUtils.notSameIds(Arrays.asList(e(1), e(2)), Arrays.asList(e(2), e(1))));
		assertTrue(IdentifiableUtils.notSameIds(Arrays.asList(e(1), e(2)), null));
		assertTrue(IdentifiableUtils.notSameIds(null, Arrays.asList(e(2), e(1))));

		assertFalse(IdentifiableUtils.notSameIds((Collection<Identifiable>) null, null));
	}

	@Test
	public void testMapByIds() {
		final Map<Long, List<Entity>> m = MapBuilder.createMap(1L, Arrays.asList(e(1))).put(2L, Arrays.asList(e(2), e(2))).build();
		assertEquals(m, IdentifiableUtils.mapByIds(Arrays.asList(e(2), e(1), e(2), null)));
		assertTrue(IdentifiableUtils.mapByIds(null).isEmpty());
	}

	@Test
	public void testCreateOrderedComparator() {
		final List<Entity> l = Arrays.asList(e(2), e(1), e(2), e(3), null);
		l.sort(IdentifiableUtils.createOrderedComparator(Arrays.asList(null, 1L, 2L)));
		assertEquals(Arrays.asList(e(3), null, e(1), e(2), e(2)), l);
	}
}
