import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EditorArvore arvore = new EditorArvore();

        do{
            System.out.println("ALUNO: SEU NOME COMPLETO");
            System.out.println("DISCIPLINA: ESTRUTURA DE DADOS I");
            System.out.println("PROFESSOR: WALACE BONFIM\n");

            System.out.println("EDITOR DE ÁRVORE");
            System.out.println("1 – INSERIR – fornecer RGM e Nome");
            System.out.println("2 – REMOVER UM NÓ – fornecer o RGM a remover");
            System.out.println("3 – PESQUISAR – fornecer o RGM a pesquisar");
            System.out.println("4 – ESVAZIAR A ÁRVORE");
            System.out.println("5 – EXIBIR A ÁRVORE – três opções: PRÉ, IN ou PÓS");
            System.out.println("0 – SAIR\n");

            System.out.print("DIGITE SUA OPÇÃO: ");
            int resposta = scanner.nextInt();

            switch(resposta){
                case 0:
                    break;
            }

        }while(true);
    }
}