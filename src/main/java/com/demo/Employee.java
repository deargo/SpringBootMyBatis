package com.demo;

import java.util.Date;
import java.util.List;

//使用数据库系统自带的数据表，对应scott.emp表
public class Employee {
	private Integer EMPNO;   //员工号
	private String  ENAME;   //员工名
	private String  JOB;     //工种
	private Integer MGR;     //上级
	private Date    HIREDATE;//入职日期
	private double  SAL;     //工资
	private double  COMM;    //奖金
	private Integer DEPTNO;  //部门号


	public String toString()
	{		
		String info = String.format("EMPNO[%d], ENAME[%s], JOB[%s], MGR[%d], HIREDATE[%tF], SAL[%.2f], COMM[%.2f], DEPTNO[%d]", EMPNO, ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO);
		return info;
	}

	public static void Print(List<Employee> empList)
	{
		int count = empList.size();	
		String format = String.format("Employee[%%%dd]: %%s", String.valueOf(count).length());
		String info = String.format("%5s, %7s, %10s, %4s, %10s, %7s, %7s, %s","EMPNO", "ENAME","JOB","MGR","HIREDATE","SAL","COMM","DEPTNO");
		System.out.println(String.format(format, count,info));	
		for(int i=0;i<count;i++){
			Employee emp = empList.get(i);
			info = String.format("%5d, %7s, %10s, %4d, %tF, %7.2f, %7.2f, %d", emp.EMPNO, emp.ENAME,emp.JOB,emp.MGR,emp.HIREDATE,emp.SAL,emp.COMM,emp.DEPTNO);
			System.out.println(String.format(format, i,info));	
		}	
		return;
	}

	public Integer getEMPNO() {
		return EMPNO;
	}

	public void setEMPNO(Integer eMPNO) {
		EMPNO = eMPNO;
	}

	public String getENAME() {
		return ENAME;
	}

	public void setENAME(String eNAME) {
		ENAME = eNAME;
	}

	public String getJOB() {
		return JOB;
	}

	public void setJOB(String jOB) {
		JOB = jOB;
	}

	public Integer getMGR() {
		return MGR;
	}

	public void setMGR(Integer mGR) {
		MGR = mGR;
	}

	public Date getHIREDATE() {
		return HIREDATE;
	}

	public void setHIREDATE(Date hIREDATE) {
		HIREDATE = hIREDATE;
	}

	public double getSAL() {
		return SAL;
	}

	public void setSAL(double sAL) {
		SAL = sAL;
	}

	public double getCOMM() {
		return COMM;
	}

	public void setCOMM(double cOMM) {
		COMM = cOMM;
	}

	public Integer getDEPTNO() {
		return DEPTNO;
	}

	public void setDEPTNO(Integer dEPTNO) {
		DEPTNO = dEPTNO;
	}


}

