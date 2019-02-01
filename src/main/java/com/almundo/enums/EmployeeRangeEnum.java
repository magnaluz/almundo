package com.almundo.enums;

/**
 * @author Miguel Vallejo 
 *
 */
public enum EmployeeRangeEnum {

	OPERATOR(0), SUPERVISOR(1), DIRECTOR(2);
	
    private final int value;

    private EmployeeRangeEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
    	return this.value;
    }
    
    public int compare(EmployeeRangeEnum other) {
    	
    	if(this.value > other.getValue()) {
    		return 1;
    	}
    	if(this.value < other.getValue()) {
    		return -1;
    	}
    	return 0;
    	
    }
}
