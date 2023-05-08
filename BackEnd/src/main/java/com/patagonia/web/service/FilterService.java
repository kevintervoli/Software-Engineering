package com.patagonia.web.service;

import com.patagonia.web.entity.POJO.PaginationPOJO;
import com.patagonia.web.entity.ResponseWrapper;
import com.patagonia.web.entity.User;
import com.patagonia.web.entity.UserResponse;
import com.patagonia.web.filter.search.Filters;
import com.patagonia.web.filter.search.SearchCriteria;
import com.patagonia.web.repository.UserRepository;
import com.patagonia.web.search.GeneralSpecification;
import com.patagonia.web.util.LogUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Qualifier("profiscService")
public class FilterService {


    private final LogUtil logUtil;
    private final UserRepository userRepository;


    public FilterService(LogUtil logUtil, UserRepository userRepository) {
        this.logUtil = logUtil;
        this.userRepository = userRepository;
    }


    public <T> ResponseWrapper<T> getData(Filters filters, Class<T> clazz, JpaSpecificationExecutor<T> repository) {

        try {
            PaginationPOJO pagination = filters.getPagination();

            List<SearchCriteria> searchCriteriaList = new ArrayList<>();

            if (filters.getSearchCriteriaList() != null)
                searchCriteriaList = filters.getSearchCriteriaList();

            GeneralSpecification<T> applicationSpecification = new GeneralSpecification<>(searchCriteriaList);

            Pageable paging = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize(),
                    Sort.by(pagination.getSortByProperty()));

            if (pagination.getSortByAsc())
                paging = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize(),
                        Sort.by(pagination.getSortByProperty()).descending());

            Page<T> results = repository.findAll(applicationSpecification, paging);
            List<T> content = results.getContent();
            int totalSize = repository.findAll(applicationSpecification).size();
            logUtil.info("List found: " + clazz.getSimpleName() + ", total size: " + totalSize);
            return new ResponseWrapper<>(true, "List found", content, totalSize);
        } catch (Exception e) {
            logUtil.error("Error fetching list for " + clazz.getSimpleName() + ": " + e.getMessage(), "GET_LIST");
            return new ResponseWrapper<>(false, e.getMessage(), new Error("List not found"));
        }
    }

    public ResponseWrapper<UserResponse> getUserById(Long id) {
        logUtil.info("Fetching user with ID: " + id);
        try {

            User user = userRepository.findById(id).orElseThrow( () -> new Exception("User not found"));
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setName(user.getName());
            userResponse.setAge(user.getAge());
            userResponse.setEmail(user.getEmail());
            userResponse.setAddress(user.getAddress());
            userResponse.setUsername(user.getUsername());
            userResponse.setStatus(user.getStatus());
            userResponse.setCreditScore(user.getCreditScore());
            userResponse.setEnabled(user.isEnabled());
            userResponse.setRole(user.getRole());

            List<UserResponse> list = new ArrayList<>();
            list.add(userResponse);
            logUtil.info("User found with ID: " + id);
            return new ResponseWrapper<>(true, "User found", list, 1);
        } catch (Exception e) {
            logUtil.error("Error fetching user with ID " + id + ": " + e.getMessage(), "GET_USER");
            return new ResponseWrapper<>(false, e.getMessage(), new Error("User not found"));
        }
    }


    public ResponseWrapper<?> getAllUsers(Filters filters, Class<User> userClass, UserRepository userRepository) {

        try {

            List<?> userList = getData(filters, userClass, userRepository).getContent();
            List<UserResponse> userResponseList = new ArrayList<>();

            for (User user : (List<User>) userList) {
                UserResponse userResponse = new UserResponse();
                userResponse.setId(user.getId());
                userResponse.setName(user.getName());
                userResponse.setAge(user.getAge());
                userResponse.setEmail(user.getEmail());
                userResponse.setAddress(user.getAddress());
                userResponse.setUsername(user.getUsername());
                userResponse.setStatus(user.getStatus());
                userResponse.setCreditScore(user.getCreditScore());
                userResponse.setEnabled(user.isEnabled());
                userResponse.setRole(user.getRole());
                userResponseList.add(userResponse);
            }
            int totalSize = getData(filters, userClass, userRepository).getTotalSize();
            logUtil.info("Users found, total size: " + totalSize);
            return new ResponseWrapper<>(true, "Users found", userResponseList, totalSize);
        } catch (Exception e) {
            logUtil.error("Error fetching all users: " + e.getMessage(), "GET_ALL_USERS");
            return new ResponseWrapper<>(false, e.getMessage(), new Error("Users not found"));
        }
    }
}
