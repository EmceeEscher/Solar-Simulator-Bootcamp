/**
 * This class acts as a log for the program, and could be used for error bug-hunting,
 * and performance management. It's a Singleton design pattern; there is only one Log object. 
 */
package com.ubcsolar.common;


//TODO: make this log it's own thread with an event queue. 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Noah
 *
 */
public class SolarLog {
	private static SolarLog oneInstance;
	
	private List<LogEntry> theLog;
	private class LogEntry{
		public final LogType reason;
		public final Long time;
		public final String theLog;
		public LogEntry(LogType reason, Long time, String theLog){
			this.reason = reason;
			this.time = time;
			this.theLog = theLog;
		}
	}
	
	private SolarLog(){
		theLog = new ArrayList<LogEntry>();
		
	}
	
	
	private static SolarLog getInstance(){
		if(oneInstance == null){
			oneInstance = new SolarLog();
		}
		return oneInstance;
	}
	
	public void add(LogType code, Long time, String message){
		theLog.add(new LogEntry(code, time, message));
		
	}
	
	public static void printOut(){
		List<LogEntry> temp = getInstance().theLog;
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
		for(int i = 0; i<getInstance().theLog.size(); i++){
			System.out.print(dateFormat.format(temp.get(i).time) + " | ");
			// The following is so they all line up nicely on print.
			// There's probably a better way to do this...
			if(temp.get(i).reason == LogType.ERROR){
				System.out.print("   ERROR      | ");
			}
			else if(temp.get(i).reason == LogType.NOTIFICATION){
				System.out.print(LogType.NOTIFICATION + "  | ");
			}
			else{
				System.out.print(temp.get(i).reason + " | ");
			}
			
			System.out.println(temp.get(i).theLog);
		}
	}
	
	/**
	 * This method adds things to the log. I didn't want to add
	 * and override because I want the time code to come from when
	 * the reason for the log was encountered (i.e when error occured)
	 * 
	 * @param The entry type (error? Regular?)
	 * @param The time the error occured
	 * @param The message to enter into the log
	 */
	public static void write(LogType code, Long time, String entry){
		getInstance().add(code, time, entry);
		
	}

}
