package br.com.washcarprime.servlet.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.washcarprime.utilitarios.JpaUtil;

@WebFilter(servletNames = { "Faces Servlet" })
public class ControlerServletFilter implements Filter {
 
    @Override  
    public void destroy() {
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterClain)  throws IOException, ServletException {

    	EntityManager entityManager = JpaUtil.getEntityManager();
        request.setAttribute("entityManager", entityManager);
        entityManager.getTransaction().begin();
        filterClain.doFilter(request, response);
        try {
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ServletException(e.toString());
        
        } finally {
          entityManager.close();
        }
    }
    @Override
    public void init(FilterConfig fc) throws ServletException {
    }
}