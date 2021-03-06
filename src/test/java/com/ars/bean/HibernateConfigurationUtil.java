package com.ars.bean;

import java.util.Iterator;

import javax.persistence.Column;

import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.Table;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.ars.core.bean.User;



public class HibernateConfigurationUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;  
  
    private static Configuration configuration;  
  
    public static Configuration getConfiguration() {  
  
        if (configuration == null) {   
            // 取sessionFactory的时候要加上&  
            LocalSessionFactoryBean factory = (LocalSessionFactoryBean) applicationContext  
                    .getBean("&sessionFactory");  
            configuration = factory.getConfiguration();  
        }  
  
        return configuration;  
    }  
  
    private static <T> PersistentClass getPersistentClass(Class<T> clazz) {  
        synchronized (HibernateConfigurationUtil.class) {  
            PersistentClass pc = getConfiguration().getClassMapping(  
                    clazz.getSimpleName());  
            if (pc == null) {  
                configuration = configuration.addClass(clazz);  
                pc = configuration.getClassMapping(clazz.getName());  
            }  
            return pc;  
        }  
    }  
  
    /** 
     * 获得实体类对应的表名 
     *  
     * @param clazz 
     *            实体类的Class对象 
     * @return 表名 
     */  
    public static <T> String getTableName(Class<T> clazz) {  
        return getPersistentClass(clazz).getTable().getName();  
    }  
  
    /** 
     * 获得实体类对应表的主键字段名称 
     *  
     * @param clazz 
     *            实体类的Class对象 
     * @return 主键字段名称 
     */  
    public static <T> String getPKColumnName(Class<T> clazz) {  
        return getPersistentClass(clazz).getTable().getPrimaryKey()  
                .getColumn(0).getName();  
    }  
  
    /** 
     * 获得类属性对应的字段名 
     *  
     * @param clazz 
     *            实体类的Class对象 
     * @param propertyName 
     *            实体类的属性名 
     * @return 属性对应的字段名 
     */  
    public static <T> String getColumnName(Class<T> clazz, String propertyName) {  
        String columnName = "";  
        PersistentClass persistentClass = getPersistentClass(clazz);  
        Property property = persistentClass.getProperty(propertyName);  
        Iterator<?> iterator = property.getColumnIterator();  
        if (iterator.hasNext()) {  
            Column column = (Column) iterator.next();  
            columnName += ((Table) column).getName();  
        }  
        return columnName;  
    }  
  
    public void setApplicationContext(ApplicationContext context)  
            throws BeansException {  
        applicationContext = context;  
    }  
    
    
    public static void main(String[] args) {
    	HibernateConfigurationUtil h=new HibernateConfigurationUtil();
		String name = h.getTableName(User.class);
		System.out.println(name);
	}
}
