/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          p4
// FILE:             Interval
//
// TEAM:    Team 82 
// Authors: 
// Author1: Matt Marcouiller, mcmarcouille@wisc.edu, 9071489638, 003
// Author2: Zhiheng Wang, zwang759@wisc.edu, 9074796922 ,003
// 
//////////////////////////// 80 columns wide //////////////////////////////////
public class Interval<T extends Comparable<T>> implements IntervalADT<T> {

    //start of interval
	T start;
	
	//end of interval
	T end;
	
	//label of the project and interval 
	String label; 
	
	/**
	 * 
	 * Constructor class for interval 
	 * Constructs 
	 * 
	 * @param start beginning of interval
	 * @param end   end of interval
	 * @param label project label with interval 
	 */
    public Interval(T start, T end, String label) {
        
    	this.start = start;
    	this.end = end;
    	this.label = label;
    	
    }

    @Override
    /**
     * Returns start value of interval
     */
    public T getStart() {
        return start;
    }

    @Override
    /**
     * Returns end value of interval 
     */
    public T getEnd() {
        return end;
    }

    @Override
    /**
     * Returns label of interval
     */
    public String getLabel() {
        return label;
    }

    @Override
    /**
     * Return true if interval overlaps with other interval 
     * 
     * @param   other other interval to compare
     * @return  true if it overlaps, false if no overlap 
     */
    public boolean overlaps(IntervalADT<T> other) {
        if (start.compareTo(other.getEnd()) > 0 || 
        		end.compareTo(other.getStart()) < 0)
        	return false;
        return true; 
    }

    @Override
    /**
     * Return true if point falls in interval
     * 
     * @param point  point we use to search 
     * @return true  if point falls in interval
     */
    public boolean contains(T point) {
        return (point.compareTo(start) >= 0 ) && 
        		(point.compareTo(end) <= 0);
    }

    @Override
    /**
     * Compares interval with other interval 
     * 	return negative if interval come before the other interval
     * 	
     * This method compares intervals by first comparing the start values 
     * of each other. If the start values are the same then the end values
     * are compared. If start values are not the same, then the en values 
     * are not compared. 
     * 
     * @param   other  the other interval to compare 
     * @return  neg if interval happens before other 
     * 			pos if interval happens after other 
     * 			0 if intervals are the same 
     */
    public int compareTo(IntervalADT<T> other) {
    	if (start.compareTo(other.getStart()) == 0)
			return end.compareTo(other.getEnd());
		return start.compareTo(other.getStart());
    }

}
