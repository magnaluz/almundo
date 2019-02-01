package com.almundo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.almundo.entity.call.Call;
import com.almundo.entity.employee.Director;
import com.almundo.entity.employee.Employee;
import com.almundo.entity.employee.Operator;
import com.almundo.entity.employee.Supervisor;

/**
 * @author Miguel Vallejo 
 *
 */
public class GenericUtil {

    /**
     * Obetien el valor random de la duracion de la llamada
     * @return
     */
    public static int getCallDuration() {
	int low = 5;
	int high = 11;
	Random r = new Random();
	int result = r.nextInt(high - low) + low;

	return result;
    }

    /**
     * Crea una lista de empleados especificos
     * 
     * @param operators
     * @param supervisors
     * @param directors
     * @return
     */
    public static List<Employee> createEmployees(int operators, int supervisors, int directors) {

	List<Employee> result = new ArrayList<Employee>();
	for (int i = 1; i <= operators; i++) {
	    Employee emp = new Operator("Operador-" + i);
	    result.add(emp);
	}
	for (int i = 1; i <= supervisors; i++) {
	    Employee emp = new Supervisor("Supervisor-" + i);
	    result.add(emp);
	}
	for (int i = 1; i <= directors; i++) {
	    Employee emp = new Director("Director-" + i);
	    result.add(emp);
	}
	return result;
    }

    /**
     * Crea una lista de llamadas
     * 
     * @param calls
     * @return
     */
    public static List<Call> createCalls(int calls) {
	
	List<Call> result = new ArrayList<Call>();
	for (int i = 1; i <= calls; i++) {
	    result.add(new Call("Call-" + i));
	}
	return result;
    }

}
