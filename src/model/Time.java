package model;

/**
 * Time
 * Gives conversions from seconds to millisecond and nanoseconds
 * 
 * @author Eric
 *
 */

public class Time {
	
	public static final long millisecond = 1000L;
	public static final long nanosecond = 1000000000L;
	private static final long milliToNano = 1000000L;
	
	// Convert seconds to nanoseconds
	public long secondToNano(long seconds){
		return seconds * Time.nanosecond;
	}
	
	// Convert nanoseconds to seconds
	public long nanoToSecond(long nano){
		return nano / nanosecond;
	}
	
	// Convert seconds to milliseconds
	public long secondToMilli(long seconds){
		return seconds * Time.millisecond;
	}
	
	// Convert milliseconds to seconds
	public long milliToSecond(long millis){
		return millis / Time.millisecond;
	}
	
	// Convert milliseconds to nanoseconds
	public long milliToNano(long millis){
		return millis * Time.milliToNano;
	}
	
	// Convert nanoseconds to milliseconds
	public long nanoToMilli(long nano){
		return nano / Time.milliToNano;
	}

}
