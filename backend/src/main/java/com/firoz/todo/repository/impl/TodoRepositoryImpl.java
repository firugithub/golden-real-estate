package com.firoz.todo.repository.impl;

import com.firoz.todo.model.Todo;
import com.firoz.todo.repository.TodoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Component
public class TodoRepositoryImpl implements TodoRepository {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public List getTodoDetails() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(Todo.class);
		Root contactRoot = criteria.from(Todo.class);
		criteria.select(contactRoot);
		return session.createQuery(criteria).getResultList();
	}

	public List<Todo> search(Todo todo) {


		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(Todo.class);
		Root contactRoot = criteria.from(Todo.class);
		criteria.select(contactRoot);
		Predicate finalPredicate=null;
		List<Predicate> predicates = new ArrayList<Predicate>();

		Predicate predicateForName = null,predicateForBuilding = null,predicateForAssig = null,predicateForCompleted= null;
		if( todo.getName().length()!=0){
			predicateForName
					= builder.equal(contactRoot.get("name"), todo.getName());
			predicates.add(predicateForName);


		}
		if( todo.getBuilding().length()!=0){
			predicateForBuilding
					= builder.equal(contactRoot.get("building"), todo.getBuilding());
			predicates.add(predicateForBuilding);

		}
		if( todo.getAssigned().length()!=0){
			predicateForAssig
					= builder.equal(contactRoot.get("assigned"), todo.getAssigned());
			predicates.add(predicateForAssig);

		}
		if( todo.isCompleted()){
			predicateForCompleted
					= builder.equal(contactRoot.get("completed"), true);
			predicates.add(predicateForCompleted);

		}else{
			predicateForCompleted
					= builder.equal(contactRoot.get("completed"), false);
			predicates.add(predicateForCompleted);


		}

		criteria.where( predicates.toArray(new Predicate[predicates.size()]));
		List<Todo> items = session.createQuery(criteria).getResultList();
		return items;
	}

}