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

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Calculates delta between two collection of IDs (old and new ones)
 * <p>
 * Usually this class is used to identified which object have been created, modified or deleted
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class IdDelta {

	private final List<Long> removedIds;
	private final List<Long> addedIds;
	private final List<Long> intersectionIds;

	/**
	 * Calculates a new delta of two collection of IDs
	 *
	 * @param oldIds
	 *            collection representing old IDs
	 * @param newIds
	 *            collection representing new IDs
	 */
	public IdDelta(final Collection<Long> oldIds, final Collection<Long> newIds) {
		final Set<Long> existingIds = (oldIds != null ? new TreeSet<Long>(oldIds) : new TreeSet<Long>());
		final Set<Long> memoryIds = (newIds != null ? new TreeSet<Long>(newIds) : new TreeSet<Long>());

		removedIds = ExtCollectionUtils.subtract(existingIds, memoryIds);
		addedIds = ExtCollectionUtils.subtract(memoryIds, existingIds);
		intersectionIds = ExtCollectionUtils.subtract(existingIds, removedIds);
	}

	/**
	 * Gets the IDs of the object that have been removed (are present in <tt>oldIds</tt> but missing in the <tt>newIds</tt>
	 *
	 * @return the IDs of the object that have been removed (are present in <tt>oldIds</tt> but missing in the <tt>newIds</tt>
	 */
	public List<Long> getRemovedIds() {
		return ExtCollectionUtils.unmodifiableList(removedIds);
	}

	/**
	 * Gets the IDs of the object that have been added (are present in <tt>newIds</tt> but missing in the <tt>oldIds</tt>
	 *
	 * @return the IDs of the object that have been added (are present in <tt>newIds</tt> but missing in the <tt>oldIds</tt>
	 */
	public List<Long> getAddedIds() {
		return ExtCollectionUtils.unmodifiableList(addedIds);
	}

	/**
	 * Gets the IDs of the object that have not been changed (are present in <tt>newIds</tt> and in the <tt>oldIds</tt>
	 *
	 * @return the IDs of the object that have not been changed (are present in <tt>newIds</tt> and in the <tt>oldIds</tt>
	 */
	public List<Long> getIntersectionIds() {
		return ExtCollectionUtils.unmodifiableList(intersectionIds);
	}

	/**
	 * Checks if there is a delta (any newly added ID, or any removed ID)
	 * 
	 * @return true, if there is a delta (any newly added ID, or any removed ID)
	 */
	public boolean hasDelta() {
		return !removedIds.isEmpty() || !addedIds.isEmpty();
	}
}
