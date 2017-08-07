package com.thestore.eam.utils;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 在Spring中配置一些属性时使用，有些属性是要加密的，如用户密码。本类的目的正是应对此问题。
 * Spring 提供的读取properties文件的配置如下：
 * <pre><bean id="propertyConfigurer"  
              class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">  
           <property name="location" value="file:/automarketing_jdbc.properties"/>  
   </bean></pre>
   但是在上述配置中使用的 automarketing_jdbc.properties都是明文的，本类可实现对数据库密码加密配置。
 * @author liuqingzhi
 *
 */
public class DecryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	
	protected String resolvePlaceholder(String placeholder, Properties props) {
		String s = props.getProperty(placeholder);
		
        if(placeholder != null && s != null){
        	if( placeholder.endsWith("jdbc.password")){
        		s = Encrypter.decrypt(s);
        	}
        }

		return s;
	}
}
