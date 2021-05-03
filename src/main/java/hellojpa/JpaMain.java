package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        /**
         * 웹 서비스 당 무조건 1개
         * EntityManagerFactory
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        /**
         * 커넥션 별 1개
         * 쓰레드를 공유하지 않음
         * EntityManager
         */
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            /**
             * 데이터 입력
             */

            // 비영속
//            Member member = new Member();
//            member.setName("helloJPA");
//            member.setId(101L);
//
//            // 영속상태
//            System.out.println("*** BEFORE ***");
//            em.persist(member); // DB에 저장하는것이 아니라 영속성 컨텍스트에 저장한다.
//            System.out.println("*** AFTER ***");

//            Member member = em.find(Member.class, 131L);
//            member.setName("change131");

            /**
             * 데이터 조회
             */

            Member findMember = em.find(Member.class, 1L);
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
