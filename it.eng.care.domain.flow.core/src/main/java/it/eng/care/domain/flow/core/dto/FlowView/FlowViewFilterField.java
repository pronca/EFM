package it.eng.care.domain.flow.core.dto.FlowView;

import java.io.Serializable;

public class FlowViewFilterField implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int section;
    private String param;


    public FlowViewFilterField() {
    }


    public int getSection() {
        return section;
    }


    public void setSection(int section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


	public String getParam() {
		return param;
	}


	public void setParam(String param) {
		this.param = param;
	}

}
