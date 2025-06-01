public class Aluno {// o t_elemento da arvore
    private int rgm;
    private String nome;

    //Construtor do objeto
    public Aluno(int rgm, String nome){
        this.rgm = rgm;
        this.nome = nome;
    }

    //metódo get para poder acessar os atributos
    public int getRgm() {
        return rgm;
    }

    public String getNome() {
        return nome;
    }

    //Metodo toString para facilitar a exibição dos dados.

    @Override
    public String toString() {
        return "RGM: " + rgm + ", Nome: " + nome;
    }
}
