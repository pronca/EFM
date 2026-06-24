package it.eng.care.domain.flow.tabgen.dto;

import java.io.Serializable;
import java.util.List;

public class BasePagingLoadResult<D extends Object> implements Serializable {

    private List<D> list;

    private int offset = 0;

    private int totalLength = 0;

    public BasePagingLoadResult(List<D> list) {
        setList(list);
    }

    public BasePagingLoadResult(List<D> data, int offset, int totalLength) {
        this(data);
        this.offset = offset;
        this.totalLength = totalLength;
    }

    public List<D> getList() {
        return list;
    }

    public void setList(List<D> list) {
        this.list = list;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

}
