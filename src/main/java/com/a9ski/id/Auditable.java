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
	 * @param created
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
