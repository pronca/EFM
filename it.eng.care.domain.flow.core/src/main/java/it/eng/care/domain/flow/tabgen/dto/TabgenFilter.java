package it.eng.care.domain.flow.tabgen.dto;

import java.io.Serializable;

/**
 *
 */
public class TabgenFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private int limit;
    private int offset;

    private String type;

    private Integer visible;

    private String description;
    private String fetchRule;

    public TabgenFilter() {
        // TODO Auto-generated constructor stub
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFetchRule() {
        return fetchRule;
    }

    public void setFetchRule(String fetchRule) {
        this.fetchRule = fetchRule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
