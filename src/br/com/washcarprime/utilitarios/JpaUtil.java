package br.com.washcarprime.utilitarios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("washcarprime");
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
