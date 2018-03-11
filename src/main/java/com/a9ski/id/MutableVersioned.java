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
 * Interface for object that have verions. Usually version is used in optimistic locking. Mutable version.
 * 
 * @author Kiril Arabadzhiyski
 *
 */
public interface MutableVersioned extends Versioned {

	/**
	 * Sets object version
	 * 
	 * @param version
	 *            object new version
	 */
	public void setVersion(final long version);
}
