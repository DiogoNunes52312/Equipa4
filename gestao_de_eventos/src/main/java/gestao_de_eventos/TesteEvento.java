package gestao_de_eventos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TesteEvento {

    private static final EventoDAO dao = new EventoDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void menu(String[] args) {
        int opcao;

        do {
            System.out.println("\n GESTÃO DE EVENTOS ");
            System.out.println("1. Inserir novo evento");
            System.out.println("2. Editar evento existente");
            System.out.println("3. Pesquisar evento por ID");
            System.out.println("4. Listar todos os eventos");
            System.out.println("5. Alterar estado do evento");
            System.out.println("6. Eliminar evento");
            System.out.println("0. Voltar para o menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1: inserirEvento(); break;
                case 2: editarEvento(); break;
                case 3: pesquisarEvento(); break;
                case 4: listarEventos(); break;
                case 5: alterarEstadoEvento(); break;
                case 0: {
                    System.out.println(" A voltar ao menu principal...");
                    return; 
                }
                default: System.out.println(" Opção inválida!");
            }
        } while (opcao != 0);

        HibernateUtil.shutdown();
        sc.close();
    }

    private static void inserirEvento() {
        Evento e = new Evento();

        System.out.print("Título: ");
        e.setTitulo(sc.nextLine());

        System.out.print("Descrição: ");
        e.setDescricao(sc.nextLine());

        System.out.print("Categoria: ");
        e.setCategoria(sc.nextLine());

        System.out.print("Local: ");
        e.setLocal(sc.nextLine());

        System.out.print("Data início (AAAA-MM-DD HH:MM): ");
        e.setDataInicio(LocalDateTime.parse(sc.nextLine().replace(" ", "T")));

        System.out.print("Data fim (AAAA-MM-DD HH:MM): ");
        e.setDataFim(LocalDateTime.parse(sc.nextLine().replace(" ", "T")));

        System.out.print("Capacidade: ");
        e.setCapacidade(sc.nextInt());
        sc.nextLine();

        e.setEstadoEvento(Evento.EstadoEvento.RASCUNHO);


        dao.inserir(e);
    }


    private static void editarEvento() {
        System.out.print("ID do evento a editar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Evento e = dao.procurarPorId(id);
        if (e == null) {
            System.out.println(" Evento não encontrado!");
            return;
        }

        System.out.print("Novo título (" + e.getTitulo() + "): ");
        String novoTitulo = sc.nextLine();
        if (!novoTitulo.trim().isEmpty()) e.setTitulo(novoTitulo);

        System.out.print("Nova descrição (" + e.getDescricao() + "): ");
        String novaDescricao = sc.nextLine();
        if (!novaDescricao.trim().isEmpty()) e.setDescricao(novaDescricao);

        System.out.print("Nova categoria (" + e.getCategoria() + "): ");
        String novaCategoria = sc.nextLine();
        if (!novaCategoria.trim().isEmpty()) e.setCategoria(novaCategoria);

        System.out.print("Novo local (" + e.getLocal() + "): ");
        String novoLocal = sc.nextLine();
        if (!novoLocal.trim().isEmpty()) e.setLocal(novoLocal);

        dao.atualizar(e);
    }

    private static void pesquisarEvento() {
        System.out.print("ID do evento: ");
        int id = sc.nextInt();
        sc.nextLine();

        Evento e = dao.procurarPorId(id);
        if (e == null) {
            System.out.println(" Nenhum evento encontrado!");
        } else {
            System.out.println("ID: " + e.getId());
            System.out.println("Título: " + e.getTitulo());
            System.out.println("Categoria: " + e.getCategoria());
            System.out.println("Local: " + e.getLocal());
            System.out.println("Estado: " + e.getEstadoEvento());
            System.out.println("Data início: " + e.getDataInicio());
            System.out.println("Data fim: " + e.getDataFim());
        }
    }


    private static void listarEventos() {
        List<Evento> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println(" Nenhum evento encontrado!");
        } else {
            System.out.println("\n Lista de Eventos ");
            for (Evento e : lista) {
                System.out.printf("%d | %-25s | %-15s | %-10s | %-10s%n",
                        e.getId(),
                        e.getTitulo(),
                        e.getCategoria(),
                        e.getLocal(),
                        e.getEstadoEvento());
            }
        }
    }

    private static void alterarEstadoEvento() {
        System.out.print("ID do evento: ");
        int id = sc.nextInt();
        sc.nextLine();

        Evento e = dao.procurarPorId(id);
        if (e == null) {
            System.out.println(" Evento não encontrado!");
            return;
        }

        System.out.println("Estado atual: " + e.getEstadoEvento());
        System.out.println("Escolha novo estado: ");
        System.out.println("1. RASCUNHO");
        System.out.println("2. ATIVO");
        System.out.println("3. FECHADO");
        System.out.println("4. CANCELADO");
        System.out.println("5. CONCLUIDO");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1: e.setEstadoEvento(Evento.EstadoEvento.RASCUNHO); break;
            case 2: e.setEstadoEvento(Evento.EstadoEvento.ATIVO); break;
            case 3: e.setEstadoEvento(Evento.EstadoEvento.FECHADO); break;
            case 4: e.setEstadoEvento(Evento.EstadoEvento.CANCELADO); break;
            case 5: e.setEstadoEvento(Evento.EstadoEvento.CONCLUIDO); break;
            default: System.out.println("❌ Opção inválida!"); return;
        }

        dao.atualizar(e);
        System.out.println(" Estado do evento atualizado!");
    }

    
}
