package br.com.washcarprime.model.controller.service.impl;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;

import br.com.washcarprime.model.controller.service.IUnidadeFederativaService;
import br.com.washcarprime.model.entities.UnidadeFederativa;
import br.com.washcarprime.model.repository.impl.UnidadeFederativaRepository;

@ManagedBean
public class UnidadeFederativaService  implements IUnidadeFederativaService{

	
	 @ManagedProperty(value="#{entityManager}")
	 private EntityManager entityManager;

    
    private UnidadeFederativa unidadeFederativa = new UnidadeFederativa();
    
    public UnidadeFederativaService() {
    }
    
    public void save() {
        UnidadeFederativaRepository unidadeFederativaRepository = new UnidadeFederativaRepository( entityManager );
        unidadeFederativaRepository.salvar(this.unidadeFederativa);
        this.unidadeFederativa = new UnidadeFederativa();
    }
     
    public void remove(UnidadeFederativa unidadeFederativa) {
    }
     

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
	public UnidadeFederativa getUnidadeFederativa() {
		return unidadeFederativa;
	}

	public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
