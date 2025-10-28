package gestao_de_eventos;

import java.util.List;
import java.util.Scanner;

public class TesteNotificacao {

    private static final NotificacaoDAO dao = new NotificacaoDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void menu(String[] args) {
        int opcao;
        do {
            System.out.println("\n GESTÃO DE NOTIFICAÇÕES ");
            System.out.println("1. Criar nova notificação");
            System.out.println("2. Editar notificação");
            System.out.println("3. Pesquisar notificação por ID");
            System.out.println("4. Listar todas as notificações");
            System.out.println("5. Marcar como lida/não lida");
            System.out.println("6. Eliminar notificação");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: inserirNotificacao();
                case 2: editarNotificacao();
                case 3: pesquisarNotificacao();
                case 4: listarNotificacoes();
                case 5: alterarEstado();
                case 6: eliminarNotificacao();
                case 0 : {
                    System.out.println(" A voltar ao menu principal...");
                    return; 
                }
                default: System.out.println(" Opção inválida!");
            }
        } while (opcao != 0);

        HibernateUtil.shutdown();
        sc.close();
    }

    private static void inserirNotificacao() {
        Notificacao n = new Notificacao();

        System.out.print("Título: ");
        n.setTitulo(sc.nextLine());

        System.out.print("Mensagem: ");
        n.setMensagem(sc.nextLine());

        System.out.print("ID do participante: ");
        int idUser = sc.nextInt();
        sc.nextLine();

        Utilizador u = new Utilizador();
        u.setId(idUser);
        n.setParticipante(u);

        dao.inserir(n);
    }

    private static void editarNotificacao() {
        System.out.print("ID da notificação a editar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Notificacao n = dao.procurarPorId(id);
        if (n == null) {
            System.out.println("  Notificação não encontrada!");
            return;
        }

        System.out.print("Novo título (" + n.getTitulo() + "): ");
        String titulo = sc.nextLine();
        if (!titulo.isEmpty()) n.setTitulo(titulo);

        System.out.print("Nova mensagem (" + n.getMensagem() + "): ");
        String msg = sc.nextLine();
        if (!msg.isEmpty()) n.setMensagem(msg);

        dao.atualizar(n);
    }

    private static void pesquisarNotificacao() {
        System.out.print("ID da notificação: ");
        int id = sc.nextInt();
        sc.nextLine();

        Notificacao n = dao.procurarPorId(id);
        if (n == null) {
            System.out.println(" Notificação não encontrada!");
        } else {
            System.out.println("\n  Detalhes da Notificação ");
            System.out.println("ID: " + n.getId());
            System.out.println("Título: " + n.getTitulo());
            System.out.println("Mensagem: " + n.getMensagem());
            System.out.println("Participante ID: " + n.getParticipante().getId());
            System.out.println("Estado: " + n.getEstado());
            System.out.println("Data de envio: " + n.getDataEnvio());
        }
    }

    private static void listarNotificacoes() {
        List<Notificacao> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println(" Nenhuma notificação encontrada!");
        } else {
            System.out.println("\n  Lista de Notificações ");
            for (Notificacao n : lista) {
                System.out.printf("%d | %s | %s | User: %d | %s%n",
                        n.getId(),
                        n.getTitulo(),
                        n.getMensagem(),
                        n.getParticipante().getId(),
                        n.getEstado());
            }
        }
    }

    private static void alterarEstado() {
        System.out.print("ID da notificação: ");
        int id = sc.nextInt();
        sc.nextLine();

        Notificacao n = dao.procurarPorId(id);
        if (n == null) {
            System.out.println(" Notificação não encontrada!");
            return;
        }

        System.out.println("Estado atual: " + n.getEstado());
        System.out.println("1. Marcar como LIDA");
        System.out.println("2. Marcar como NÃO LIDA");
        int opcao = sc.nextInt();
        sc.nextLine();

        if (opcao == 1)
            n.setEstado(Notificacao.EstadoNotificacao.LIDA);
        else
            n.setEstado(Notificacao.EstadoNotificacao.NAO_LIDA);

        dao.atualizar(n);
        System.out.println(" Estado atualizado!");
    }

    private static void eliminarNotificacao() {
        System.out.print("ID da notificação: ");
        int id = sc.nextInt();
        sc.nextLine();

        dao.eliminar(id);
    }
}
