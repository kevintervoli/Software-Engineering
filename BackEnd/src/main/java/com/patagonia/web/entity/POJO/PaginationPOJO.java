package com.patagonia.web.entity.POJO;

public class PaginationPOJO {

	private int pageSize;
	private int pageNumber;
	private String sortByProperty;
	private Boolean sortByAsc;

	public PaginationPOJO() {
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageNumber - 1 because in java JPA page number starts from 0
	 */
	public int getPageNumber() {
		return pageNumber - 1;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the sortByProperty
	 */
	public String getSortByProperty() {
		return sortByProperty;
	}

	/**
	 * @param sortByProperty the property name of the object that you want to sort
	 *                       by list
	 */
	public void setSortByProperty(String sortByProperty) {
		this.sortByProperty = sortByProperty;
	}

	/**
	 * @return the sortByAsc
	 */
	public Boolean getSortByAsc() {
		return sortByAsc;
	}

	/**
	 * @param sortByAsc the sort by ascending or descending if true sorting will be
	 *                  ascending
	 */
	public void setSortByAsc(Boolean sortByAsc) {
		this.sortByAsc = sortByAsc;
	}

	@Override
	public String toString() {
		return "PaginationPOJO\":{\"pageSize\":" + pageSize + ", \"pageNumber\":" + pageNumber + ", \"sortByProperty\":"
				+ sortByProperty + ", \"sortByAsc\":" + sortByAsc + "\"}";
	}
}
