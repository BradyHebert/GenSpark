package org.genspark;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class App 
{
    public static void main( String[] args )
    {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Student stu = (Student)context.getBean("student");

        System.out.print(stu);

    }
}
