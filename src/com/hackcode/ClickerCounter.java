/**
 * 
 */
package com.hackcode;

/**
 * @author arjunflex
 *
 */
public class ClickerCounter {
	
	private  int count = 0;
	
	public void updateCount(int numOfClicks)
	{
		count = count + numOfClicks;
	}
	
	public int getTotalCount()
	{
		return count;
	}

}
