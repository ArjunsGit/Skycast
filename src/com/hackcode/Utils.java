/**
 * 
 */
package com.hackcode;

import java.util.List;

/**
 * @author arjunflex
 *
 */
public class Utils {
	
	public static final int DOWN = 1;
	public static final int UP = 2;
	public static final int BACKDOWN = 3;
	public static final int BACKUP = 4;
	public static final int KEYIN = 5;
	public static final int BACK = 6;
	
	public static int numberOfDigits(int num)
	{
		int count = 0;
		num = Math.abs(num);
		
		if(num < 10 )
		{
			return 1;
		}
		while(num >  0 )
		{
			count++;
			num = num /10;
		}
		
		return count;
	}
	
	public static String printListOfIntegerToString(List<Integer> list) 
	{
		StringBuffer buffer = new StringBuffer();
		
		for(int i  = 0; i < list.size(); i++) {
			
			buffer.append(list.get(i)).append(" ");
			
		}
		
		return buffer.toString();
	}

}
