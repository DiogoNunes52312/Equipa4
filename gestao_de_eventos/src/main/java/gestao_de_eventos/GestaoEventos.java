package gestao_de_eventos;

import java.util.Scanner;

public class GestaoEventos {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n SISTEMA DE GESTÃO DE EVENTOS UPT ");
            System.out.println("1. Gerir Utilizadores");
            System.out.println("2. Gerir Eventos");
            System.out.println("3. Gerir Inscrições");
            System.out.println("4. Gerir Avaliações");
            System.out.println("5. Gerir Notificações");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> Utilizadores();
                case 2 -> Eventos();
                case 3 -> Inscricoes();
                case 4 -> Avaliacoes();
                case 5 -> Notificacoes();
                case 0 -> System.out.println("A encerrar o sistema.");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        HibernateUtil.shutdown();
        sc.close();
    }

 
    private static void Utilizadores() {
        System.out.println("\n  A abrir menu de Utilizadores...");
        TesteUtilizador.menu(new String[]{});
    }

    private static void Eventos() {
        System.out.println("\n  A abrir menu de Eventos...");
        TesteEvento.menu(new String[]{});
    }

    private static void Inscricoes() {
        System.out.println("\n A abrir menu de Inscrições...");
        TesteInscricao.menu(new String[]{});
    }

    private static void Avaliacoes() {
        System.out.println("\n  A abrir menu de Avaliações...");
        TesteAvaliacao.menu(new String[]{});
    }

    private static void Notificacoes() {
        System.out.println("\n  A abrir menu de Notificações...");
        TesteNotificacao.menu(new String[]{});
    }
}
