package com.almundo.entity.employee;

import com.almundo.enums.EmployeeRangeEnum;

/**
 * @author Miguel Vallejo 
 *
 */
public abstract class Employee {

    private String employeeName;
    protected EmployeeRangeEnum range;
    private boolean busy = false;
    private int callCount = 0;

    // OTHER METHODs
    public abstract String getPresentation();
    
    // GETTERs && SETTERs
    public String getEmployeeName() {
	return employeeName;
    }

    public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
    }

    public EmployeeRangeEnum getRange() {
	return range;
    }

    public boolean isBusy() {
	return busy;
    }

    public void setBusy(boolean busy) {
	this.busy = busy;
    }

    public int getCallCount() {
        return callCount;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }

}
