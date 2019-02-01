package com.almundo.entity.employee;

import com.almundo.enums.EmployeeRangeEnum;

/**
 * @author Miguel Vallejo 
 *
 */
public class Operator extends Employee {

    // OTHER METHODs
    @Override
    public String getPresentation() {

	StringBuilder sb = new StringBuilder("");
	sb.append("Buenos días, soy el OPERADOR: ");
	sb.append(this.getEmployeeName());

	return sb.toString();
    }
    
    // CONSTRUCTORs
    public Operator() {
	super.range = EmployeeRangeEnum.OPERATOR;
    }

    public Operator(String name) {
	super.range = EmployeeRangeEnum.OPERATOR;
	super.setEmployeeName(name);
    }

}
