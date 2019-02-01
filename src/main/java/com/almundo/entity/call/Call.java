package com.almundo.entity.call;

/**
 * @author Miguel Vallejo
 *
 */
public class Call {

    private String callName;
    // Datos para auditoria
    private String employeeName;
    private int durationTime;
    private int waitTime;

    // CONSTRUCTORs
    public Call() {
    }

    public Call(String name) {
	this.callName = name;
    }

    // GETTERs && SETTERs
    public String getCallName() {
	return callName;
    }

    public void setCallName(String callName) {
	this.callName = callName;
    }

    public String getEmployeeName() {
	return employeeName;
    }

    public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
    }

    public int getDurationTime() {
	return durationTime;
    }

    public void setDurationTime(int durationTime) {
	this.durationTime = durationTime;
    }

    public int getWaitTime() {
	return waitTime;
    }

    public void setWaitTime(int waitTime) {
	this.waitTime = waitTime;
    }

}
