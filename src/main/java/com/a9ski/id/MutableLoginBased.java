package com.a9ski.id;

/**
 * Interface for objects that have login. Mutable version.
 *
 * @author Kiril Arabadzhiyski
 *
 */
public interface MutableLoginBased extends LoginBased {
	/**
	 * Sets the login
	 * 
	 * @param login
	 *            the login
	 */
	public void setLogin(String login);
}
