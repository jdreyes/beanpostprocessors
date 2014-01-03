package beanpostprocessors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.autentia.tutoriales.beanpostprocessors.bean.AppCfg;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext-test.xml"})
public class BppTest {

	@Autowired
	ApplicationContext applicationContext;
	
	@Test
	public void testBpp() {
		
		Object bean = applicationContext.getBean("AppCfg");
		AppCfg appCfg = (AppCfg)bean;
		
		System.out.println(String.format("Property #1: %s", appCfg.getCfgProperty1()));
		System.out.println(String.format("Property #2: %s", appCfg.getCfgProperty1()));
	}

}
