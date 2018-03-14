package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.service;

import java.util.List;

public interface BaseService<E,K>  {
	
	void saveOrUpdate(E entity);
	List<E> getAll();
    E get(K id);
    void add(E entity);
    void update(E entity);
    void remove(E entity);
    void saveAll(List<E> entity);
}
