package com.example.jpastudyjpql.app;

import com.example.jpastudyjpql.domain.Member;
import com.example.jpastudyjpql.domain.Team;

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
        try {
            Member member = new Member();
            member.setAge(30);
            member.setUsername("JM");
            em.persist(member);
            Team team = new Team();
            em.persist(team);
            team.setName("LOTTE");

            member.setTeam(team);
            team.getMembers().add(member);
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
