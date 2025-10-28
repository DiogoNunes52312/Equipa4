package gestao_de_eventos;

import java.util.List;
import java.util.Scanner;

public class TesteUtilizador {

    private static final UtilizadorDAO dao = new UtilizadorDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n===== GESTÃO DE UTILIZADORES =====");
            System.out.println("1. Inserir novo utilizador");
            System.out.println("2. Editar utilizador existente");
            System.out.println("3. Pesquisar utilizador por email");
            System.out.println("4. Listar todos os utilizadores");
            System.out.println("5. Alterar estado (Ativo/Inativo)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
            case 1: inserirUtilizador(); break;
            case 2: editarUtilizador(); break;
            case 3: pesquisarUtilizador(); break;
            case 4: listarUtilizadores(); break;
            case 5: alterarEstado(); break;
            case 0: System.out.println("A sair..."); break;
            default: System.out.println("❌ Opção inválida!");
        }

        } while (opcao != 0);

        HibernateUtil.shutdown();
        sc.close();
    }

    private static void inserirUtilizador() {
        Utilizador u = new Utilizador();

        System.out.print("Nome: ");
        u.setNomeUtilizador(sc.nextLine());

        System.out.print("Email: ");
        u.setEmail(sc.nextLine());

        System.out.print("Senha: ");
        u.setSenhaUtilizador(sc.nextLine());

        System.out.println("Tipo (1=ADMIN, 2=ORGANIZADOR, 3=PARTICIPANTE): ");
        int tipo = sc.nextInt(); sc.nextLine();
        switch (tipo) {
        case 1:
            u.setTipoUtilizador(Utilizador.TipoUtilizador.ADMIN);
            break;
        case 2:
            u.setTipoUtilizador(Utilizador.TipoUtilizador.ORGANIZADOR);
            break;
        default:
            u.setTipoUtilizador(Utilizador.TipoUtilizador.PARTICIPANTE);
    }


        u.setEstadoUtilizador(Utilizador.EstadoUtilizador.ATIVO);
        dao.inserir(u);
    }

    private static void editarUtilizador() {
        System.out.print("Email do utilizador a editar: ");
        String email = sc.nextLine();

        Utilizador u = dao.procurarPorEmail(email);
        if (u == null) {
            System.out.println("❌ Utilizador não encontrado!");
            return;
        }

        System.out.print("Novo nome (" + u.getNomeUtilizador() + "): ");
        String novoNome = sc.nextLine();
        if (!novoNome.trim().isEmpty()) u.setNomeUtilizador(novoNome);

        System.out.print("Nova senha (" + u.getSenhaUtilizador() + "): ");
        String novaSenha = sc.nextLine();
        if (!novaSenha.trim().isEmpty()) u.setSenhaUtilizador(novaSenha);

        dao.atualizar(u);
    }

    private static void pesquisarUtilizador() {
        System.out.print("Email do utilizador: ");
        String email = sc.nextLine();

        Utilizador u = dao.procurarPorEmail(email);
        if (u == null) {
            System.out.println("❌ Nenhum utilizador encontrado!");
        } else {
            System.out.println("ID: " + u.getId());
            System.out.println("Nome: " + u.getNomeUtilizador());
            System.out.println("Email: " + u.getEmail());
            System.out.println("Tipo: " + u.getTipoUtilizador());
            System.out.println("Estado: " + u.getEstadoUtilizador());
        }
    }

    private static void listarUtilizadores() {
        List<Utilizador> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("⚠️ Nenhum utilizador encontrado!");
        } else {
            System.out.println("\n--- Lista de Utilizadores ---");
            for (Utilizador u : lista) {
                System.out.printf("%d | %-20s | %-25s | %-12s | %-8s%n",
                        u.getId(),
                        u.getNomeUtilizador(),
                        u.getEmail(),
                        u.getTipoUtilizador(),
                        u.getEstadoUtilizador());
            }
        }
    }

    private static void alterarEstado() {
        System.out.print("Email do utilizador: ");
        String email = sc.nextLine();

        Utilizador u = dao.procurarPorEmail(email);
        if (u == null) {
            System.out.println("❌ Utilizador não encontrado!");
            return;
        }

        if (u.getEstadoUtilizador() == Utilizador.EstadoUtilizador.ATIVO) {
            u.setEstadoUtilizador(Utilizador.EstadoUtilizador.INATIVO);
            System.out.println("🔴 Utilizador agora está INATIVO.");
        } else {
            u.setEstadoUtilizador(Utilizador.EstadoUtilizador.ATIVO);
            System.out.println("🟢 Utilizador agora está ATIVO.");
        }

        dao.atualizar(u);
    }
}
