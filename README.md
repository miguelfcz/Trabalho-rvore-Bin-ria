# Trabalho-Árvore-Binária-Pesquisa

## 📑 Sumário

* [Visão Geral](#Visão-Geral)
* [✨ Funcionalidades](#Funcionalidades)
* [🛠️ Tecnologias Utilizadas](#Tecnologias-Utilizadas)
* [🧱 Estrutura do Projeto](#Estrutura-do-Projeto)
    * [Principais Classes e Suas Responsabilidades](#Principais-Classes-e-Suas-Responsabilidades)
        * [`Aluno.java`](#alunojava)
        * [`No.java`](#nojava)
        * [`EditorArvore.java`](#editorarvorejava)
        * [`Main.java`](#mainjava)
    * [Estruturas de Dados Implementadas/Utilizadas](#Estruturas-de-Dados-ImplementadasUtilizadas)
* [⚙️ Como Executar o Projeto](#Como-Executar-o-Projeto)
* [🤔 Decisões de Design e Motivações](#Decisões-de-Design-e-Motivações)
* [👨‍💻 Autor](#Autor)

---

## 📜 Visão Geral

Este projeto é um trabalho acadêmico para a disciplina de Estrutura de Dados I, focado na implementação e manipulação de uma Árvore Binária de Pesquisa (ABB). O sistema permite gerenciar dados de alunos, utilizando o RGM (Registro Geral do Aluno) como chave principal para as operações na árvore. As operações básicas como inserção, remoção, pesquisa e exibição da árvore são contempladas através de um menu interativo no console.

---

## ✨ Funcionalidades

O sistema oferece um menu com as seguintes opções:

* **1 – INSERIR:** Permite adicionar um novo aluno (RGM e Nome) à árvore.
* **2 – REMOVER UM NÓ:** Permite remover um aluno da árvore fornecendo seu RGM. (Funcionalidade planejada, implementação futura)
* **3 – PESQUISAR:** Permite buscar por um aluno na árvore fornecendo seu RGM. (Funcionalidade planejada, implementação futura)
* **4 – ESVAZIAR A ÁRVORE:** Remove todos os alunos da árvore. (Funcionalidade planejada, implementação futura)
* **5 – EXIBIR A ÁRVORE:** Oferece três formas de visualização dos dados da árvore: Pré-ordem, Em-ordem (In-order) e Pós-ordem. (Funcionalidade planejada, implementação futura)
* **0 – SAIR:** Encerra a execução do programa.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java
* **Paradigmas:** Programação Orientada a Objetos (POO)
* **Estruturas de Dados:** Árvore Binária de Pesquisa (ABB)
* **IDE (sugerida):** IntelliJ IDEA, Eclipse ou VS Code com extensões Java.
* **Entrada de Dados:** `java.util.Scanner` para interação com o usuário via console.

---

## 🧱 Estrutura do Projeto

O projeto está organizado em classes que representam os elementos da árvore, a própria árvore e a interface com o usuário.

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
    * `toString()`: Retorna uma representação em String do objeto Aluno, facilitando a exibição dos dados.
* **Motivo:** Esta classe encapsula os dados do aluno, tornando o código mais organizado e permitindo que a árvore manipule objetos `Aluno` de forma genérica, sem precisar conhecer os detalhes internos de sua representação, apenas que possui um RGM para ordenação.

#### `No.java`

* **Propósito:** Representa um nó na Árvore Binária de Pesquisa. Cada nó armazena um `Aluno` e possui referências para seus filhos esquerdo e direito.
* **Principais Atributos:**
    * `aluno` (Aluno): O objeto Aluno armazenado neste nó.
    * `esquerda` (No): Referência para o filho à esquerda (nós com RGM menor).
    * `direita` (No): Referência para o filho à direita (nós com RGM maior).
* **Principais Métodos:**
    * `No(Aluno aluno, No esquerda, No direita)`: Construtor que inicializa o nó com um aluno e, opcionalmente, seus filhos. Na criação inicial de um nó folha, `esquerda` e `direita` são geralmente `null`.
    * `getAluno()`: Retorna o objeto `Aluno` armazenado no nó.
    * `getEsquerda()`: Retorna o nó filho da esquerda.
    * `getDireita()`: Retorna o nó filho da direita.
    * `setAluno(Aluno aluno)`: Permite alterar o aluno armazenado no nó.
    * `setEsquerda(No esquerda)`: Define o filho esquerdo do nó.
    * `setDireita(No direita)`: Define o filho direito do nó.
* **Motivo:** É a unidade fundamental da árvore. A interconexão desses nós (através das referências `esquerda` e `direita`) forma a estrutura da árvore binária. Os setters são importantes para as operações de inserção e remoção, permitindo a reorganização da árvore.

#### `EditorArvore.java`

* **Propósito:** Classe central que gerencia a Árvore Binária de Pesquisa. Ela mantém a referência para o nó raiz e implementa as operações principais da árvore, como inserir, remover e pesquisar.
* **Principais Atributos:**
    * `raiz` (No): A raiz da árvore binária. Se `raiz` for `null`, a árvore está vazia.
* **Principais Métodos (Implementados/Planejados):**
    * `EditorArvore()`: Construtor que inicializa a árvore como vazia (`raiz = null`).
    * `inserir(Aluno aluno)`: Método público para iniciar a inserção de um aluno na árvore. Ele chama um método recursivo auxiliar.
    * `inserirRecursivo(No atual, Aluno alunoInserir)`: Método privado e recursivo que navega pela árvore para encontrar a posição correta de inserção do novo aluno, baseado no RGM.
        * Se o RGM a ser inserido for menor que o RGM do nó atual, continua a busca na subárvore esquerda.
        * Se for maior, continua na subárvore direita.
        * Se for igual, o aluno não é inserido para evitar duplicatas, conforme a regra da ABB.
        * Se o nó atual for `null`, um novo `No` é criado e retornado, efetivamente inserindo o aluno.
    * Outros métodos (remover, pesquisar, esvaziar, exibir) estão planejados conforme o menu em `Main.java`.
* **Motivo:** Esta classe abstrai a complexidade da manipulação da árvore. O usuário da classe (neste caso, a `Main`) interage com métodos de alto nível (`inserir`, etc.) sem precisar se preocupar com os detalhes da navegação recursiva entre os nós.

#### `Main.java`

* **Propósito:** Ponto de entrada do programa. Responsável por apresentar o menu de opções ao usuário, coletar a entrada e chamar os métodos apropriados da classe `EditorArvore` para realizar as operações solicitadas.
* **Principais Métodos:**
    * `main(String[] args)`: Método estático principal que inicia o loop do menu interativo.
* **Funcionamento:**
    * Utiliza um `Scanner` para ler a opção do usuário.
    * Um loop `do-while` mantém o menu ativo até que o usuário escolha a opção de sair (0).
    * Um `switch` direciona a execução para a funcionalidade escolhida. (Atualmente, apenas o `case 0` para sair está funcional, as demais opções precisam da implementação dos métodos correspondentes em `EditorArvore.java`).
* **Motivo:** Separa a lógica de interface com o usuário da lógica de manipulação da estrutura de dados, o que é uma boa prática de design (separação de responsabilidades).

### Estruturas de Dados Implementadas/Utilizadas

* **Árvore Binária de Pesquisa (ABB):**
    * **Implementação:** A ABB é implementada através da interação das classes `No` (que define a estrutura de cada elemento da árvore e suas conexões) e `EditorArvore` (que gerencia a árvore como um todo, começando pela `raiz`, e contém a lógica para as operações).
    * **Motivo da Escolha:** ABBs são eficientes para busca, inserção e remoção de dados ordenados (neste caso, pelo RGM do aluno). Em média, essas operações têm complexidade de tempo $O(\log n)$, onde $n$ é o número de nós na árvore, desde que a árvore esteja balanceada. Para este trabalho, o foco está na implementação da estrutura básica e suas operações.
    * **Regras de Inserção:**
        1.  O RGM do aluno é a chave de comparação.
        2.  Se o RGM a ser inserido é menor que o RGM do nó atual, o novo nó deve ser inserido na subárvore esquerda.
        3.  Se o RGM a ser inserido é maior que o RGM do nó atual, o novo nó deve ser inserido na subárvore direita.
        4.  RGMs duplicados não são permitidos na árvore. Se uma tentativa de inserir um RGM já existente ocorrer, a inserção é abortada para aquele aluno.

---
