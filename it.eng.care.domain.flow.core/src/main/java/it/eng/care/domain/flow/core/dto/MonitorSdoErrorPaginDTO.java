package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;
import java.util.List;

public class MonitorSdoErrorPaginDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private List<MonitorSdoXlErrorDTO> data;
	private Integer totalPages;
	private Long count;
	
	public List<MonitorSdoXlErrorDTO> getData() {
		return data;
	}
	public void setData(List<MonitorSdoXlErrorDTO> data) {
		this.data = data;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	

}
