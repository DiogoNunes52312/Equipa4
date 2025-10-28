package gestao_de_eventos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UtilizadorDAO {

    // Inserir utilizador
    public void inserir(Utilizador u) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.persist(u);
            tx.commit();
            System.out.println("✅ Utilizador inserido com sucesso!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Atualizar utilizador
    public void atualizar(Utilizador u) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.merge(u);
            tx.commit();
            System.out.println("✅ Utilizador atualizado com sucesso!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Procurar por email
    public Utilizador procurarPorEmail(String email) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("FROM Utilizador WHERE email = :email", Utilizador.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }

    // Listar todos
    public List<Utilizador> listar() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("FROM Utilizador", Utilizador.class).list();
        }
    }
}
