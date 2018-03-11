package com.a9ski.id;

/**
 * Interface for object that have numeric id. Mutable version
 *
 * @author Kiril Arabadzhiyski
 *
 */
public interface MutableIdentifiable extends Identifiable {

	/**
	 * Sets the id of the object
	 * 
	 * @param id
	 *            the id of the object
	 */
	public void setId(long id);

}
