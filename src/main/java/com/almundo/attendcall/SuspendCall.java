package com.almundo.attendcall;

import java.util.List;

import com.almundo.dispatcher.Dispatcher;
import com.almundo.entity.call.Call;
import com.almundo.util.TimeValue;

/**
 * @author Miguel Vallejo
 *
 */
public class SuspendCall extends Thread {

    private Dispatcher dispatcher;
    private Call suspendCall;
    private List<Call> suspendCalls;

    // ESPERAR E INTENTAR SER ATENDIDO OTRA VEZ
    @Override
    public void run() {

	try {
	    sleep(1 * TimeValue.SECOND_VALUE);

	    if (suspendCall == null) {
		dispatcher.dispatchCalls(suspendCalls);
		return;
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    // CONSTRUCTORs
    public SuspendCall(Dispatcher dispatcher, Call suspendCall) {
	super();
	this.dispatcher = dispatcher;
	this.suspendCall = suspendCall;
    }

    public SuspendCall(Dispatcher dispatcher, List<Call> suspendCalls) {
	super();
	this.dispatcher = dispatcher;
	this.suspendCalls = suspendCalls;
    }
}
