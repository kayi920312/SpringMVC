package com.ki.province.model;

import java.io.Serializable;
import java.util.List;

import com.ki.city.model.CityInfo;

//@Alias("provinceInfo")
public class ProvinceInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;					//主键
	private String provinceid;			//省份标识
	private String province;			//省名
	
	private List<CityInfo> cities;		//城市

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public List<CityInfo> getCities() {
		return cities;
	}

	public void setCities(List<CityInfo> cities) {
		this.cities = cities;
	}
	
	
	@Override
	public String toString() {
		return "id="+id+", provinceid="+provinceid+", province="+province+", cities="+cities;
	}
}
