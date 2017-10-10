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

	/**
	 * Sets soft deleted flag
	 */
	public void setDeleted(boolean deleted);
}
