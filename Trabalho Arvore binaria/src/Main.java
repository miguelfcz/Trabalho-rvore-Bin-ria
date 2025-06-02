import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EditorArvore arvore = new EditorArvore();


        arvore.carregarArquivo("Alunos.txt");
        while(true){
            System.out.println("\nALUNO: SEU NOME COMPLETO\n" +
                    "DISCIPLINA: ESTRUTURA DE DADOS I\n" +
                    "PROFESSOR: WALACE BONFIM\n" +
                    "               EDITOR DE ÁRVORE\n" +
                    "1 – INSERIR – fornecer RGM e Nome\n" +
                    "2 – REMOVER UM NÓ – fornecer o RGM a remover\n" +
                    "3 – PESQUISAR – fornecer o RGM a pesquisar\n" +
                    "4 – ESVAZIAR A ÁRVORE\n" +
                    "5 – EXIBIR A ÁRVORE – três opções: PRÉ, IN ou PÓS\n" +
                    "0 – SAIR\n" +
                    "               DIGITE SUA OPÇÃO:");

                int resposta = scanner.nextInt();
                scanner.nextLine();

                switch (resposta) {
                    case 0:
                        System.out.println("Encerrando programa...");
                        System.exit(0);
                        break;
                    case 1:
                        try{
                            System.out.println("Digite o RGM do aluno: ");
                            int rgm = scanner.nextInt();
                            scanner.nextLine();

                            System.out.println("\nDigite o nome do aluno: ");
                            String nome = scanner.nextLine();

                            if (nome.trim().isEmpty()) {
                                System.out.println("Nome não pode ser vazio. ");
                            } else{
                                Aluno alunoNovo = new Aluno(rgm, nome.trim());
                                System.out.println("Inserindo aluno...");
                                arvore.inserir(alunoNovo);
                            }
                        } catch (Exception e) {
                                System.out.println("Ocorreu um erro durante a inserção: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("--- OPÇÃO 2: REMOVER NÓ ---");
                        if (arvore.getRaiz() == null) { // Supondo que você tenha um getRaiz() ou um estaVazia()
                            System.out.println("A árvore está vazia. Não há nós para remover.");
                            break;
                        }
                        System.out.print("Digite o RGM do aluno a ser removido: ");
                        int rgmParaRemover = scanner.nextInt();
                        scanner.nextLine(); // Consome a nova linha
                        arvore.remover(rgmParaRemover); // As mensagens já são impressas dentro do método removerRec
                        break;

                    case 3:
                        System.out.println("--- OPÇÃO 3: PESQUISAR ALUNO ---");
                        if (arvore.getRaiz() == null) {
                            System.out.println("A árvore está vazia.");
                            break;
                        }
                        System.out.print("Digite o RGM do aluno a pesquisar: ");
                        int rgmParaPesquisar = scanner.nextInt();
                        scanner.nextLine(); // Consome a nova linha
                        boolean encontrado = arvore.pesquisar(rgmParaPesquisar);
                        if (encontrado) {
                            System.out.println("Aluno com RGM " + rgmParaPesquisar + " está presente na árvore.");
                            // Se quisesse mostrar os dados, o método pesquisar precisaria retornar Aluno,
                            // mas como retorna boolean, essa mensagem é suficiente.
                        } else {
                            System.out.println("Aluno com RGM " + rgmParaPesquisar + " não encontrado na árvore.");
                        }
                        break;

                    case 4:
                        arvore.esvaziar();
                        break;

                    case 5:
                        if (arvore.getRaiz() == null) { //
                            System.out.println("A árvore está vazia. Não há nada para exibir.");
                            break;
                        }

                        System.out.println("\n--- OPÇÃO 5: EXIBIR ÁRVORE ---");
                        System.out.println("ESCOLHA COMO VOCÊ DESEJA EXIBIR:");
                        System.out.println("1 - EM ORDEM");
                        System.out.println("2 - PRÉ ORDEM");
                        System.out.println("3 - PÓS ORDEM");
                        System.out.println("0 - VOLTAR AO MENU PRINCIPAL");
                        System.out.print("Digite sua sub-opção: ");

                        int subOpcao = scanner.nextInt();
                        scanner.nextLine(); // Consome a nova linha

                        switch (subOpcao) {
                            case 1:
                                System.out.println("\nEXIBINDO EM-ORDEM:");
                                arvore.emOrdem(arvore.getRaiz()); //
                                break;
                            case 2:
                                System.out.println("\nEXIBINDO PRÉ-ORDEM:");
                                arvore.preOrdem(arvore.getRaiz()); //
                                break;
                            case 3:
                                System.out.println("\nEXIBINDO PÓS-ORDEM:");
                                arvore.posOrdem(arvore.getRaiz()); //
                                break;
                            case 0:
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Sub-opção inválida.");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
        }
    }
}