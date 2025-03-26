package com.example.newsService.validation;


import com.example.newsService.web.model.RequestFilter;
import com.example.newsService.web.model.news.NewsFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class RequestFilterValidValidator implements ConstraintValidator<RequestFilterValid, RequestFilter> {


    @Override
    public boolean isValid(RequestFilter value, ConstraintValidatorContext constraintValidatorContext) {

        if(ObjectUtils.allNull(value.getPageNumber(), value.getPageSize())) {
            return false;
        }

        return true;
    }
}
