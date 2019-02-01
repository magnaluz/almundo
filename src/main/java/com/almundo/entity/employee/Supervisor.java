package com.almundo.entity.employee;

import com.almundo.enums.EmployeeRangeEnum;

/**
 * @author Miguel Vallejo
 *
 */
public class Supervisor extends Employee {

    // OTHER METHODs
    @Override
    public String getPresentation() {

	StringBuilder sb = new StringBuilder("");
	sb.append("Buenos días, soy el SUPERVISOR: ");
	sb.append(this.getEmployeeName());

	return sb.toString();
    }

    // CONSTRUCTORs
    public Supervisor() {
	super.range = EmployeeRangeEnum.SUPERVISOR;
    }

    public Supervisor(String name) {
	super.range = EmployeeRangeEnum.SUPERVISOR;
	super.setEmployeeName(name);
    }

}
