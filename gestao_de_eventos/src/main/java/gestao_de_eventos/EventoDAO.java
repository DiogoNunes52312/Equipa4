package gestao_de_eventos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EventoDAO {

    public void inserir(Evento evento) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(evento);
            tx.commit();
            System.out.println("✅ Evento inserido com sucesso!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Erro ao inserir evento: " + e.getMessage());
        }
    }

    public void atualizar(Evento evento) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(evento);
            tx.commit();
            System.out.println("✅ Evento atualizado com sucesso!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Erro ao atualizar evento: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Evento evento = session.get(Evento.class, id);
            if (evento != null) {
                tx = session.beginTransaction();
                session.remove(evento);
                tx.commit();
                System.out.println("🗑️ Evento eliminado com sucesso!");
            } else {
                System.out.println("⚠️ Evento não encontrado!");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Erro ao eliminar evento: " + e.getMessage());
        }
    }

    public Evento procurarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Evento.class, id);
        }
    }

    public List<Evento> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Evento", Evento.class).list();
        }
    }
}
