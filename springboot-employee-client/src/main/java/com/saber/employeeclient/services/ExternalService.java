package com.saber.employeeclient.services;

import com.saber.common.dto.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ExternalService {
    @Autowired
    @Qualifier("loadBalanced")
    private RestTemplate restTemplate;
    private static final String EXCEPTION_ON_CALLING_TRACE = "Exception on calling {} : {}  \n {}";
    private static final String REQUEST_PARAMETER_FOR_CALLING = "Request parameter for calling {} : {} ";

    public <T> ResponseEntity<T> callingService(String url, HttpMethod httpMethod, HttpEntity<?> entity
            , String errorServerMessage, ParameterizedTypeReference<T> responseType) {
        try {
            return restTemplate.exchange(url, httpMethod, entity, responseType);
        } catch (HttpServerErrorException | ResourceAccessException ex) {
            log.info(REQUEST_PARAMETER_FOR_CALLING, url, entity != null ? entity.getBody() : "");
            log.error(EXCEPTION_ON_CALLING_TRACE, url, ex.getMessage(), ex.getStackTrace());
            throw new BusinessException(errorServerMessage, null);
        } catch (HttpClientErrorException ex) {
            log.info(REQUEST_PARAMETER_FOR_CALLING, url, entity != null ? entity.getBody() : "");
            log.error(EXCEPTION_ON_CALLING_TRACE, url, ex.getMessage(), ex.getStackTrace());
            throw new BusinessException(ex.getResponseBodyAsString());
        }
    }
}
