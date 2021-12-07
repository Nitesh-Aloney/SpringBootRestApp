package com.example.SpringBootRestApp.dao;

import com.example.SpringBootRestApp.entity.Employee;
import com.example.SpringBootRestApp.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeDAOImplJPA implements EmployeeDAO{

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public List<Employee> findAll() {

        //  get entity manager using entityManagerFactory
        EntityManager em = emf.createEntityManager();

        //  create query using session
        TypedQuery<Employee> query = em.createQuery("from Employee", Employee.class);

        //  execute the query and collect the result
        List<Employee> employees = query.getResultList();

        //  Return the result
        return employees;

    }

    @Override
    public void save(Employee employee){
        //  get entity manager using entityManagerFactory
        EntityManager em = emf.createEntityManager();

        // save the employee
        Employee savedEmployee = em.merge(employee);
        System.out.println(savedEmployee);
    }

    @Override
    public Employee findById(Integer id) {
        //  get current session using entityManager
        EntityManager em = emf.createEntityManager();

        //  create query using session
        TypedQuery<Employee> query = em.createQuery("from Employee where id="+id, Employee.class);

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

    @Override
    @Transactional
    public void update(Employee employee) {
        //  get entity manager using entityManagerFactory
        EntityManager em = emf.createEntityManager();

        //  begin the transaction
        em.getTransaction().begin();

        // update the employee
        Employee updatedEmployee = em.merge(employee);

        // commit the transaction
        em.getTransaction().commit();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        //  get entity manager using entityManagerFactory
        EntityManager em = emf.createEntityManager();

        //  begin the transaction
        em.getTransaction().begin();

        // get employee with the given Id and delete it
        em.remove(em.find(Employee.class, id));

        // commit the transaction
        em.getTransaction().commit();
    }

    @Override
    @Transactional
    public void delete(Employee employee) {

        //  get entity manager using entityManagerFactory
        EntityManager em = emf.createEntityManager();

        //  begin the transaction
        em.getTransaction().begin();

        // delete the employee, if the employee is detached then attach it before deleting
        em.remove(em.contains(employee) ? employee : em.merge(employee));

        // commit the transaction
        em.getTransaction().commit();
    }
}
