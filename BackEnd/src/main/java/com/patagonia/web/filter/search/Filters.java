package com.patagonia.web.filter.search;



import com.patagonia.web.entity.POJO.PaginationPOJO;

import java.util.List;

public class Filters {

    private List<SearchCriteria> searchCriteriaList;
    private PaginationPOJO pagination;

    public List<SearchCriteria> getSearchCriteriaList() {
        return searchCriteriaList;
    }

    public void setSearchCriteriaList(List<SearchCriteria> searchCriteriaList) {
        this.searchCriteriaList = searchCriteriaList;
    }

    public PaginationPOJO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationPOJO pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return "Filters\":{\"searchCriteriaList\":" + searchCriteriaList + ", \"pagination\":" + pagination
                + "\"}";
    }
}
