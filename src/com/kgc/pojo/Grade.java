package com.kgc.pojo;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class Grade implements Serializable{
	private Integer gradeId;
	private String gradeName;
	private Set<Student2> stu;
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Set<Student2> getStu() {
		return stu;
	}
	public void setStu(Set<Student2> stu) {
		this.stu = stu;
	}
	
}
