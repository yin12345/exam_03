package edu.henu.exam_03.exam_03.entity;

import javax.persistence.*;

import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {
@Id

private Integer id;
private String name;
private String pass;
private String dutycycle;
private String pagecount;
private String time;
private String high;
private String allowed;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public String getDutycycle() {
	return dutycycle;
}
public void setDutycycle(String dutycycle) {
	this.dutycycle = dutycycle;
}
public String getPagecount() {
	return pagecount;
}
public void setPagecount(String pagecount) {
	this.pagecount = pagecount;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getHigh() {
	return high;
}
public void setHigh(String high) {
	this.high = high;
}
public String getAllowed() {
	return allowed;
}
public void setAllowed(String allowed) {
	this.allowed = allowed;
}







}
