/**
 * 
 */
package com.hackcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author arjunflex
 *
 */
public class ViewChannels {
	
	private Map<Integer, Integer> viewChannels
			= new HashMap<Integer, Integer>();
	private  int count = 1;
	
	public void addChannel(int c) {
		viewChannels.put(count++, new Integer(c));
	}
	
	public int getChannel(int position) {
		return viewChannels.get(new Integer(position));
	}
	
	public int getCount() {
		return viewChannels.size();
	}

}
