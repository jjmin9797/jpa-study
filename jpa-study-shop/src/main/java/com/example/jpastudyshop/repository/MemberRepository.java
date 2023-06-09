package com.example.jpastudyshop.repository;

import com.example.jpastudyshop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Repository
public class MemberRepository {

    public void save(Member member) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jm");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            System.out.println("asdasd");
            em.persist(member);
            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    public String proxyTest() {
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
            em.clear();

            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println(findMember.getClass());
            userName = findMember.getName();
            System.out.println(findMember.getClass());
            tx.commit();
            return userName;
        }catch (Exception e) {
            tx.rollback();
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
        emf.close();
        return userName;
    }
}
