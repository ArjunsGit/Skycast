/**
 * 
 */
package com.hackcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author arjunflex
 *
 */
public class BlockedChannels {
	
	private List<Integer> blockedChannels = 
				new ArrayList<Integer>();
	
	public void addChannelToBlockedList(Integer i) {
		blockedChannels.add(i);
	}
	
	public boolean isChannelBlocked(int c) {
		return blockedChannels.contains(new Integer(c));
	}
	
	

}
