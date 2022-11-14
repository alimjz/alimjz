package com.digipay.product.model.cache;

import com.digipay.product.model.entity.Customer;
import com.digipay.product.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class Cache {
    private CustomerRepository repository;
    public static final Set<String> customerCache = new HashSet<>();

    @Autowired
    private Cache(CustomerRepository repository) {
        this.repository = repository;
        cacheInitializer();
    }

    @PostConstruct
    private  void cacheInitializer() {
        long start = System.currentTimeMillis();
        List<Customer> customerList = this.repository.findAll();
        log.info("Customer Cache Initialization with " + customerList.size() + " Items.");
        for (Customer customer : customerList) {
            customerCache.add(customer.getNationalId());
        }
        long end = System.currentTimeMillis();
        log.info("Customer Cache Initialization finished in " + (end - start) + " millisecond.");
    }


}
