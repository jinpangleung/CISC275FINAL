package model;

/**
 * Time is a class that gives converts units of time within the metric system.
 * 
 * @author Eric
 */

public class Time {
	
	public static final long millisecond = 1000L;
	public static final long nanosecond = 1000000000L;
	private static final long milliToNano = 1000000L;
	
	/**
	 * Converts seconds to nanoseconds
	 * 
	 * @param seconds a long of time in seconds
	 * @return seconds in nanoseconds
	 */
	public long secondToNano(long seconds){
		return seconds * Time.nanosecond;
	}
	
	/**
     * Converts nanoseconds to seconds
     * 
     * @param nano a long of time in nanoseconds
     * @return nano in seconds
     */
	public long nanoToSecond(long nano){
		return nano / nanosecond;
	}
	
	/**
     * Converts second to milliseconds
     * 
     * @param seconds a long of time in seconds
     * @return seconds in milliseconds
     */
	public long secondToMilli(long seconds){
		return seconds * Time.millisecond;
	}
	
	/**
     * Converts milliseconds to seconds
     * 
     * @param millis a long of time in milliseconds
     * @return millis in seconds
     */
	public long milliToSecond(long millis){
		return millis / Time.millisecond;
	}
	
	/**
     * Converts milliseconds to nanoseconds
     * 
     * @param millis a long of time in milliseconds
     * @return millis in nanoseconds
     */
	public long milliToNano(long millis){
		return millis * Time.milliToNano;
	}
	
	/**
     * Converts nanoseconds to milliseconds
     * 
     * @param nano a long of time in nanoseconds
     * @return nano in milliseconds
     */
	public long nanoToMilli(long nano){
		return nano / Time.milliToNano;
	}

}
