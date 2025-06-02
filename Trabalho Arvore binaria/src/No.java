public class No {
    private Aluno aluno;
    private No esquerda; //filho esquerda
    private No direita; //filho direita

    //Construtor do objeto
    public No(Aluno aluno, No esquerda, No direita) {
        this.aluno = aluno;
        this.esquerda = null; //o valor é null porque ao criar um no, ele não tem filhos inicialmente
        this.direita = null;
    }

    //metódo get para acessar os atributos
    public Aluno getAluno() {
        return aluno;
    }

    public No getEsquerda(){
        return esquerda;
    }

    public No getDireita(){
        return direita;
    }

    /*Setter vai permitir alterarmos o valor privado do No,
    com isso será possivel manipular e realocar o No na melhor posição*/
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(No direita){
        this.direita = direita;
    }

    @Override
    public String toString() {
        return
                " " + aluno + " ";
    }
}
