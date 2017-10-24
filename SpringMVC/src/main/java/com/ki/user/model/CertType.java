package com.ki.user.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("certType")
public class CertType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;		//主键
	private String content; //证件类型
	
	public CertType(){
	}

	public CertType(String content){
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		return "CertType=[ id=" + id +", content=" + content + " ]";
	}
}
