package gestao_de_eventos;

import java.util.List;
import java.util.Scanner;

public class TesteAvaliacao {

    private static final AvaliacaoDAO dao = new AvaliacaoDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void menu (String[] args) {
        int opcao;
        do {
            System.out.println("\n GESTÃO DE AVALIAÇÕES ");
            System.out.println("1. Inserir nova avaliação");
            System.out.println("2. Editar avaliação");
            System.out.println("3. Pesquisar avaliação por ID");
            System.out.println("4. Listar todas as avaliações");
            System.out.println("5. Eliminar avaliação");
            System.out.println("0. Voltar para o menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: inserirAvaliacao();
                case 2: editarAvaliacao();
                case 3: pesquisarAvaliacao();
                case 4: listarAvaliacoes();
                case 5: eliminarAvaliacao();
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

    private static void inserirAvaliacao() {
        Avaliacao a = new Avaliacao();

        System.out.print("ID do evento: ");
        int idEvento = sc.nextInt();
        sc.nextLine();
        Evento e = new Evento();
        e.setId(idEvento);
        a.setEvento(e);

        System.out.print("ID do participante: ");
        int idUser = sc.nextInt();
        sc.nextLine();
        Utilizador u = new Utilizador();
        u.setId(idUser);
        a.setParticipante(u);

        System.out.print("Pontuação (1-5): ");
        a.setPontuacao(sc.nextInt());
        sc.nextLine();

        System.out.print("Comentário: ");
        a.setComentario(sc.nextLine());

        dao.inserir(a);
    }

    private static void editarAvaliacao() {
        System.out.print("ID da avaliação: ");
        int id = sc.nextInt();
        sc.nextLine();

        Avaliacao a = dao.procurarPorId(id);
        if (a == null) {
            System.out.println(" Avaliação não encontrada!");
            return;
        }

        System.out.print("Nova pontuação (" + a.getPontuacao() + "): ");
        String novaPont = sc.nextLine();
        if (!novaPont.isEmpty()) a.setPontuacao(Integer.parseInt(novaPont));

        System.out.print("Novo comentário (" + a.getComentario() + "): ");
        String novoComent = sc.nextLine();
        if (!novoComent.isEmpty()) a.setComentario(novoComent);

        dao.atualizar(a);
    }

    private static void pesquisarAvaliacao() {
        System.out.print("ID da avaliação: ");
        int id = sc.nextInt();
        sc.nextLine();

        Avaliacao a = dao.procurarPorId(id);
        if (a == null) {
            System.out.println(" Avaliação não encontrada!");
        } else {
            System.out.println("ID: " + a.getId());
            System.out.println("Evento ID: " + a.getEvento().getId());
            System.out.println("Participante ID: " + a.getParticipante().getId());
            System.out.println("Pontuação: " + a.getPontuacao());
            System.out.println("Comentário: " + a.getComentario());
            System.out.println("Data: " + a.getDataAvaliacao());
        }
    }

    private static void listarAvaliacoes() {
        List<Avaliacao> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println(" Nenhuma avaliação encontrada!");
        } else {
            for (Avaliacao a : lista) {
                System.out.printf("%d | Evento: %d | User: %d | %d★ | %s%n",
                        a.getId(),
                        a.getEvento().getId(),
                        a.getParticipante().getId(),
                        a.getPontuacao(),
                        a.getComentario());
            }
        }
    }

    private static void eliminarAvaliacao() {
        System.out.print("ID da avaliação a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        dao.eliminar(id);
    }
}
