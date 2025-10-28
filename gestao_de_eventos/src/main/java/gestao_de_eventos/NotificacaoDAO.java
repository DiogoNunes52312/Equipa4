package gestao_de_eventos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class NotificacaoDAO {

    public void inserir(Notificacao n) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(n);
            tx.commit();
            System.out.println(" Notificação inserida com sucesso!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println(" Erro ao inserir notificação: " + e.getMessage());
        }
    }

    public void atualizar(Notificacao n) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(n);
            tx.commit();
            System.out.println(" Notificação atualizada!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println(" Erro ao atualizar notificação: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Notificacao n = session.get(Notificacao.class, id);
            if (n != null) {
                tx = session.beginTransaction();
                session.remove(n);
                tx.commit();
                System.out.println(" Notificação eliminada!");
            } else {
                System.out.println(" Notificação não encontrada!");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println(" Erro ao eliminar notificação: " + e.getMessage());
        }
    }

    public Notificacao procurarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Notificacao.class, id);
        }
    }

    public List<Notificacao> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Notificacao", Notificacao.class).list();
        }
    }
}
