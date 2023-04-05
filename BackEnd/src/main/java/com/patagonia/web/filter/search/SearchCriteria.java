package com.patagonia.web.filter.search;


import com.patagonia.web.enums.SearchOperation;

import java.io.Serializable;

/**
 * The Class SearchCriteria.
 */
public class SearchCriteria implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 883331102367018311L;

	/** The key. */
    private String key;

    /** The inner object. */
    private String innerObject;

    /** The operation. */
    private SearchOperation operation;

    /** The value. */
    private Object value;

    /**
     * Instantiates a new search criteria.
     *
     * @param key         the key
     * @param operation   the operation
     * @param value       the value
     * @param innerObject the inner object
     */
    public SearchCriteria(String key, SearchOperation operation, Object value, String innerObject) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.innerObject = innerObject;
    }

//    public SearchCriteria(SpecificationFilterModel specification) {
//        this.key = specification.getKey();
//        this.operation = SearchOperation.valueOf(specification.getOperation());
//        this.value = specification.getValue();
//        this.innerObject = specification.getInnerObject();
//    }

    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key the new key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the operation.
     *
     * @return the operation
     */
    public SearchOperation getOperation() {
        return operation;
    }

    /**
     * Sets the operation.
     *
     * @param operation the new operation
     */
    public void setOperation(SearchOperation operation) {
        this.operation = operation;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Gets the inner object.
     *
     * @return the innerObject
     */
    public String getInnerObject() {
        return innerObject;
    }

    /**
     * Sets the inner object.
     *
     * @param innerObject the innerObject to set
     */
    public void setInnerObject(String innerObject) {
        this.innerObject = innerObject;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "SearchCriteria\":{\"key\":" + key + ", \"innerObject\":" + innerObject + ", \"operation\":" + operation
                + ", \"value\":" + value + "\"}";
    }
}
