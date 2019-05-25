package com.shen.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.shen.model.*;

public class MemberDao {
private JdbcTemplate jdbcTemplate;



public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

public int registerMember(Member m){
	try {
		String query="INSERT INTO `member`(`mname`, `password`, `mtype`, `address`, `mail`, `contactNumber`) VALUES ('"+m.getName()+"','"+m.getPassword()+"', '"+m.getType()+"' , '"+m.getAddress()+"' , '"+m.getMail()+"' , '"+m.getContactNumber()+"')";
		return jdbcTemplate.update(query);
	}
	catch(Exception e) {
		System.out.println("Error: " + e);
		return -99;
	}
}

public int loginValidate(String name, String password) {
	try {
		return this.jdbcTemplate.queryForInt("SELECT count(*) FROM `member` WHERE mname = '"+name+"' AND password = '"+password+"'");
	}
	catch(Exception e) {
		System.out.println(e);
		return -99;
	}
}

public Member getMem(String name) {
	Member mem;
	try {
		int mid = Integer.parseInt((String) this.jdbcTemplate.queryForObject("select mid from member where mname = '"+name+"'", String.class));
		String password = (String) this.jdbcTemplate.queryForObject("select password from member where mname = '"+name+"'", String.class);
		String mtype = (String) this.jdbcTemplate.queryForObject("select mtype from member where mname = '"+name+"'", String.class);
		String address	 = (String) this.jdbcTemplate.queryForObject("select address from member where mname = '"+name+"'", String.class);
		String mail = (String) this.jdbcTemplate.queryForObject("select mail from member where mname = '"+name+"'", String.class);
		int contactNumber = Integer.parseInt((String) this.jdbcTemplate.queryForObject("select contactNumber from member where mname = '"+name+"'", String.class));
		String status = (String) this.jdbcTemplate.queryForObject("select status from member where mname = '"+name+"'", String.class);
		mem = new Member(mid, name, password, mtype, address, mail, contactNumber, status);
	}
	catch(Exception e) {
		System.out.println("Error: " + e);
		mem = null;
	}
	return mem;
}

public int updateEmployee(Member e){
	try {
		String query="update member set mtype='"+e.getType()+"',address='"+e.getAddress()+"', mail='"+e.getMail()+"', contactNumber='"+e.getContactNumber()+"' where mname='"+e.getName()+"' ";
		return jdbcTemplate.update(query);
	}
	catch(Exception z) {
		System.out.println(z);
		return -99;
	}
}

public int deactivate(String name){
	try {
		String query="Delete from member where mname='"+name+"'";
		return jdbcTemplate.update(query);
	}
	catch(Exception z) {
		System.out.println(z);
		return -99;
	}
}
/*
public int deleteEmployee(Member e){
	String query="delete from employee where id='"+e.getId()+"' ";
	return jdbcTemplate.update(query);
}
*/
}
