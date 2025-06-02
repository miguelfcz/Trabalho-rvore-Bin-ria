# Trabalho: Árvore Binária de Pesquisa de Alunos

## 📑 Sumário

* [Visão Geral](#visão-geral)
* [✨ Funcionalidades Implementadas](#-funcionalidades-implementadas)
* [🛠️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
* [🧱 Estrutura do Projeto](#-estrutura-do-projeto)
    * [Principais Classes e Suas Responsabilidades](#principais-classes-e-suas-responsabilidades)
        * [`Aluno.java`](#alunojava)
        * [`No.java`](#nojava)
        * [`EditorArvore.java`](#editorarvorejava)
        * [`Main.java`](#mainjava)
    * [Arquivo de Dados](#arquivo-de-dados)
        * [`Alunos.txt`](#alunostxt)
    * [Estrutura de Dados Principal](#estrutura-de-dados-principal)
* [⚙️ Como Executar o Projeto](#️-como-executar-o-projeto)
* [🤔 Decisões de Design e Motivações](#-decisões-de-design-e-motivações)
* [👨‍💻 Autor](#-autor)

---

## 📜 Visão Geral

Este projeto é um trabalho acadêmico para a disciplina de Estrutura de Dados I, focado na implementação e manipulação de uma Árvore Binária de Pesquisa (ABB). O sistema permite gerenciar dados de alunos, utilizando o RGM (Registro Geral do Aluno) como chave principal para as operações na árvore. As operações como inserção, remoção, pesquisa, esvaziamento da árvore e diferentes formas de exibição são contempladas através de um menu interativo no console. O programa também carrega dados iniciais de alunos a partir de um arquivo `Alunos.txt`.

---

## ✨ Funcionalidades Implementadas

O sistema oferece um menu interativo no console com as seguintes opções:

* **Carregamento Inicial:** Ao iniciar, o programa automaticamente carrega os dados dos alunos do arquivo `Alunos.txt`.
* **1 – INSERIR:** Permite adicionar um novo aluno (RGM e Nome) à árvore.
* **2 – REMOVER UM NÓ:** Permite remover um aluno da árvore fornecendo seu RGM.
* **3 – PESQUISAR:** Permite buscar por um aluno na árvore fornecendo seu RGM e informa se o aluno foi encontrado.
* **4 – ESVAZIAR A ÁRVORE:** Remove todos os alunos da árvore.
* **5 – EXIBIR A ÁRVORE:** Oferece três formas de visualização dos dados da árvore: Pré-ordem, Em-ordem (In-order) e Pós-ordem.
* **0 – SAIR:** Encerra a execução do programa.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java
* **Paradigmas:** Programação Orientada a Objetos (POO)
* **Estruturas de Dados:** Árvore Binária de Pesquisa (ABB)
* **IDE (sugerida):** IntelliJ IDEA, Eclipse ou VS Code com extensões Java.
* **Entrada de Dados:** `java.util.Scanner` para interação com o usuário via console.
* **Leitura de Arquivo:** `java.io.BufferedReader` e `java.io.FileReader` para carregar dados de alunos.

---

## 🧱 Estrutura do Projeto

O projeto está organizado em classes que representam os elementos da árvore (alunos), os nós da árvore, a própria árvore com suas operações e a interface com o usuário.

### Principais Classes e Suas Responsabilidades

#### `Aluno.java`

* **Propósito:** Representa o elemento (dado) a ser armazenado em cada nó da árvore. Contém as informações de um aluno.
* **Principais Atributos:**
    * `rgm` (int): Identificador único do aluno, utilizado como chave na árvore binária de pesquisa.
    * `nome` (String): Nome do aluno.
* **Principais Métodos:**
    * `Aluno(int rgm, String nome)`: Construtor para inicializar um objeto Aluno.
    * `getRgm()`: Retorna o RGM do aluno.
    * `getNome()`: Retorna o nome do aluno.
    * `toString()`: Retorna uma representação em String do objeto Aluno (ex: "RGM: 123, Nome: Joao Silva"), facilitando a exibição dos dados.
* **Motivo:** Encapsula os dados do aluno, tornando o código mais organizado e permitindo que a árvore manipule objetos `Aluno` de forma genérica.

#### `No.java`

* **Propósito:** Representa um nó na Árvore Binária de Pesquisa. Cada nó armazena um `Aluno` e possui referências para seus filhos esquerdo e direito.
* **Principais Atributos:**
    * `aluno` (Aluno): O objeto Aluno armazenado neste nó.
    * `esquerda` (No): Referência para o filho à esquerda (nós com RGM menor).
    * `direita` (No): Referência para o filho à direita (nós com RGM maior).
* **Principais Métodos:**
    * `No(Aluno aluno, No esquerda, No direita)`: Construtor que inicializa o nó com um aluno. As referências `esquerda` e `direita` são inicializadas como `null`.
    * `getAluno()`: Retorna o objeto `Aluno` armazenado no nó.
    * `getEsquerda()`: Retorna o nó filho da esquerda.
    * `getDireita()`: Retorna o nó filho da direita.
    * `setAluno(Aluno aluno)`: Permite alterar o aluno armazenado no nó.
    * `setEsquerda(No esquerda)`: Define o filho esquerdo do nó.
    * `setDireita(No direita)`: Define o filho direito do nó.
    * `toString()`: Retorna a representação em String do `Aluno` contido no nó.
* **Motivo:** É a unidade fundamental da árvore. A interconexão desses nós forma a estrutura da árvore binária. Os setters são importantes para as operações de inserção e remoção.

#### `EditorArvore.java`

* **Propósito:** Classe central que gerencia a Árvore Binária de Pesquisa. Mantém a referência para o nó raiz e implementa as operações principais da árvore.
* **Principais Atributos:**
    * `raiz` (No): A raiz da árvore binária. Se `raiz` for `null`, a árvore está vazia.
* **Principais Métodos Implementados:**
    * `EditorArvore()`: Construtor que inicializa a árvore como vazia (`raiz = null`).
    * `inserir(Aluno aluno)`: Método público para iniciar a inserção de um aluno. Chama `inserirRecursivo`.
    * `inserirRecursivo(No atual, Aluno alunoInserir)`: Método privado recursivo para encontrar a posição correta e inserir o novo aluno baseado no RGM, evitando duplicatas.
    * `remover(int rgm)`: Método público para iniciar a remoção de um aluno pelo RGM. Chama `removerRec`.
    * `removerRec(No atual, int rgm)`: Método privado recursivo para localizar e remover o nó com o RGM especificado. Trata casos de nós com zero, um ou dois filhos, utilizando o sucessor (menor da subárvore direita) quando necessário.
    * `encontrarMin(No no)`: Método privado para encontrar o nó com o menor RGM em uma subárvore (usado na remoção).
    * `pesquisar(int rgm)`: Método público para verificar a existência de um aluno pelo RGM. Chama `pesquisarRec`.
    * `pesquisarRec(No atual, int rgm)`: Método privado recursivo para buscar um RGM na árvore. Retorna `true` se encontrado, `false` caso contrário.
    * `esvaziar()`: Esvazia a árvore, removendo todos os nós. Utiliza `esvaziarRecursivo`.
    * `esvaziarRecursivo(No atual)`: Método privado recursivo que remove os nós em Pós-Ordem.
    * `getRaiz()`: Retorna o nó raiz da árvore.
    * `emOrdem(No atual)`, `preOrdem(No atual)`, `posOrdem(No atual)`: Métodos públicos para exibir os alunos da árvore nos respectivos percursos.
    * `carregarArquivo(String nomeArquivo)`: Lê alunos de um arquivo texto (RGM,Nome) e os insere na árvore.
* **Motivo:** Abstrai a complexidade da manipulação da árvore, fornecendo uma interface clara para a classe `Main`.

#### `Main.java`

* **Propósito:** Ponto de entrada do programa. Responsável por apresentar o menu de opções ao usuário, coletar a entrada e chamar os métodos apropriados da classe `EditorArvore`.
* **Principais Métodos:**
    * `main(String[] args)`: Método estático principal que carrega os dados iniciais do arquivo `Alunos.txt` e inicia o loop do menu interativo.
* **Funcionamento:**
    * Utiliza um `Scanner` para ler a opção do usuário.
    * Um loop `while(true)` mantém o menu ativo até que o usuário escolha a opção de sair (0).
    * Um `switch` direciona a execução para a funcionalidade escolhida, invocando os métodos correspondentes em `EditorArvore`.
* **Motivo:** Separa a lógica de interface com o usuário da lógica de manipulação da estrutura de dados (separação de responsabilidades).

### Arquivo de Dados

#### `Alunos.txt`

* **Propósito:** Arquivo de texto utilizado para popular inicialmente a árvore de alunos.
* **Formato:** Cada linha representa um aluno, com o RGM e o Nome separados por vírgula.
    ```
    RGM,Nome
    ```
    Exemplo:
    ```
    101,Maria Silva
    205,Joao Santos Oliveira
    ```
* **Tratamento de Erros:** O método `carregarArquivo` em `EditorArvore.java` possui tratamento para formato de linha inválido, nome vazio e erro na conversão do RGM para número.

### Estrutura de Dados Principal

* **Árvore Binária de Pesquisa (ABB):**
    * **Implementação:** A ABB é implementada através da interação das classes `No` e `EditorArvore`.
    * **Motivo da Escolha:** ABBs são eficientes para busca, inserção e remoção de dados ordenados (pelo RGM do aluno). Em média, essas operações têm complexidade de tempo $O(\log n)$, onde $n$ é o número de nós, assumindo uma árvore razoavelmente balanceada.
    * **Regras de Inserção:**
        1.  O RGM do aluno é a chave de comparação.
        2.  Se o RGM a ser inserido é menor que o RGM do nó atual, o novo nó é inserido na subárvore esquerda.
        3.  Se o RGM a ser inserido é maior que o RGM do nó atual, o novo nó é inserido na subárvore direita.
        4.  RGMs duplicados não são permitidos. Se houver tentativa de inserir um RGM já existente, a inserção é abortada para aquele aluno.

---

## ⚙️ Como Executar o Projeto

1.  **Pré-requisitos:**
    * Java Development Kit (JDK) instalado e configurado no sistema.
2.  **Arquivos:**
    * Certifique-se de que os arquivos `Aluno.java`, `No.java`, `EditorArvore.java`, e `Main.java` estejam no mesmo diretório (por exemplo, dentro de uma pasta `src`).
    * Coloque o arquivo `Alunos.txt` na raiz do projeto (ou seja, no mesmo nível onde a pasta `src` estaria, ou no diretório a partir do qual o programa será executado, pois `Main.java` chama `arvore.carregarArquivo("Alunos.txt")`).
3.  **Compilação:**
    * Abra um terminal ou prompt de comando.
    * Navegue até o diretório onde os arquivos `.java` estão localizados (ou o diretório pai da pasta `src`).
    * Compile os arquivos Java:
        ```bash
        javac src/Aluno.java src/No.java src/EditorArvore.java src/Main.java
        # Ou se estiver dentro da pasta src:
        # javac Aluno.java No.java EditorArvore.java Main.java
        ```
4.  **Execução:**
    * Após a compilação bem-sucedida (que gerará os arquivos `.class`), execute a classe principal:
        ```bash
        java -cp src Main
        # Ou se os .class files estiverem na raiz junto com Alunos.txt:
        # java Main
        ```
    * O menu interativo será exibido no console.

---

## 🤔 Decisões de Design e Motivações

* **Encapsulamento:** A classe `Aluno` encapsula os dados do aluno, e `No` encapsula a estrutura do nó, promovendo um código mais limpo e modular.
* **Separação de Responsabilidades:** `Main.java` cuida da interação com o usuário, enquanto `EditorArvore.java` lida com a lógica da árvore binária. Isso torna o código mais fácil de entender, manter e testar.
* **Recursividade:** As operações de inserção, remoção, pesquisa e esvaziamento na `EditorArvore` são implementadas recursivamente, o que reflete a natureza recursiva das árvores e pode levar a um código mais elegante e conciso para essas operações.
* **Tratamento de Duplicatas:** A árvore não permite RGMs duplicados, conforme uma regra comum para chaves em Árvores Binárias de Pesquisa.
* **Carregamento de Arquivo:** A funcionalidade de carregar alunos de um arquivo (`Alunos.txt`) permite popular a árvore com dados iniciais facilmente, facilitando testes e demonstrações.
* **Feedback ao Usuário:** O sistema fornece mensagens informativas no console para as operações realizadas, como sucesso na inserção/remoção, RGM não encontrado, ou tentativa de inserir RGM duplicado.

---
