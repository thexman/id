package com.a9ski.id;

import java.io.Serializable;

/**
 * Interface for object that have numeric id
 *
 * @author Kiril Arabadzhiyski
 *
 */
public interface Identifiable extends Serializable {
	/**
	 * The id of the object
	 * 
	 * @return the id of the object
	 */
	public long getId();

	/**
	 * Sets the id of the object
	 * 
	 * @param id
	 *            the id of the object
	 */
	public void setId(long id);

	/**
	 * Checks if the object is new
	 * 
	 * @return true if the object is new
	 */
	public default boolean isNew() {
		return getId() == 0;
	}

	/**
	 * Checks if the object is existing (not new)
	 * 
	 * @return true if the object is existing
	 */
	public default boolean isExisting() {
		return !isNew();
	}
}
