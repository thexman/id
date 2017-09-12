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
 * Interface for objects that have title
 *
 * @author Kiril Arabadzhiyski
 *
 */
public interface Titled {
	/**
	 * Gets the object title
	 *
	 * @return the object title
	 */
	public String getTitle();

	/**
	 * Sets the object title
	 *
	 * @param title
	 *            the object title
	 */
	public void setTitle(String title);
}
