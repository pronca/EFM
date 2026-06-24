package it.eng.care.domain.flow.core.dto.FlowView;

import java.io.Serializable;
import java.util.Date;

public class FlowViewFilterData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int section;
    private int position;
    private Date dateIn;
    private Date dateOut;


    public FlowViewFilterData() {
    }


    public int getSection() {
        return section;
    }


    public void setSection(int section) {
        this.section = section;
    }


    public int getPosition() {
        return position;
    }


    public void setPosition(int position) {
        this.position = position;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
    
    public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

}
