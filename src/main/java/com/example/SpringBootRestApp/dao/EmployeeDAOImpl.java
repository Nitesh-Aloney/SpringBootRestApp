package com.example.SpringBootRestApp.dao;

import com.example.SpringBootRestApp.entity.Employee;
import com.example.SpringBootRestApp.exception.EmployeeNotFoundException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public List<Employee> listEmployees() {

        //  get current session using entityManager
        Session session = entityManager.unwrap(Session.class);

        //  create query using session
        Query<Employee> query = session.createQuery("from Employee", Employee.class);

        //  execute the query and collect the result
        List<Employee> employees = query.getResultList();

        //  Return the result
        return employees;

    }

    @Transactional
    @Override
    public void addEmployee(Employee employee){
        //  get current session using entityManager
        Session session = entityManager.unwrap(Session.class);

        //  create query using session
        session.saveOrUpdate(employee);
    }

    @Transactional
    @Override
    public Employee getEmployeeById(Integer id) {
        //  get current session using entityManager
        Session session = entityManager.unwrap(Session.class);

        //  create query using session
        Query<Employee> query = session.createQuery("from Employee where id="+id, Employee.class);

        //  execute the query and collect the result
        Employee employee = null;
        try {
            employee = query.getResultList().get(0);
        }catch (Exception e){
            throw new EmployeeNotFoundException("Employee with the given Id doesn't exist");
        }

        //  Return the result
        return employee;
    }

    @Transactional
    @Override
    public void update(Employee employee) {
        //  get current session using entityManager
        Session session = entityManager.unwrap(Session.class);

        session.update(employee);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        //  get current session using entityManager
        Session session = entityManager.unwrap(Session.class);

        //  remove the Employee using session
        session.remove(getEmployeeById(id));
    }

    @Transactional
    @Override
    public void deleteByEmployee(Employee employee) {
        deleteById(employee.getId());
    }
}
