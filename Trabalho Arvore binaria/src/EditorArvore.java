import java.io.BufferedReader; //Ler texto linha por linha de forma eficiente, geralmente de arquivos.
import java.io.IOException; //Tratar erros de entrada/saída
import java.io.FileReader; //Abrir arquivos de texto e permitir que sejam lidos caractere por caractere.


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

    //metódo para carregar arquivos
    public void carregarArquivo(String nomeArquivo) {

        /*FileReader vai abrir o arquivo e o bufferedReader vai ler de forma eficiente, linha por linha */
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha; //variável para armazenar cada linha do arquivo
            System.out.println("Carregando dados do arquivo: " + nomeArquivo);

            //loop que lê o arquivo
            while ((linha = br.readLine()) != null) {
                linha = linha.trim(); //remove espaços em branco
                if (linha.isEmpty()) {
                    continue;
                }

                //Tenta encontrar o primeiro espaço para separar RGM do nome
                int primeiroEspacoIndex = linha.indexOf(',');

                //Caso não encontre a vírgula separando RGM do nome
                if(primeiroEspacoIndex == -1) {
                    System.err.println("Formato de linha inválido (sem espaço para separar RGM do nome): " + linha);
                    continue;
                }

                String rgmStr = linha.substring(0, primeiroEspacoIndex); //pega o texto que tiver antes da virgula
                String nome = linha.substring(primeiroEspacoIndex + 1).trim();//pega o texto que estiver após a vírgula

                if(nome.isEmpty()) {
                    System.err.println("Formato de linha inválido (nome vazio): " + linha);
                    continue;
                }

                try {
                    int rgm = Integer.parseInt(rgmStr);
                    Aluno aluno = new Aluno(rgm, nome);
                    this.inserir(aluno); //usa o metódo de inserção já existente
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter RGM para número na linha: " + linha + " (" + e.getMessage() + ")");
                }
            }
            System.out.println("Dados carregados com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro de E/S ao ler o arquivo: " + nomeArquivo + " (" + e.getMessage() + ")");
        }
        catch(Exception e) {
            System.err.println("Ocorreu um erro inesperado durante o carregamento do arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // REMOVER NÓ
    // Começa a remoção de um aluno, usando o RGM como chave
    public void remover(int rgm) {
        raiz = removerRec(raiz, rgm); // Chama o método que vai procurar e remover o nó certo
    }

    // Aqui é onde a gente realmente procura o RGM na árvore, de forma recursiva
    private No removerRec(No atual, int rgm) {
        // Se chegou em um galho vazio, é porque o RGM não está na árvore
        if (atual == null) {
            System.out.println("RGM não encontrado.");
            return null;
        }

        // se o RGM é menor do que o que está aqui, continua procurando pela esquerda
        if (rgm < atual.getAluno().getRgm()) {
            atual.setEsquerda(removerRec(atual.getEsquerda(), rgm));
        }
        // se for maior, procura pela direita
        else if (rgm > atual.getAluno().getRgm()) {
            atual.setDireita(removerRec(atual.getDireita(), rgm));
        }
        // achou o rgm, remove esse nó
        else {
            System.out.println("Removido: " + atual.getAluno());

            // Se o nó só tem o filho da direita (ou nenhum filho)
            if (atual.getEsquerda() == null) return atual.getDireita();

            // Se o nó só tem o filho da esquerda (ou nenhum filho)
            if (atual.getDireita() == null) return atual.getEsquerda();

            // se o nó tem dois filhos  vamos procurar o sucessor (o menor da direita)
            No sucessor = encontrarMin(atual.getDireita());

            // Troca os dados do nó atual pelos dados do sucessor
            atual.setAluno(sucessor.getAluno());

            // Agora removemos o sucessor de onde ele estava originalmente
            atual.setDireita(removerRec(atual.getDireita(), sucessor.getAluno().getRgm()));
        }
        return atual;
    }

    // esse método vai descendo para a esquerda até achar o menor valor (mais à esquerda)
    private No encontrarMin(No no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no;
    }

    // PESQUISA
    // começa uma busca por um RGM na árvore
    public boolean pesquisar(int rgm) {
        return pesquisarRec(raiz, rgm);
    }

    // busca : vai descendo na árvore até achar (ou não) o RGM
    private boolean pesquisarRec(No atual, int rgm) {
        if (atual == null) return false; // Chegou no fim da linha e não achou
        if (rgm == atual.getAluno().getRgm()) return true; // Achou!

        // decide se continua procurando à esquerda ou à direita
        return rgm < atual.getAluno().getRgm()
                ? pesquisarRec(atual.getEsquerda(), rgm)
                : pesquisarRec(atual.getDireita(), rgm);
    }

    //ESVAZIAR
    public void esvaziar() {
        if (this.raiz == null) {
            System.out.println("A árvore já está vazia.");
            return;
        }
        this.raiz = esvaziarRecursivo(this.raiz);
        System.out.println("Árvore esvaziada (nó por nó).");
    }

    // Método recursivo privado para esvaziar a árvore usando percurso em Pós-Ordem
    private No esvaziarRecursivo(No atual) {
        // Caso base: se o nó atual é null, não há nada a fazer nesta subárvore
        if (atual == null) {
            return null;
        }

        // 1. Esvazia a subárvore esquerda
        atual.setEsquerda(esvaziarRecursivo(atual.getEsquerda())); //

        // 2. Esvazia a subárvore direita
        atual.setDireita(esvaziarRecursivo(atual.getDireita())); //

        return null;
    }


    // -----EM ORDEM ----- PRE ORDEM ----- PÓS ORDEM -----

    public No getRaiz(){ //Manda a função emOrdem começar pela RAIZ (Função inicializada no Main)
        return this.raiz;
    }

    // - EM ORDEM ------------------------
    public void emOrdem(No atual){
        if(atual != null) {
            emOrdem(atual.getEsquerda());
            System.out.println(atual);
            emOrdem(atual.getDireita());
        }
    }
    // - PRE ORDEM ------------------------
    public void preOrdem(No atual){
        if(atual != null) {
            System.out.println(atual);
            preOrdem(atual.getEsquerda());
            preOrdem(atual.getDireita());
        }
    }
    // - POS ORDEM -------------------------
    public void posOrdem(No atual){
        if(atual != null) {
            preOrdem(atual.getEsquerda());
            preOrdem(atual.getDireita());
            System.out.println(atual);
        }
    }
}