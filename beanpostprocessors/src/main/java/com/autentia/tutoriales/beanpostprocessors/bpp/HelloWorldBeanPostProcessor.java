package com.autentia.tutoriales.beanpostprocessors.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * Hello World BeanPostProcessor
 * @author jreyes
 *
 */
public class HelloWorldBeanPostProcessor implements BeanPostProcessor, Ordered {

	/**
	 * Método que se ejecuta antes de inicializar el bean
	 */
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		
		System.out.println(String.format("Initializating bean %s",beanName));
		return bean;
	}

	/**
	 * Método que se ejecuta después de inicializar el bean
	 */
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		
		System.out.println(String.format("Bean %s initialized successfully", beanName));
		return bean;
	}

	/**
	 * Obtiene la ordenación del beanpostprocessor
	 */
	public int getOrder() {
		return Integer.MAX_VALUE;
	}
	
}
