package com.ars.core.utils;


import java.lang.annotation.Annotation;

import javax.persistence.Entity;

import com.ars.core.bean.User;

public   class TabelNameUtil {
		public  static String getTableName(Class  entity){
			Entity annotation = (Entity) entity.getAnnotation(Entity.class);
			String name = annotation.name();
			System.out.println(name);
			return name;
		}
		public static void main(String[] args) {
			TabelNameUtil.getTableName(User.class);
		}
}
