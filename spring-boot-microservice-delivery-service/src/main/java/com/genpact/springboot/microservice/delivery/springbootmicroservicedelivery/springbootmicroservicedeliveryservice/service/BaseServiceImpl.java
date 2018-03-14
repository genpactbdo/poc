package com.genpact.springboot.microservice.delivery.springbootmicroservicedelivery.springbootmicroservicedeliveryservice.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service("baseService")
public abstract class BaseServiceImpl<E, K> implements BaseService<E,K> {

	private JpaRepository<E,K> jpaRepository;
	
	public BaseServiceImpl(JpaRepository<E,K> jpaRespository) {
		this.jpaRepository = jpaRespository;
	}
	
	public BaseServiceImpl() {
		
	}
	
	@Override
	public void add(E entity) {
		// TODO Auto-generated method stub
		jpaRepository.save(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E get(K id) {
		// TODO Auto-generated method stub
		return (E) jpaRepository.findById(id);
	}
	
	@Override
	public List<E> getAll() {
		// TODO Auto-generated method stub
		return jpaRepository.findAll();
	}
	
	@Override
	public void remove(E entity) {
		// TODO Auto-generated method stub
		jpaRepository.delete(entity);
	}
	
	@Override
	public void saveOrUpdate(E entity) {
		// TODO Auto-generated method stub
		jpaRepository.save(entity);
	}
	
	@Override
	public void update(E entity) {
		// TODO Auto-generated method stub
		jpaRepository.save(entity);
	}
	
	@Override
	public void saveAll(List<E> entity) {
		jpaRepository.saveAll(entity);
	}
	
}
