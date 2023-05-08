package com.telepathy.question1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MeetingRoomCount {
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (args.length < 1) {
            System.out.println("Usage: java meetings file path [meetings file Path]");
            return;
        }
       
    	String filePath = args[0];
        BufferedReader reader;
        List<LocalTime> startTimes=new ArrayList<LocalTime>();
        List<LocalTime> endTimes=new ArrayList<LocalTime>();
        LocalTime startTime;
        LocalTime endTime;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();

			while (line != null) {
				String[] times=line.split("-");
				startTime= LocalTime.of(Integer.parseInt(times[0].split(":")[0].trim()), Integer.parseInt(times[0].split(":")[1].trim()));
				endTime= LocalTime.of(Integer.parseInt(times[1].split(":")[0].trim()), Integer.parseInt(times[1].split(":")[1].trim()));
				startTimes.add(startTime);
				endTimes.add(endTime);
				// read next line
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		
		
		Collections.sort(startTimes, (x1,x2)->x1.compareTo(x2));
		Collections.sort(endTimes, (x1,x2)->x1.compareTo(x2));
		
		 // Initialize minHeap with the end time of the first meeting
        PriorityQueue<LocalTime> meetingRoomholder = new PriorityQueue<>();
        meetingRoomholder.add(endTimes.get(0));
        
        // Iterate through the remaining meetings
        for (int i = 0; i < startTimes.size(); i++) {
            LocalTime meeting = startTimes.get(i);
            LocalTime minEnd = meetingRoomholder.peek();
            if (meeting.compareTo(minEnd)>=0) {
                // This meeting can use the same room as the previous meeting
            	meetingRoomholder.poll();
            }
            // Add this meeting's end time to the minHeap
            if(i<startTimes.size()-1)
            	meetingRoomholder.add(endTimes.get(i+1));
        }
        
        // The size of the minHeap is the minimum number of meeting rooms required
        System.out.println("Minimum number of meeting rooms required: " + meetingRoomholder.size());
	}

}
