package com.hackcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelBrowser {
	
	
	private ClickerCounter clickerCounter;
	private ViewChannels viewChannels;
	private ChannelController controller;
	private List<Integer> keyIndistances;
	private List<Integer> upDistances;
	private List<Integer> downDistances;
	private List<Integer> backUpDistances;
	private List<Integer> backDownDistances;
	//private List<Integer> backDistances;
	
	
	public enum step {BACK, UP, DOWN, KEYIN, NOOP};
	private step lastStep = null;
	
	private ChannelBrowser() { }
	
	public ChannelBrowser(ClickerCounter counter,
						 ViewChannels channels,
						 ChannelController controller) {
		this.clickerCounter = counter;
		this.viewChannels = channels;
		this.controller = controller;
		this.upDistances = new ArrayList<Integer>();
		this.downDistances = new ArrayList<Integer>();
		this.keyIndistances = new ArrayList<Integer>();
		this.backUpDistances = new ArrayList<Integer>();
		this.backDownDistances = new ArrayList<Integer>();
		//this.backDistances = new ArrayList<Integer>();
		computeDistances();
		findPath();
	}
	
   private void computeDistances() {
	   int channelNum = 0;
	   int prevChannelNum = 0;
	   int backChannelNum = 0;
	   
	   for(int i = 1; i <= viewChannels.getCount(); i++)
	   {
		    channelNum = viewChannels.getChannel(i);
		    int downCounts = 0;
		    int upCounts = 0;
		    int backUpCounts = 0;
		    int backDownCounts = 0;
		    
		    List<Integer> list = new ArrayList<Integer>();
		    
		    if(i > 1)
		    {
		    	prevChannelNum =viewChannels.getChannel(i - 1);
		    }
		    /**
		     * If it first channel in the viewable list of channels
		     * User has to key in the numbers. He cannot use back,up,down 
		     * channel buttons
		     */
		    if(i == 1) {
		    	list.add(Utils.numberOfDigits(channelNum));
		    	controller.jumpToChannel(channelNum);
		    	controller.setPreviousChannel(0);
		    	keyIndistances.add(Utils.numberOfDigits(channelNum) );
		    	downDistances.add(10000);
		    	upDistances.add(10000);
		    	backDownDistances.add(10000);
		    	backUpDistances.add(10000);
		    	//backDistances.add(10000);
		    }
		    
		    /**
		     * If it is second channel in the list the user
		     * can go to this channel in three ways
		     * key in
		     * up
		     * down
		     * Since the user has keyed in the first number,
		     * he can only either use up or down, he cannot combine 
		     * both key in and combine
		     
		    if(i == 2) {
		    	list.add(Utils.numberOfDigits(channelNum));
		    	
		    	int temp = controller.downChannel(prevChannelNum);
		    	while(temp != channelNum) {
		    		++downCounts;
		    		temp = controller.downChannel(temp);
		    	}
		    	
		    	
		    	list.add(++downCounts);
		    	temp = controller.upChannel(prevChannelNum);
		       	while(temp != channelNum) {
		    		++upCounts;
		    		temp = controller.upChannel(temp);
		    	}
		       	list.add(++upCounts);
		       	
		       	controller.setCurrentChannel(channelNum);
		    } */
		    
		    /**
		     * If it is greater than second channel in the list the user
		     * can go to this channel in five ways
		     * key in
		     * up
		     * down
		     * back
		     * back up
		     * back down
		     
		     */

		    
		    if(i > 1) {
		    	if(i > 2) {
		    		backChannelNum = viewChannels.getChannel(i -2);
		    	}
		    	keyIndistances.add(Utils.numberOfDigits(channelNum));

		    	int temp = controller.downChannel(prevChannelNum);
		    	while(temp != channelNum) {
		    		++downCounts;
		    		temp = controller.downChannel(temp);
		    	}
		    	downDistances.add(++downCounts);
		    	
		    	
		    	temp = controller.upChannel(prevChannelNum);
		       	while(temp != channelNum) {
		    		++upCounts;
		    		temp = controller.upChannel(temp);
		    	}
		       	upDistances.add(++upCounts);
		       	
		       	if(i <= 2) {
		       		backDownDistances.add(10000);
		       		backUpDistances.add(10000);
		       	}
                if(i > 2) { 
			       	temp = controller.downChannel(backChannelNum);
			    	while(temp != channelNum) {
			    		++backDownCounts;
			    		temp = controller.downChannel(temp);
			    	}
			    	backDownDistances.add(++backDownCounts + 1);
			    	
			    	temp = controller.upChannel(backChannelNum);
			       	while(temp != channelNum) {
			    		++backUpCounts;
			    		temp = controller.upChannel(temp);
			    	}
			       	backUpDistances.add(++backUpCounts + 1 );
	                
			       	if(channelNum == backChannelNum) {
			       		//backDistances.add(1);
			       	}
                }
		    }
		    
		   
	   }
   }
   
   public Integer getDistances(int type, int channelNum)
   {
	   switch(type) {
	   case Utils.BACK:
		   return 0;//backDistances.get(channelNum);
	   case Utils.UP:
		   return upDistances.get(channelNum);
	   case Utils.BACKUP:
		   return backUpDistances.get(channelNum);
	   case Utils.BACKDOWN:
		   return backDownDistances.get(channelNum);
	   case Utils.KEYIN:
		   return keyIndistances.get(channelNum);
	   case Utils.DOWN:
		   return downDistances.get(channelNum);
		   
	   }
	   
	   return null;
   }
   
   private int findPath() {
	   
	   int minClicks = 0;
	   
	   
	   for(int i = 1; i <= viewChannels.getCount(); i++) {
		   /*
		    * If it is first channel
		    * then key in the numbers
		    */
		   int channel = viewChannels.getChannel(i );
		   if(i == 1) { 
			   lastStep = step.KEYIN;
			   minClicks = keyIndistances.get(i - 1);
		   }
		   
		   if(i > 1) {
			   int tempchannel = viewChannels.getChannel(i -1);
			   if (tempchannel == channel) {
				   continue;
			   }
			   
		   }
		   if(i == 2) {
			   minClicks = min(keyIndistances.get(i - 1), 
					   downDistances.get(i - 1), 
					   upDistances.get(i - 1)) + minClicks;
		   }
		   
		   if(i > 2) {
			   int tempBackDist = 10000;
			   if(channel == viewChannels.getChannel(i - 2) ){
				   tempBackDist = 1;
			   }

			   if(lastStep == step.BACK) {
				   minClicks = min(10000,
						   downDistances.get(i - 1),
						   upDistances.get(i -1),
						   backDownDistances.get(i - 1),
						   backUpDistances.get(i - 1),
						   tempBackDist
						   ) + minClicks;
			   } else {
				   minClicks = min(keyIndistances.get(i - 1),
						   downDistances.get(i - 1),
						   upDistances.get(i - 1),
						   backDownDistances.get(i - 1),
						   backUpDistances.get(i - 1),
						   tempBackDist
						   ) + minClicks;
			   }
		   }
			   
			   
	   }
       	   
	   System.out.println(minClicks);
	   return minClicks;
	   
   }
   
   
	private int min(Integer keyIn, Integer down, Integer up) {
		
			if(keyIn <= down && keyIn <= up) {
				lastStep = step.KEYIN;
				return keyIn;
			}
			
			if(down <= keyIn && down <= up) {
				lastStep = step.DOWN;
				return down;
			}
			
			if(up <= keyIn && up <= down) {
				lastStep = step.UP;
				return up;
			}
	
		
		return 0;
	}
	
	private int min(Integer keyIn, 
					Integer down, 
					Integer up, 
					Integer backDown,
					Integer backUp,
					Integer back
					) {
		
		
		if(keyIn <= backDown && keyIn <= backUp && keyIn <= back && 
			keyIn <= down && keyIn <= up)
		{
			lastStep = step.KEYIN;
			 return keyIn;
		} 
		
		if(down <= backDown && down <= backUp && down <= back && 
				down <= keyIn && down <= up)
		{
				lastStep = step.DOWN;
				 return down;
	     }
		
		if(up <= backDown && up <= backUp && up <= back && 
				up <= keyIn && up <= down)
		{
				lastStep = step.UP;
				 return up;
		}
		
		if(backDown <= keyIn && backDown <= backUp && backDown <= back && 
				backDown <= up && backDown <= down)
		{
				if(backDown == keyIn) {
			     lastStep = step.KEYIN;
				 return keyIn;
				}
				if(backDown == up) {
				     lastStep = step.UP;
					 return up;
				}
				if(backDown == down) {
				     lastStep = step.DOWN;
					 return down;
				} else {
					lastStep = step.BACK;
					return backDown;
				}
		}
		
		if(backUp <= keyIn && backUp <= backDown && backUp <= back && 
				backUp <= up && backUp <= down)
		{
				if(backUp == keyIn) {
			     lastStep = step.KEYIN;
				 return keyIn;
				}
				if(backUp == up) {
				     lastStep = step.UP;
					 return up;
				}
				if(backUp == down) {
				     lastStep = step.DOWN;
					 return down;
				} else {
					lastStep = step.BACK;
					return backUp;
				}
		}
		
		if(back <= keyIn && back <= backDown && back <= backUp && 
				back <= up && back <= down)
		{
				if(back == keyIn) {
			     lastStep = step.KEYIN;
				 return keyIn;
				}
				if(back == up) {
				     lastStep = step.UP;
					 return up;
				}
				if(back == down) {
				     lastStep = step.DOWN;
					 return down;
				} else {
					lastStep = step.BACK;
					return back;
				}
		}
		
		return 0;
		
	}
	

}
