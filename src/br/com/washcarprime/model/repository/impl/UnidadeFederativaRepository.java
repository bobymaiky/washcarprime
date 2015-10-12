package br.com.washcarprime.model.repository.impl;


import javax.persistence.EntityManager;

import br.com.washcarprime.model.entities.UnidadeFederativa;
import br.com.washcarprime.model.repository.IUnidadeFederativaRepository;

@SuppressWarnings("serial")
public class UnidadeFederativaRepository  extends EntitiesRepository<UnidadeFederativa> implements IUnidadeFederativaRepository {
    	
	    public UnidadeFederativaRepository(EntityManager entity) {
	        entityManager = entity;
	    } 

}
