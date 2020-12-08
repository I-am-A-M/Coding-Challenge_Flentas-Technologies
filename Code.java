import java.io.*;
import java.util.Scanner;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class sortByEvents implements Comparator<GFG>
{
	public int compare(GFG a, GFG b)
    {
        if(a.hours != b.hours) return (a.hours - b.hours); 
        else 
        return (a.minutes - b.minutes);
    }
}

class GFG {

	int hours; 
	int minutes;

	public GFG(int hours, int minutes)
	{
		this.hours = hours; 
		this.minutes = minutes; 
	} 

	 public String toString(){  
       return hours+" "+minutes;  
    } 



	public static GFG getDifference(GFG start, GFG stop)
	{
		GFG diff = new GFG(0, 0);

      
        if(start.minutes > stop.minutes){
            --stop.hours;
            stop.minutes += 60;
        }

        diff.minutes = stop.minutes - start.minutes;
        diff.hours = stop.hours - start.hours;

        
        return(diff);
	}


	static void showMaxDifference(String strArr[],int n)
	{
		String startTime[] = new String[n]; 
		String endTime[] = new String[n]; 
		final int lengthOfEvent = strArr[0].length();

		List<GFG> startEvents = new ArrayList<>();
		List<GFG> endEvents = new ArrayList<>();  
		

		for(int i = 0; i < n; i++)
		{   
			String currTime = strArr[i]; 
			
			int startHr = Integer.parseInt(currTime.substring(0,2));
			int startMin = Integer.parseInt(currTime.substring(3,5));

			int endHr =  Integer.parseInt(currTime.substring(9,11));
			int endMin =  Integer.parseInt(currTime.substring(12,14));
			
			if(currTime.charAt(6) == 'P')
			{
				
				if(startHr != 12)
				startEvents.add(new GFG(startHr+12,startMin));
				else
				startEvents.add(new GFG(startHr,startMin));
			}
			else
			{
				
				startEvents.add(new GFG(startHr,startMin));
			}

			if(currTime.charAt(15) == 'P')
			{
				 
				if(endHr != 12)
				endEvents.add(new GFG(endHr+12,endMin));
				else 
				endEvents.add(new GFG(endHr,endMin));
				
				
			}
			else
			{
				
				endEvents.add(new GFG(endHr,endMin));
			}

				
		}

		Collections.sort(startEvents, new sortByEvents());
		Collections.sort(endEvents, new sortByEvents());

		GFG maxTimeDifference = new GFG(0,0);

		for(int i = 1; i < n; i++)
		{
			GFG currDiff = getDifference(endEvents.get(i-1),startEvents.get(i));

			if(maxTimeDifference.hours < currDiff.hours)
				maxTimeDifference = currDiff; 
			else if(maxTimeDifference.hours == currDiff.hours && maxTimeDifference.minutes < currDiff.minutes)
				maxTimeDifference = currDiff; 
		}


		System.out.println("Maximum Time Difference is " + maxTimeDifference.hours + " : " + maxTimeDifference.minutes);


	}

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of events you want to have");
		int n = Integer.parseInt(sc.nextLine());
       
		String strArr[] = new String[n];
        
        System.out.println("Start Entering the events in the correct format");
		for(int i = 0; i < n; i++)
		{
			String currInput = sc.nextLine();
			strArr[i] = currInput;
			System.out.println(strArr[i]);
		} 

		showMaxDifference(strArr,n);
	}
}
