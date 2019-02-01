package almundo.test.dispatcher;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import com.almundo.dispatcher.Dispatcher;
import com.almundo.entity.call.Call;
import com.almundo.entity.employee.Employee;
import com.almundo.util.GenericUtil;

/**
 * @author Miguel Vallejo
 *
 */
public class DispatcherTest {

    private static final Logger LOGGER = Logger.getLogger(DispatcherTest.class.getName());

    /**
     * 1 llamada 1 empleado (1 operador)
     */
    @Test
    public void testOne() {
	LOGGER.info("+++++++++++++++++++++++++++++++++++++");
	LOGGER.info("Inicio del Test - 1");
	// INICIALIZAR
	Dispatcher disp = new Dispatcher();
	disp.setEmployees(GenericUtil.createEmployees(1, 0, 0));

	List<Call> calls = GenericUtil.createCalls(1);
	try {
	    // EJECUTAR
	    disp.attendAllCalls(calls);
	} catch (Exception e) {
	    LOGGER.info("Se ah producido un error: " + e.getMessage());
	}

	// VALIDAR
	Assert.assertEquals(disp.getAcceptCalls().size(), 1);
	Assert.assertEquals(disp.getSuspendCalls().size(), 0);
	for (Employee emp : disp.getEmployees()) {
	    Assert.assertEquals(emp.getCallCount(), 1);
	}
    }

    /**
     * 10 llamada 10 empleado (5 operadores, 3 supervisores, 2 directores)
     */
    @Test
    public void testTwo() {
	LOGGER.info("+++++++++++++++++++++++++++++++++++++");
	LOGGER.info("Inicio del Test - 2");
	// INICIALIZAR
	Dispatcher disp = new Dispatcher();
	disp.setEmployees(GenericUtil.createEmployees(5, 3, 2));

	List<Call> calls = GenericUtil.createCalls(10);

	try {
	    // EJECUTAR
	    disp.attendAllCalls(calls);
	} catch (Exception e) {
	    LOGGER.info("Se ah producido un error: " + e.getMessage());
	}

	// VALIDAR
	Assert.assertEquals(disp.getAcceptCalls().size(), 10);
	Assert.assertEquals(disp.getSuspendCalls().size(), 0);
	Assert.assertEquals(disp.getTotalCall(), 0);
	for (Employee emp : disp.getEmployees()) {
	    Assert.assertEquals(emp.getCallCount(), 1);
	}
    }

    /**
     * 6 llamada 1 empleado (1 operadores, 0 supervisores, 0 directores)
     */
    @Test
    public void testThree() {
	LOGGER.info("+++++++++++++++++++++++++++++++++++++");
	LOGGER.info("Inicio del Test - 3");
	// INICIALIZAR
	Dispatcher disp = new Dispatcher();
	disp.setEmployees(GenericUtil.createEmployees(1, 0, 0));

	List<Call> calls = GenericUtil.createCalls(6);

	try {
	    // EJECUTAR
	    disp.attendAllCalls(calls);
	} catch (Exception e) {
	    LOGGER.info("Se ah producido un error: " + e.getMessage());
	    e.printStackTrace();
	}

	// VALIDAR
	Assert.assertEquals(disp.getAcceptCalls().size(), 6);
	Assert.assertEquals(disp.getSuspendCalls().size(), 0);
	Assert.assertEquals(disp.getTotalCall(), 0);
	for (Employee emp : disp.getEmployees()) {
	    Assert.assertEquals(emp.getCallCount(), 6);
	}
    }

    /**
     * 5 llamada 0 empleado
     */
    @Test
    public void testFor() {
	LOGGER.info("+++++++++++++++++++++++++++++++++++++");
	LOGGER.info("Inicio del Test - 4");
	// INICIALIZAR
	Dispatcher disp = new Dispatcher();
	disp.setEmployees(GenericUtil.createEmployees(0, 0, 0));

	List<Call> calls = GenericUtil.createCalls(5);

	try {
	    // EJECUTAR
	    disp.attendAllCalls(calls);
	} catch (Exception e) {
	    LOGGER.info("Se ah producido un error: " + e.getMessage());
	}

	// VALIDAR
	Assert.assertEquals(disp.getAcceptCalls().size(), 0);
	Assert.assertEquals(disp.getSuspendCalls().size(), 0);
	Assert.assertEquals(disp.getTotalCall(), 0);
    }

    /**
     *  10 llamada 15 empleado (10 operadores, 3 supervisores, 2 directores)
     */
    @Test
    public void testFive() {
	LOGGER.info("+++++++++++++++++++++++++++++++++++++");
	LOGGER.info("Inicio del Test - 5");
	// INICIALIZAR
	Dispatcher disp = new Dispatcher();
	disp.setEmployees(GenericUtil.createEmployees(10, 3, 2));

	List<Call> calls = GenericUtil.createCalls(10);

	try {
	    // EJECUTAR
	    disp.attendAllCalls(calls);
	} catch (Exception e) {
	    LOGGER.info("Se ah producido un error: " + e.getMessage());
	    e.printStackTrace();
	}

	// VALIDAR
	Assert.assertEquals(disp.getAcceptCalls().size(), 10);
	Assert.assertEquals(disp.getSuspendCalls().size(), 0);
	
	for (Call call : disp.getAcceptCalls()) {
	    Assert.assertTrue( call.getEmployeeName().contains("Operador"));
	}
    }
    
    /**
     * 15 llamada 10 empleado (5 operadores, 3 supervisores, 2 directores)
     */
    @Test
    public void testSix() {
	LOGGER.info("+++++++++++++++++++++++++++++++++++++");
	LOGGER.info("Inicio del Test - 6");
	// INICIALIZAR
	Dispatcher disp = new Dispatcher();
	disp.setEmployees(GenericUtil.createEmployees(5, 3, 2));

	List<Call> calls = GenericUtil.createCalls(15);

	try {
	    // EJECUTAR
	    disp.attendAllCalls(calls);
	} catch (Exception e) {
	    LOGGER.info("Se ah producido un error: " + e.getMessage());
	    e.printStackTrace();
	}

	// VALIDAR
	Assert.assertEquals(disp.getAcceptCalls().size(), 15);
	Assert.assertEquals(disp.getSuspendCalls().size(), 0);
	Assert.assertEquals(disp.getTotalCall(), 0);
	for (Employee emp : disp.getEmployees()) {
	    Assert.assertTrue(emp.getCallCount() >= 1);
	}
	int callWithWaitTime = 0;
	int callWithOutWaitTime = 0;
	for(Call call : disp.getAcceptCalls()) {
	    if(call.getWaitTime() > 0) {
		callWithWaitTime++;
	    }else {
		callWithOutWaitTime++;
	    }
	}
	Assert.assertTrue(callWithOutWaitTime == 10);
	Assert.assertTrue(callWithWaitTime == 5);
    }

}
