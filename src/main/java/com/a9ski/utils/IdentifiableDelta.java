package com.a9ski.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.a9ski.id.Identifiable;

/**
 * Calculates delta between two collection of identifiable objects (the old ones and the new ones)
 * <p>
 * Usually this class is used to identified which object have been created, modified or deleted
 *
 * @author Kiril Arabadzhiyski
 *
 * @param <O>
 *            the type of old objects
 * @param <N>
 *            the type of new objects
 */
public class IdentifiableDelta<O extends Identifiable, N extends Identifiable> extends IdDelta {
	private final List<O> removed;
	private final List<N> added;
	private final List<O> oldObjects;
	private final List<N> newObjects;
	private final List<Pair<O, N>> intersection = new ArrayList<>();

	/**
	 * Calculates a new delta between old objects and new objects
	 *
	 * @param oldObjects
	 *            collection of old identifiable objects
	 * @param newObjects
	 *            collection of new identifiable objects
	 */
	public IdentifiableDelta(final List<O> oldObjects, final List<N> newObjects) {
		super(IdentifiableUtils.getIds(oldObjects), IdentifiableUtils.getIds(newObjects));
		this.oldObjects = ExtCollectionUtils.copy(oldObjects);
		this.newObjects = ExtCollectionUtils.copy(newObjects);

		this.removed = IdentifiableUtils.getByIds(oldObjects, getRemovedIds());
		this.added = IdentifiableUtils.getByIds(newObjects, getAddedIds());

		getIntersectionIds().forEach(id -> {
			final N newObject = IdentifiableUtils.getById(newObjects, id);
			final O oldObject = IdentifiableUtils.getById(oldObjects, id);
			this.intersection.add(Pair.of(oldObject, newObject));
		});
	}

	/**
	 * Gets the object that have been removed (present in the <tt>oldObjects</tt> collection, but missing in <tt>newObjects</tt> collection)
	 *
	 * @return the object that have been removed (present in the <tt>oldObjects</tt> collection, but missing in <tt>newObjects</tt> collection)
	 */
	public List<O> getRemoved() {
		return ExtCollectionUtils.unmodifiableList(removed);
	}

	/**
	 * Gets the object that have been added (present in the <tt>newObjects</tt> collection, but missing in <tt>oldObjects</tt> collection)
	 *
	 * @return the object that have been added (present in the <tt>newObjects</tt> collection, but missing in <tt>oldObjects</tt> collection)
	 */
	public List<N> getAdded() {
		return ExtCollectionUtils.unmodifiableList(added);
	}

	/**
	 * Gets the objects that have not been changed (are present in <tt>newObjects</tt> and in the <tt>oldObjects</tt>
	 * <p>
	 * The first element of the pair is the element from <tt>oldObjects</tt>, the second element of the pair is element from <tt>newObjects</tt>
	 *
	 * @return the objects that have not been changed (are present in <tt>newObjects</tt> and in the <tt>oldObjects</tt>
	 */
	public List<Pair<O, N>> getIntersection() {
		return ExtCollectionUtils.unmodifiableList(intersection);
	}

	/**
	 * Returns the list of old objects
	 * 
	 * @return the list of old objects
	 */
	public List<O> getOldObjects() {
		return ExtCollectionUtils.unmodifiableList(oldObjects);
	}

	/**
	 * Returns the list of new objects
	 * 
	 * @return the list of new objects
	 */
	public List<N> getNewObjects() {
		return ExtCollectionUtils.unmodifiableList(newObjects);
	}
}
