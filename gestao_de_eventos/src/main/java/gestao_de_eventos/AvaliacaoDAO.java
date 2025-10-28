package gestao_de_eventos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AvaliacaoDAO {

    public void inserir(Avaliacao avaliacao) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(avaliacao);
            tx.commit();
            System.out.println(" Avaliação inserida com sucesso!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println(" Erro ao inserir avaliação: " + e.getMessage());
        }
    }

    public void atualizar(Avaliacao avaliacao) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(avaliacao);
            tx.commit();
            System.out.println(" Avaliação atualizada!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println(" Erro ao atualizar avaliação: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Avaliacao a = session.get(Avaliacao.class, id);
            if (a != null) {
                tx = session.beginTransaction();
                session.remove(a);
                tx.commit();
                System.out.println("  Avaliação eliminada!");
            } else {
                System.out.println(" Avaliação não encontrada!");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Erro ao eliminar avaliação: " + e.getMessage());
        }
    }

    public Avaliacao procurarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Avaliacao.class, id);
        }
    }

    public List<Avaliacao> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Avaliacao", Avaliacao.class).list();
        }
    }
}
