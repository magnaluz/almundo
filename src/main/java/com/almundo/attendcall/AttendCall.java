package com.almundo.attendcall;

import com.almundo.dispatcher.Dispatcher;
import com.almundo.entity.call.Call;
import com.almundo.entity.employee.Employee;
import com.almundo.util.GenericUtil;
import com.almundo.util.TimeValue;

/**
 * @author Miguel Vallejo
 *
 */
public class AttendCall extends Thread {

    private Dispatcher dispatcher;
    private Call call;
    private Employee employee;

    // ATENDER LA LLAMADA
    @Override
    public void run() {
	try {
	    // SLEEP -> simulacion de la llamada
	    int duration = GenericUtil.getCallDuration();
	    sleep(duration * TimeValue.SECOND_VALUE);

	    // SETEO DE DATOS DE AUDITORIA
	    this.call.setEmployeeName(this.employee.getEmployeeName());
	    this.call.setDurationTime(duration);
	    this.employee.setCallCount(this.employee.getCallCount() + 1);
	    this.employee.setBusy(false);

	    dispatcher.freeThread(this);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    // CONSTRUCTORs
    public AttendCall(int id, Dispatcher dispatcher, Employee employee, Call call) {
	this.setName(String.valueOf(id));
	this.dispatcher = dispatcher;
	this.employee = employee;
	this.call = call;
    }

    // GETTERS && SETTERS
    public Call getCall() {
	return call;
    }

    public void setCall(Call call) {
	this.call = call;
    }

    public Employee getEmployee() {
	return employee;
    }

    public void setEmployee(Employee employee) {
	this.employee = employee;
    }

    public Dispatcher getDispatcher() {
	return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
	this.dispatcher = dispatcher;
    }

}
