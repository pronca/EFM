package it.eng.care.domain.flow.core.dto;

import java.io.Serializable;
import java.util.List;

public class MonitorSdoPaginDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private List<MonitorSdoXlDTO> data;
	private Integer totalPages;
	private Long count;
	
	public MonitorSdoPaginDTO() {
		// TODO Auto-generated constructor stub
	}

	public List<MonitorSdoXlDTO> getData() {
		return data;
	}

	public void setData(List<MonitorSdoXlDTO> data) {
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
