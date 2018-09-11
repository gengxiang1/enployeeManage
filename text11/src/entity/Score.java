package entity;

import until.Grade;

public class Score {
	private int id;
	private Integer value;
	private Employee emp;
	private Project pro;
	private Grade grade;
 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public  Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Integer  getValue() {
		return value;
	}

	public void setValue(Integer  value) {
		this.value = value;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Project getPro() {
		return pro;
	}

	public void setPro(Project pro) {
		this.pro = pro;
	}

}
