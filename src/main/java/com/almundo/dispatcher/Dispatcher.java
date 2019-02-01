package com.almundo.dispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

import com.almundo.attendcall.AttendCall;
import com.almundo.entity.call.Call;
import com.almundo.entity.employee.Employee;
import com.almundo.enums.EmployeeRangeEnum;
import com.almundo.util.TimeValue;

/**
 * @author Miguel Vallejo
 *
 */
public class Dispatcher {

    private static final Logger LOGGER = Logger.getLogger(Dispatcher.class.getName());
    private static int MAX_CURRENT_CALL = 10;
    private int idCall = 0;
    public final Semaphore semaphore = new Semaphore(1);
    private List<Employee> employees = new ArrayList<>();
    private int totalCall = 0;
    private int totalThreads = 0;
    private List<Call> acceptCalls = new ArrayList<>();
    private List<Call> suspendCalls = new ArrayList<>();

    /**
     * Asigna las llamadas a los empleados disponibles
     */
    public void attendAllCalls(List<Call> calls) {

	totalCall = calls.size();
	// Por si se se ingresaron en orden, ordenar por rango
	orderEmployees();

	try {
	    dispatchCalls(calls);
	    waitForThreathsFinish();
	    showResult();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Metodo que contesta las llamadas
     * @param calls
     * @throws InterruptedException
     */
    public void dispatchCalls(List<Call> calls) throws InterruptedException {
	if (employees == null || employees.isEmpty()) {
	    LOGGER.info("NO HAY EMPLEADOS PARA ATENDER LLAMADAS, TODAS LAS LLAMADAS FUERON RECHAZADAS.");
	    totalCall = 0;
	    return;
	}
	for (Call call : calls) {
	    dispatchCall(call);
	}

    }

    /**
     * Contestar la llamada si hay empleados y cupo disponible
     * 
     * @param employee
     * @param call
     * @throws InterruptedException
     */
    public void dispatchCall(Call call) throws InterruptedException {

	Employee employee = getEmployeeFree();

	if (employee == null || totalThreads/*threads.size()*/ >= MAX_CURRENT_CALL) {
	    // ESPERAR A SER ATENDIDO

	    suspendCalls.add(call);
	} else {
	    // ACEPTAR LA LLAMADA
	    acceptCalls.add(call);
	    // INSTANCIAR LA ATENCION DE LA LLAMADA
	    employee.setBusy(true);
	    AttendCall attendCall = new AttendCall(idCall++, this, employee, call);
	    totalThreads++;
	    attendCall.start();
	}
    }

    /**
     * Esperar a que todos los threads terminen para finalizar la ejecucion
     * 
     * @throws InterruptedException
     */
    private void waitForThreathsFinish() throws InterruptedException {

	while (totalCall > 0) {

	    Thread.sleep(1 * TimeValue.SECOND_VALUE);
	}
    }

    /**
     * Imprimir el resultado de la ejecucion
     */
    private void showResult() {
	// MOSTRAR EL RESULTADO
	acceptCalls.forEach(call -> {
	    LOGGER.info("La llamada: " + call.getCallName() + ", fue atendida por: " + call.getEmployeeName()
		    + ", duró: " + call.getDurationTime() + " segundos, tiempo de espera: " + call.getWaitTime());
	});

    }

    /**
     * Odernar la lista de empleados por prioridad de llamada Operador -> Supervisor
     * -> Director
     */
    private void orderEmployees() {
	List<Employee> result = new ArrayList<>();

	for (Employee ope : employees) {
	    if (ope.getRange().compare(EmployeeRangeEnum.OPERATOR) == 0) {
		result.add(ope);
	    }
	}
	for (Employee sup : employees) {
	    if (sup.getRange().compare(EmployeeRangeEnum.SUPERVISOR) == 0) {
		result.add(sup);
	    }
	}
	for (Employee dir : employees) {
	    if (dir.getRange().compare(EmployeeRangeEnum.DIRECTOR) == 0) {
		result.add(dir);
	    }
	}

	this.employees = result;
    }

    /**
     * Obtener un empleado Libre
     * 
     * @return
     */
    private Employee getEmployeeFree() {
	Optional<Employee> employeeOptional = this.employees.stream().filter(emp -> !emp.isBusy()).findFirst();
	if (employeeOptional.isPresent()) {
	    return employeeOptional.get();
	}
	return null;
    }

    public void freeThread(AttendCall thread) {

	try {

	    this.semaphore.acquire();
	    totalCall--;
	    totalThreads--;

	    Call call = null;
	    if (!suspendCalls.isEmpty()) {
		call = suspendCalls.get(0);
	    }
	    suspendCalls.remove(call);

	    this.semaphore.release();
	    
	    if (call!=null) {
		call.setWaitTime(thread.getCall().getDurationTime() + thread.getCall().getWaitTime());
		this.dispatchCall(call);
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

    }

    // GETTERS && SETTERS
    public List<Employee> getEmployees() {
	return employees;
    }

    public void setEmployees(List<Employee> employees) {
	this.employees = employees;
    }

    public int getTotalCall() {
	return totalCall;
    }

    public void setTotalCall(int totalCall) {
	this.totalCall = totalCall;
    }

    public List<Call> getAcceptCalls() {
	return acceptCalls;
    }

    public void setAcceptCalls(List<Call> acceptCalls) {
	this.acceptCalls = acceptCalls;
    }

    public List<Call> getSuspendCalls() {
	return suspendCalls;
    }

    public void setSuspendCalls(List<Call> suspendCalls) {
	this.suspendCalls = suspendCalls;
    }

}
