/**
 * 
 */
package com.hackcode;

import java.util.List;
import java.util.Map;

/**
 * @author arjunflex
 *
 */
public class ChannelClicks {
	
	private int min = 10;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	   
		MinMaxChannels minMaxChannels = new MinMaxChannels();
		minMaxChannels.setMin(1);
		minMaxChannels.setMax(20);
		System.out.println(minMaxChannels.getMin() + " " 
							+ minMaxChannels.getMax());
		BlockedChannels bc = new BlockedChannels();
		bc.addChannelToBlockedList(18);
		bc.addChannelToBlockedList(19);
		
		System.out.println(bc.isChannelBlocked(18) + "  " 
						+ bc.isChannelBlocked(19) );
		
		ViewChannels viewChannels = new ViewChannels();
		viewChannels.addChannel(15);
		viewChannels.addChannel(14);
		viewChannels.addChannel(17);
		viewChannels.addChannel(1);
		viewChannels.addChannel(17);
		
		System.out.println( "" + 
		                   viewChannels.getChannel(1) + " "
		                   +viewChannels.getChannel(2) + " "
		                   +viewChannels.getChannel(3) + " "
		                   +viewChannels.getChannel(4) + " "
		                   +viewChannels.getChannel(5));
		
		ChannelController channelController = 
				 new ChannelController(bc, minMaxChannels);
		
		ClickerCounter clickCounter = new ClickerCounter();
		
		ChannelBrowser browser = new ChannelBrowser(clickCounter, 
				               viewChannels, channelController);
		
		/*
		 * Test case 2
		 */
		MinMaxChannels minMaxChannels2 = new MinMaxChannels();
		minMaxChannels2.setMin(103);
		minMaxChannels2.setMax(108);
		BlockedChannels blockedChannels2 = new BlockedChannels();
		blockedChannels2.addChannelToBlockedList(104);
		ViewChannels viewChannels2 = new ViewChannels();
		viewChannels2.addChannel(105);
		viewChannels2.addChannel(106);
		viewChannels2.addChannel(107);
		viewChannels2.addChannel(103);
		viewChannels2.addChannel(105);
		
		ChannelController channelController2 = 
				 new ChannelController(blockedChannels2, minMaxChannels2);
		
		ClickerCounter clickCounter2 = new ClickerCounter();
		
		ChannelBrowser browser2 = new ChannelBrowser(clickCounter2, 
				               viewChannels2, channelController2);
		
		/*
		 * Test Case 3
		 */
		
		MinMaxChannels minMaxChannels3 = new MinMaxChannels();
		minMaxChannels3.setMin(1);
		minMaxChannels3.setMax(100);
		BlockedChannels blockedChannels3 = new BlockedChannels();
		blockedChannels3.addChannelToBlockedList(78);
		blockedChannels3.addChannelToBlockedList(79);
		blockedChannels3.addChannelToBlockedList(80);
		blockedChannels3.addChannelToBlockedList(3);
		
		ViewChannels viewChannels3 = new ViewChannels();
		viewChannels3.addChannel(10);
		viewChannels3.addChannel(13);
		viewChannels3.addChannel(13);
		viewChannels3.addChannel(100);
		viewChannels3.addChannel(99);
		viewChannels3.addChannel(98);
		viewChannels3.addChannel(77);
		viewChannels3.addChannel(81);
		
		ChannelController channelController3 = 
				 new ChannelController(blockedChannels3, minMaxChannels3);
		
		ClickerCounter clickCounter3 = new ClickerCounter();
		
		ChannelBrowser browser3 = new ChannelBrowser(clickCounter3, 
				               viewChannels3, channelController3);
		/*
		 * Test Case 4
		 */
		MinMaxChannels minMaxChannels4 = new MinMaxChannels();
		minMaxChannels4.setMin(1);
		minMaxChannels4.setMax(200);
		BlockedChannels blockedChannels4 = new BlockedChannels();
	
		
		ViewChannels viewChannels4 = new ViewChannels();
		viewChannels4.addChannel(1);
		viewChannels4.addChannel(100);
		viewChannels4.addChannel(1);
		viewChannels4.addChannel(101);
	
		
		ChannelController channelController4 = 
				 new ChannelController(blockedChannels4, minMaxChannels4);
		
		ClickerCounter clickCounter4 = new ClickerCounter();
		
		ChannelBrowser browser4 = new ChannelBrowser(clickCounter4, 
				               viewChannels4, channelController4);
	    
		/*
		 * Test case 5
		 */
		
		MinMaxChannels minMaxChannesls5 = new MinMaxChannels();
		minMaxChannesls5.setMin(4);
		minMaxChannesls5.setMax(28);
		BlockedChannels blockedChannesls5 = new BlockedChannels();
		blockedChannesls5.addChannelToBlockedList(6);
		blockedChannesls5.addChannelToBlockedList(14);
		blockedChannesls5.addChannelToBlockedList(18);
		blockedChannesls5.addChannelToBlockedList(19);
		blockedChannesls5.addChannelToBlockedList(20);
		ViewChannels viewChannels5 = new ViewChannels();
		viewChannels5.addChannel(7);
		viewChannels5.addChannel(10);
		viewChannels5.addChannel(13);
		viewChannels5.addChannel(15);
		viewChannels5.addChannel(21);
		viewChannels5.addChannel(23);
		viewChannels5.addChannel(11);
		viewChannels5.addChannel(23);
		
		
		ChannelController channelController5 = 
				 new ChannelController(blockedChannesls5, minMaxChannesls5);
		
		ClickerCounter clickCounter5 = new ClickerCounter();
		
		ChannelBrowser browser5 = new ChannelBrowser(clickCounter5, 
				               viewChannels5, channelController5);
		
		System.out.println(Utils.numberOfDigits(10));
		
		
     
	}

}
