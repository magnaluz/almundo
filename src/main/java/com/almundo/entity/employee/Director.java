package com.almundo.entity.employee;

import com.almundo.enums.EmployeeRangeEnum;

/**
 * @author Miguel Vallejo 
 *
 */
public class Director extends Employee {

    // OTHER METHODs
    @Override
    public String getPresentation() {

	StringBuilder sb = new StringBuilder("");
	sb.append("Buenos días, soy el DIRECTOR: ");
	sb.append(this.getEmployeeName());

	return sb.toString();
    }

    // CONSTRUCTORs
    public Director() {
	super.range = EmployeeRangeEnum.DIRECTOR;
    }

    public Director(String name) {
	super.range = EmployeeRangeEnum.DIRECTOR;
	super.setEmployeeName(name);
    }
}
