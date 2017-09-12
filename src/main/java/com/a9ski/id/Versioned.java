package com.a9ski.id;

/**
 * Interface for object that have verions. Usually version is used in optimistic locking.
 * 
 * @author Kiril Arabadzhiyski
 *
 */
public interface Versioned {
	/**
	 * Gets object version
	 * 
	 * @return object version
	 */
	public long getVersion();

	/**
	 * Sets object version
	 * 
	 * @param version
	 *            object new version
	 */
	public void setVersion(final long version);
}
