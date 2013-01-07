/**
 * 
 */
package com.hackcode;

/**
 * @author arjunflex
 *
 */
public class ChannelController {
	
	private int currentChanel;
	private int prevChannel;
	
	private MinMaxChannels minMaxChannel;
	private BlockedChannels blockedChannels;
	
	
	private ChannelController() { }
	
	public ChannelController(BlockedChannels blockedChannels,
							 MinMaxChannels minMaxChannels)
	{
		this.blockedChannels = blockedChannels;
		this.minMaxChannel = minMaxChannels;
	}
	
	public int upChannel(int channel) {
		prevChannel = channel;
		return (findUpNextUnblockedChannel(channel));
	}
	
	public int downChannel(int channel) {
		prevChannel = channel;
		return (findDownNextUnblockedChannel(channel));
	}
	
	public void jumpToChannel(int num) {
		currentChanel = num;
	}
	
	public void setPreviousChannel(int channel) {
		prevChannel = channel;
	}
	public void setCurrentChannel(int channel) {
		currentChanel = channel;
	}
	
	public int getCurentChannel() {
		return currentChanel;
	}
	
	public int getBackChannel() {
		currentChanel = prevChannel;
		return prevChannel;
	}
	
	public void setBackChannel(int num) {
		currentChanel = num;
	}
	
	private int findUpNextUnblockedChannel(int channel) {
		int nextChannel = 0;
		
		if(channel == minMaxChannel.getMax()) {
			nextChannel = minMaxChannel.getMin();
		}else {
			nextChannel = channel + 1;
		}
		
		while(blockedChannels.isChannelBlocked(nextChannel)) {
			if(channel == minMaxChannel.getMax()) {
				nextChannel = minMaxChannel.getMin();
			}else {
				nextChannel = nextChannel + 1;
			}
		}
		currentChanel = nextChannel;
		return nextChannel;
	}
	
	private int findDownNextUnblockedChannel(int channel) {
		int nextChannel = 0;
		
		if(channel == minMaxChannel.getMin()) {
			nextChannel = minMaxChannel.getMax();
		}else {
			nextChannel = channel - 1;
		}
		
		while(blockedChannels.isChannelBlocked(nextChannel)) {
			if(channel == minMaxChannel.getMin()) {
				nextChannel = minMaxChannel.getMax();
			}else {
				nextChannel = nextChannel - 1;
			}
		}
		currentChanel = nextChannel;
		return nextChannel;
	}


}
