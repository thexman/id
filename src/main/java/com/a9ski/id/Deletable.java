package com.a9ski.id;

/**
 * Interface representing objects that have deleted flag (soft delete)
 *
 * @author Kiril Arabadzhiyski
 *
 */
public interface Deletable {

	/**
	 * Returns true if the object is soft deleted
	 *
	 * @return true if the object is soft deleted
	 */
	public boolean isDeleted();

	/**
	 * Returns true if the object is not soft deleted
	 *
	 * @return true if the object is not soft deleted
	 */
	public boolean isNotDeleted();
}
