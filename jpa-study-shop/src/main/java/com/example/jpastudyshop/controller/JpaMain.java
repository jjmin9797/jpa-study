package com.example.jpastudyshop.controller;



import com.example.jpastudyshop.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jm");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        System.out.println("In Proxy Test");
        String userName = "";
        try {
            Member member = new Member();
            member.setName("JM");
            em.persist(member);
            System.out.println("In Proxy Try Test");
            em.flush();


            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println(findMember.getClass());
            userName = findMember.getName();
            String city = findMember.getCity();
            System.out.println(city);
            System.out.println(userName);
            System.out.println(findMember.getClass());
            tx.commit();

        }catch (Exception e) {
            tx.rollback();
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
        emf.close();
    }
}
