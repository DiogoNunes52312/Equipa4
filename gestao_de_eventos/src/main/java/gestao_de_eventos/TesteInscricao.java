package gestao_de_eventos;

import java.util.List;
import java.util.Scanner;

public class TesteInscricao {

    private static final InscricaoDAO dao = new InscricaoDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void menu(String[] args) {
        int opcao;
        do {
            System.out.println("\n GESTÃO DE INSCRIÇÕES ");
            System.out.println("1. Inserir nova inscrição");
            System.out.println("2. Editar inscrição existente");
            System.out.println("3. Pesquisar inscrição por ID");
            System.out.println("4. Listar todas as inscrições");
            System.out.println("5. Alterar estado da inscrição");
            System.out.println("6. Eliminar inscrição");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: inserirInscricao();
                case 2: editarInscricao();
                case 3: pesquisarInscricao();
                case 4: listarInscricoes();
                case 5: alterarEstadoInscricao();
                case 6: eliminarInscricao();
                case 0: {
                    System.out.println(" A voltar ao menu principal...");
                    return; 
                }
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        HibernateUtil.shutdown();
        sc.close();
    }

    private static void inserirInscricao() {
        Inscricao i = new Inscricao();

        System.out.print("ID do evento: ");
        int idEvento = sc.nextInt();
        sc.nextLine();

        Evento evento = new Evento();
        evento.setId(idEvento);
        i.setEvento(evento);

        System.out.print("ID do participante: ");
        int idUser = sc.nextInt();
        sc.nextLine();

        Utilizador u = new Utilizador();
        u.setId(idUser);
        i.setParticipante(u);

        i.setEstadoInscricao(Inscricao.EstadoInscricao.PENDENTE);

        dao.inserir(i);
    }

    private static void editarInscricao() {
        System.out.print("ID da inscrição: ");
        int id = sc.nextInt();
        sc.nextLine();

        Inscricao i = dao.procurarPorId(id);
        if (i == null) {
            System.out.println("❌ Inscrição não encontrada!");
            return;
        }

        System.out.println("Estado atual: " + i.getEstadoInscricao());
        System.out.println("1. PENDENTE");
        System.out.println("2. CONFIRMADA");
        System.out.println("3. CANCELADA");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> i.setEstadoInscricao(Inscricao.EstadoInscricao.PENDENTE);
            case 2 -> i.setEstadoInscricao(Inscricao.EstadoInscricao.CONFIRMADA);
            case 3 -> i.setEstadoInscricao(Inscricao.EstadoInscricao.CANCELADA);
        }

        dao.atualizar(i);
    }

    private static void pesquisarInscricao() {
        System.out.print("ID da inscrição: ");
        int id = sc.nextInt();
        sc.nextLine();

        Inscricao i = dao.procurarPorId(id);
        if (i == null) {
            System.out.println(" Inscrição não encontrada!");
        } else {
            System.out.println("ID: " + i.getId());
            System.out.println("Evento ID: " + i.getEvento().getId());
            System.out.println("Participante ID: " + i.getParticipante().getId());
            System.out.println("Estado: " + i.getEstadoInscricao());
            System.out.println("Data inscrição: " + i.getDataInscricao());
        }
    }

    private static void listarInscricoes() {
        List<Inscricao> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println(" Nenhuma inscrição encontrada!");
        } else {
            for (Inscricao i : lista) {
                System.out.printf("%d | Evento: %d | User: %d | Estado: %s%n",
                        i.getId(),
                        i.getEvento().getId(),
                        i.getParticipante().getId(),
                        i.getEstadoInscricao());
            }
        }
    }

    private static void alterarEstadoInscricao() {
        System.out.print("ID da inscrição: ");
        int id = sc.nextInt();
        sc.nextLine();

        Inscricao i = dao.procurarPorId(id);
        if (i == null) {
            System.out.println("Inscrição não encontrada!");
            return;
        }

        System.out.println("1. PENDENTE");
        System.out.println("2. CONFIRMADA");
        System.out.println("3. CANCELADA");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> i.setEstadoInscricao(Inscricao.EstadoInscricao.PENDENTE);
            case 2 -> i.setEstadoInscricao(Inscricao.EstadoInscricao.CONFIRMADA);
            case 3 -> i.setEstadoInscricao(Inscricao.EstadoInscricao.CANCELADA);
        }

        dao.atualizar(i);
        System.out.println("Estado atualizado!");
    }

    private static void eliminarInscricao() {
        System.out.print("ID da inscrição: ");
        int id = sc.nextInt();
        sc.nextLine();
        dao.eliminar(id);
    }
}
