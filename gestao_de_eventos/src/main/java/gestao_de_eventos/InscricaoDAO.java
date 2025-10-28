package gestao_de_eventos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class InscricaoDAO {

    public void inserir(Inscricao inscricao) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(inscricao);
            tx.commit();
            System.out.println(" Inscrição inserida com sucesso!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println(" Erro ao inserir inscrição: " + e.getMessage());
        }
    }

    public void atualizar(Inscricao inscricao) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(inscricao);
            tx.commit();
            System.out.println(" Inscrição atualizada!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println(" Erro ao atualizar inscrição: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Inscricao inscricao = session.get(Inscricao.class, id);
            if (inscricao != null) {
                tx = session.beginTransaction();
                session.remove(inscricao);
                tx.commit();
                System.out.println(" Inscrição eliminada!");
            } else {
                System.out.println(" Inscrição não encontrada!");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println(" Erro ao eliminar inscrição: " + e.getMessage());
        }
    }

    public Inscricao procurarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Inscricao.class, id);
        }
    }

    public List<Inscricao> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Inscricao", Inscricao.class).list();
        }
    }
}
