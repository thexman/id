package com.a9ski.id;

import java.util.Date;

/**
 * Interface representing auditable object
 *
 * @author Kiril Arabadzhiyski
 *
 */
public interface Auditable {
	/**
	 * Returns the id of the user who created this object
	 *
	 * @return the id of the user who created this object
	 */
	public Long getCreator();

	/**
	 * Sets the id of the user created this object
	 *
	 * @param creatorId
	 *            the id of the user who created this object
	 */
	public void setCreator(Long creatorId);

	/**
	 * Gets the id of the user who last modified this object
	 *
	 * @return the id of the user who last modified this object
	 */
	public Long getEditor();

	/**
	 * Sets the id of the user who last modified this object
	 *
	 * @param editorId
	 *            the id of the user who last modified this object
	 */
	public void setEditor(Long editorId);

	/**
	 * Gets the time stamp when this object was created
	 *
	 * @return the time stamp when this object was created
	 */
	public Date getCreated();

	/**
	 * Sets the time stamp when this object was created
	 *
	 * @param create
	 *            the time stamp when this object was created
	 */
	public void setCreated(Date created);

	/**
	 * Gets the time stamp of last modification
	 *
	 * @return the time stamp of last modification
	 */
	public Date getEdited();

	/**
	 * Sets the time stamp of last modification
	 *
	 * @param edited
	 *            the time stamp of last modification
	 */
	public void setEdited(Date edited);

	/**
	 * Changes the last modification date and user id. Sets the creation date and creation user id if they are not already set
	 *
	 * @param editorId
	 *            the user id who performed last modification the object
	 */
	public default void touch(final long editorId) {
		final Date now = new Date();
		if (getCreator() == null || getCreated() == null) {
			setCreator(editorId);
			setCreated(now);
		}
		setEditor(editorId);
		setEdited(now);
	}
}
