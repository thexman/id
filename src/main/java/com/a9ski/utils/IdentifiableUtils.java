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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.a9ski.id.Identifiable;

/**
 * Utility classes with helper methods for Identifiable
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class IdentifiableUtils {

	protected IdentifiableUtils() {
		super();
	}

	/**
	 * Returns a set with IDs of the entities
	 *
	 * @param entities
	 *            a list of identifiable objects
	 * @return set with IDs corresponding to the identifiable objects
	 */
	public static final Set<Long> getIds(final Collection<? extends Identifiable> entities) {
		return (Set<Long>) getIds(entities, new HashSet<Long>());
	}

	/**
	 * Returns a set with IDs of the entities
	 *
	 * @param entities
	 *            a list of identifiable objects
	 * @return set with IDs corresponding to the identifiable objects
	 */
	public static final Set<Long> getIds(final Identifiable... entities) {
		return getIds(ExtCollectionUtils.toList(entities));
	}

	/**
	 * Returns a list with IDs of the entities
	 *
	 * @param entities
	 *            a list of identifiable objects
	 * @return list with IDs corresponding to the identifiable objects
	 */
	public static final List<Long> getIdsList(final Collection<? extends Identifiable> entities) {
		return (List<Long>) getIds(entities, new ArrayList<Long>());
	}

	/**
	 * Returns a list with IDs of the entities
	 *
	 * @param entities
	 *            a list of identifiable objects
	 * @return list with IDs corresponding to the identifiable objects
	 */
	public static final List<Long> getIdsList(final Identifiable... entities) {
		return getIdsList(ExtCollectionUtils.toList(entities));
	}

	/**
	 * Returns a collection with IDs of the entities
	 *
	 * @param entities
	 *            a collection of identifiable objects
	 * @param ids
	 *            collection that stores the IDs. This collection will be returned
	 * @return collection with IDs corresponding to the identifiable objects
	 */
	private static Collection<Long> getIds(final Collection<? extends Identifiable> entities, final Collection<Long> ids) {
		if (CollectionUtils.isNotEmpty(entities)) {
			entities.stream().forEach(e -> ids.add(getId(e)));
		}
		return ids;
	}

	/**
	 * Gets the ID of the object or null if <tt>entity</tt> is null
	 *
	 * @param entity
	 *            the identifiable object
	 * @return the ID of the object or null if <tt>entity</tt> is null
	 */
	public static Long getId(final Identifiable entity) {
		return (entity != null ? entity.getId() : null);
	}

	/**
	 * Checks if the <tt>entity</tt> id is equals to <tt>id</tt>
	 *
	 * @param entity
	 *            the identifiable object. Can be null
	 * @param id
	 *            the id to be checked. Can be null
	 * @return true if the <tt>entity</tt> id is equals to <tt>id</tt> or both are null
	 */
	public static boolean equalsId(final Identifiable entity, final Long id) {
		final Long eid = getId(entity);
		if (eid == null && id == null) {
			return true;
		} else if (eid == null || id == null) {
			return false;
		} else {
			return eid.longValue() == id.longValue();
		}
	}

	/**
	 * Checks if the <tt>entity</tt> id is not equals to <tt>id</tt>
	 *
	 * @param entity
	 *            the identifiable object. Can be null
	 * @param id
	 *            the id to be checked. Can be null
	 * @return true if the <tt>entity</tt> id is not equals to <tt>id</tt> or one of them is null, but the other is not
	 */
	public static boolean notEqualsId(final Identifiable entity, final Long id) {
		return !equalsId(entity, id);
	}

	/**
	 * Gets the first object from the collection with given id or returns null if no such object exits
	 *
	 * @param collection
	 *            the collection of identifiable objects. Can be null
	 * @param id
	 *            the id to search for. Can be null
	 * @param <O>
	 *            the Identifiable type
	 * @return the first object from the collection with given id, or null if no such object exits
	 */
	public static <O extends Identifiable> O getById(final Collection<O> collection, final Long id) {
		return ExtCollectionUtils.get0(getByIds(collection, Arrays.asList(id)));
	}

	/**
	 * Gets the first object from the collection which have the same id as <tt>obj</tt> or returns null if no such object exits
	 *
	 * @param collection
	 *            the collection of identifiable objects. Can be null
	 * @param obj
	 *            the object whose id will be search for. Can be null
	 * @param <O>
	 *            Identifiable type
	 * @return the first object from the collection with the same id as <tt>obj</tt> or returns null if no such object exits
	 */
	public static <O extends Identifiable> O getById(final Collection<O> collection, final Identifiable obj) {
		if (obj != null) {
			return getById(collection, obj.getId());
		}
		return null;
	}

	/**
	 * Checks if collection of identifiable objects contains at least one object with given id
	 *
	 * @param collection
	 *            collection of identifiable objects. Can be null
	 * @param id
	 *            id to search for. Can be null
	 * @param <O>
	 *            Identifiable type
	 * @return true if collection of identifiable objects contains at least one object with given id
	 */
	public static <O extends Identifiable> boolean containsId(final Collection<O> collection, final Long id) {
		return getById(collection, id) != null;
	}

	/**
	 * Checks if collection of identifiable objects contains at least one object with the same id as the id of <tt>obj</tt>
	 *
	 * @param collection
	 *            collection of identifiable objects. Can be null
	 * @param obj
	 *            id to search for. Can be null
	 * @param <O>
	 *            Identifiable type
	 * @return true if collection of identifiable objects contains at least one object with given id
	 */
	public static <O extends Identifiable> boolean contains(final Collection<O> collection, final Identifiable obj) {
		return containsId(collection, getId(obj));
	}

	/**
	 * Returns all objects from the collection that have id equals to any of the provided IDs
	 *
	 * @param collection
	 *            collection of identifiable objects. Can be null.
	 * @param ids
	 *            IDs to search for. Can be null
	 * @param <O>
	 *            Identifiable type
	 * @return all objects from the collection that have id equals to any of the provided IDs
	 */
	public static <O extends Identifiable> List<O> getByIds(final Collection<O> collection, final Long... ids) {
		return getByIds(collection, Arrays.asList(ids));
	}

	/**
	 * Returns all objects from the collection that have id equals to any of the provided IDs
	 *
	 * @param collection
	 *            collection of identifiable objects. Can be null.
	 * @param ids
	 *            IDs to search for. Can be null
	 * @param <O>
	 *            Identifiable type
	 * @return all objects from the collection that have id equals to any of the provided IDs
	 */
	public static <O extends Identifiable> List<O> getByIds(final Collection<O> collection, final Collection<Long> ids) {
		final List<O> objects;
		if (CollectionUtils.isNotEmpty(ids) && CollectionUtils.isNotEmpty(collection)) {
			objects = collection.stream().filter(o -> ids.contains(getId(o))).collect(Collectors.toList());
		} else {
			objects = new ArrayList<>();
		}
		return objects;
	}

	/**
	 * Removes objects from collection with given IDs
	 *
	 * @param collection
	 *            collection of identifiable objects. Can be null.
	 * @param ids
	 *            IDs to be removed
	 * @param <C>
	 *            collection type
	 * @return the original collection, which has been altered
	 */
	public static <C extends Collection<? extends Identifiable>> C removeByIds(final C collection, final long... ids) {
		final List<Long> idsList = ExtCollectionUtils.toList(ArrayUtils.toObject(ids));
		return removeByIds(collection, idsList);
	}

	/**
	 * Removes objects from collection with given IDs
	 *
	 * @param collection
	 *            collection of identifiable objects. Can be null.
	 * @param ids
	 *            IDs to be removed
	 * @param <C>
	 *            collection type
	 * @return the original collection, which has been altered
	 */
	public static <C extends Collection<? extends Identifiable>> C removeByIds(final C collection, final Collection<Long> ids) {
		if (CollectionUtils.isNotEmpty(collection)) {
			final Set<Long> idsSet = ExtCollectionUtils.toSet(ids);
			collection.removeIf(e -> idsSet.contains(getId(e)));
		}
		return collection;
	}

	/**
	 * Check if two identifiable objects have same IDs
	 *
	 * @param o1
	 *            identifiable object 1
	 * @param o2
	 *            identifiable object 2
	 * @return true if two identifiable objects have same IDs
	 */
	public static boolean sameIds(final Identifiable o1, final Identifiable o2) {
		if (o1 == o2) { // NOSONAR
			return true;
		} else if (o1 == null || o2 == null) {
			return false;
		} else {
			return o1.getId() == o2.getId();
		}
	}

	/**
	 * Check if two identifiable object have different IDs
	 *
	 * @param o1
	 *            identifiable object 1
	 * @param o2
	 *            identifiable object 2
	 * @return true if two identifiable object have different IDs
	 */
	public static boolean notSameIds(final Identifiable o1, final Identifiable o2) {
		return !sameIds(o1, o2);
	}

	/**
	 * Convert a collection to map. The map key is the object id, the value is a list of objects having that id.
	 * <p>
	 * The null identifiable objects are removed
	 *
	 * @param collection
	 *            collection of identifiable objects
	 * @param <O>
	 *            the Identifiable type
	 * @return A map. The map key is the object id, the value is a list of objects having that id.
	 */
	public static <O extends Identifiable> SortedMap<Long, List<O>> mapByIds(final Collection<O> collection) {
		final SortedMap<Long, List<O>> map = new TreeMap<Long, List<O>>();
		if (CollectionUtils.isNotEmpty(collection)) {
			collection.stream().filter(o -> o != null).forEach(o -> {
				final Long id = getId(o);
				if (!map.containsKey(id)) {
					map.put(id, new ArrayList<>());
				}
				map.get(id).add(o);
			});
		}
		return map;
	}

	/**
	 * Creates a comparator that orders the identifiable objects according to the order of IDs list
	 *
	 * @param orderedIds
	 *            list of IDs defining the order
	 * @return comparator that orders the identifiable objects according to the order of IDs list
	 */
	public static Comparator<Identifiable> createOrderedComparator(final List<Long> orderedIds) {
		return new Comparator<Identifiable>() {

			@Override
			public int compare(final Identifiable o1, final Identifiable o2) {
				final long id1 = NumberUtils.defaultLong(getId(o1), 0L);
				final long id2 = NumberUtils.defaultLong(getId(o2), 0L);
				final int ind1 = Math.max(0, orderedIds.indexOf(id1));
				final int ind2 = Math.max(0, orderedIds.indexOf(id2));
				return ind1 - ind2;
			}
		};
	}

	/**
	 * Check if the collections of identifiable objects have the same IDs.
	 *
	 * @param col1
	 *            first collections of identifiable objects
	 * @param col2
	 *            second collections of identifiable objects
	 * @return true if the two collections have the same IDs
	 */
	public static boolean sameIds(final Collection<? extends Identifiable> col1, final Collection<? extends Identifiable> col2) {
		return getIds(col1).equals(getIds(col2));
	}

	/**
	 * Check if the provided collections do not have the same IDs
	 *
	 * @param col1
	 *            first collections of identifiable objects
	 * @param col2
	 *            second collections of identifiable objects
	 * @return true if the two collections don't have the same IDs
	 */
	public static boolean notSameIds(final Collection<? extends Identifiable> col1, final Collection<? extends Identifiable> col2) {
		return !sameIds(col1, col2);
	}
}
