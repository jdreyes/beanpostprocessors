package com.autentia.tutoriales.beanpostprocessors.bpp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;

import com.autentia.tutoriales.beanpostprocessors.bean.AppCfg;

/**
 * BeanPostProcessor de ejemplo
 * @author jreyes
 *
 */
public class AppCfgBeanPostProcessor implements BeanPostProcessor, Ordered {

	/**
	 * Método que se ejecuta antes de inicializar el bean
	 */
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		
		return bean;
	}

	/**
	 * Método que se ejecuta después de inicializar el bean. 
	 * Comprueba que todas las propiedades de la aplicación han sido rellenadas
	 */
	@SuppressWarnings("serial")
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		

		boolean allPropsConfigured = true;
		
		try {	

			if(bean instanceof AppCfg) {
				
				AppCfg appCfg = (AppCfg)bean;
				
				Method[] methods = appCfg.getClass().getMethods();
				
				for(int i=0; i<methods.length && allPropsConfigured; i++) {
					
					Method method = methods[i];
					
					if(method.getName().startsWith("get")) {
						Object ret = method.invoke(appCfg, new Object[0]);
						
						//Se comprueba que la propiedad no sea nula ni vacía (en caso de ser String)
						if(ret == null || 
						  (ret instanceof String && StringUtils.isEmpty((String)ret))) {
							allPropsConfigured = false;
						}
						
					}
				}
			}
			
			if(!allPropsConfigured) {
				throw new BeansException("Not all props configured") {};
			}
		
		} catch(InvocationTargetException e) {
			throw new BeansException("AppCfg class bad configured") {};
		} catch(IllegalAccessException e) {
			throw new BeansException("AppCfg class bad configured") {};
		}
		
		return bean;
	}

	/**
	 * Obtiene la ordenación del beanpostprocessor
	 */
	public int getOrder() {
		return Integer.MIN_VALUE;
	}
	
}
