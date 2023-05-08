package com.patagonia.web.enums;

public enum SearchOperation {

    // This are used in get all application
    // Gets application by {status, applicationType, passageType} code
    INNER_OBJECT_CODE,
    INNER_OBJECT_CODE_NOT_EQUAL,
    MATCH_START_DATE,
    MATCH_END_DATE,
    FISCALIZED,
    INNER_OBJECT_CONTAINS, // contains value
    INNER_OBJECT_CUSTOMER, // search by first name/last name/identification nr
    VALIDITY, // check if end date is greater or equal to today date
    EQUAL_DATE,
    RENEW,
    
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN_EQUAL,
    NOT_EQUAL,
    EQUAL,
    MATCH,
    MATCH_START,
    MATCH_END,
    IN,
    NOT_IN
}