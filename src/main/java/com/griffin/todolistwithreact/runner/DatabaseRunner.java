package com.griffin.todolistwithreact.runner;



import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.griffin.todolistwithreact.entity.Todo;
import com.griffin.todolistwithreact.repository.TodoRepository;

@Component
public class DatabaseRunner implements ApplicationRunner{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private TodoRepository repository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		try(Connection con = dataSource.getConnection()){
			DatabaseMetaData metaData = con.getMetaData();
			System.out.println("URL : " + metaData.getURL());
			System.out.println("Username : " + metaData.getUserName());
			System.out.println("DataSource classname : " + dataSource.getClass().getName());
			
			Todo todo1 = new Todo();
			todo1.setText("할일1");
			todo1.setChecked(true);
			repository.save(todo1);
			
			Todo todo2 = new Todo();
			todo2.setText("할일2");
			todo2.setChecked(false);
			repository.save(todo2);
			
		}
		
	}

}
