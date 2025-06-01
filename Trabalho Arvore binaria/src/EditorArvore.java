public class EditorArvore {
    /*classe responsável por manter uma referência ao Nó raiz da árvore
     e realizar todas as operações do menu(Inserir, remover, pesquisar, etc) */
    private No raiz;

    //construtor
    public EditorArvore() {
        this.raiz = null; //A árvore começa vazia
    }

    public void inserir(Aluno aluno) {
        if (aluno == null) {
            System.out.println("Não é possível inserir um aluno vazio.");
            return;
        }

        /*Chama o método auxiliar inserirRecursivo(), que é recursivo,
         ou seja, chama ele mesmo várias vezes até achar onde inserir.*/
        this.raiz = inserirRecursivo(this.raiz, aluno);
    }

    private No inserirRecursivo(No atual, Aluno alunoInserir) {
        /*Primeiro caso: se o nó atual é Null, então encontramos a posição para inserir o novo Nó.*/
        if (atual == null) {
            return new No(alunoInserir, null, null);
        }

        //Caso o nó atual não for null, iremos descer na árvore
        if ( alunoInserir.getRgm() < atual.getAluno().getRgm() ) {
            //RGM menor, vai para a esquerda
            atual.setEsquerda(inserirRecursivo(atual.getEsquerda(), alunoInserir));
        } else if(alunoInserir.getRgm() > atual.getAluno().getRgm()) {
            //RGM maior, vai para a direita
            atual.setDireita(inserirRecursivo(atual.getDireita(), alunoInserir));
        } else {
            // Tercerio caso: se o rgm ja existir, seguindo as regras da ABB, não poderemos inseri-lo novamente.
            System.out.println("O RGM " + alunoInserir.getRgm() + " já existe na árvore. Aluno não inserido.");
        }
        return atual;
    }
}
