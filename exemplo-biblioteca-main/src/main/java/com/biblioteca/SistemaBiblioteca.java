package com.biblioteca;

import java.util.List;
import java.util.Scanner;

import com.biblioteca.controller.LivroController;
import com.biblioteca.model.entity.Livro;
import com.biblioteca.view.LivroView;

public class SistemaBiblioteca {
  private LivroController livroController;
  private LivroView livroView;
  private Scanner scanner;
  public int aluguel;


  public SistemaBiblioteca(LivroController livroController, LivroView livroView, Scanner scanner) {
    this.livroController = livroController;
    this.livroView = livroView;
    this.scanner = scanner;
  }

  public void iniciar() {
    int opcao;
    do {
      mostrarMenu();
      opcao = scanner.nextInt();
      switch (opcao) {
        case 1:
          cadastrarLivro();
          break;
        case 2:
          listarLivros();
          break;
        case 3:
          atualizarLivro();
          break;
        case 4:
          excluirLivro();
          break;
        case 5:
          buscarLivro();
          break;
        case 6:
          alugarLivro();
           break; 
        case 7:
          devolverLivro();
          break;
        case 0:
          livroView.mostrarMensagem("Saindo do sistema...");
          break;
        default:
          livroView.mostrarMensagem("Opção inválida!");
          break;
      }
    } while (opcao != 0);
  }

  private void mostrarMenu() {
    livroView.mostrarMensagem("=== Menu ===");
    livroView.mostrarMensagem("1. Cadastrar Livro");
    livroView.mostrarMensagem("2. Listar Livros");
    livroView.mostrarMensagem("3. Atualizar Livro");
    livroView.mostrarMensagem("4. Excluir Livro");
    livroView.mostrarMensagem("5. Buscar livo");
    livroView.mostrarMensagem("6. Alugar livro");
    livroView.mostrarMensagem("7. Devolver livro");
    livroView.mostrarMensagem("0. Sair");
    livroView.mostrarMensagem("============");
    livroView.mostrarMensagem("Escolha uma opção:");
  }

  private void cadastrarLivro() {
    scanner.nextLine(); // Limpar o buffer do scanner
    livroView.mostrarMensagem("Digite o título do livro:");
    String titulo = scanner.nextLine();
    livroView.mostrarMensagem("Digite o autor do livro:");
    String autor = scanner.nextLine();
    livroView.mostrarMensagem("Digite o número de páginas do livro:");
    int numPaginas = scanner.nextInt();

    Livro novoLivro = new Livro(titulo, autor, numPaginas);
    String retorno = livroController.cadastrarLivro(novoLivro);
    livroView.mostrarMensagem(retorno);
  }

  private void listarLivros() {
    livroView.mostrarMensagem("=== Livros Cadastrados ===");
    List<Livro> livros = livroController.listarLivros();
    livroView.mostrarListaLivros(livros);
    livroView.mostrarMensagem("===========================");
  }

  private void atualizarLivro() {
    livroView.mostrarMensagem("Digite o ID do livro a ser atualizado:");
    int id = scanner.nextInt();
    Livro livro = livroController.buscarLivro(id);
    if (livro != null) {
      scanner.nextLine(); // Limpar o buffer do scanner
      livroView.mostrarMensagem("Digite o novo título do livro:");
      String titulo = scanner.nextLine();
      livro.setTitulo(titulo);
      livroView.mostrarMensagem("Digite o novo autor do livro:");
      String autor = scanner.nextLine();
      livro.setAutor(autor);
      livroView.mostrarMensagem("Digite o novo número de páginas do livro:");
      int numPaginas = scanner.nextInt();
      livro.setNumPaginas(numPaginas);
      String retorno = livroController.atualizarLivro(livro);
      livroView.mostrarMensagem(retorno);
    } else {
      livroView.mostrarMensagem("Livro não encontrado!");
    }
  }

  private void excluirLivro() {
    livroView.mostrarMensagem("Digite o ID do livro a ser excluído:");
    int id = scanner.nextInt();
    String retorno = livroController.excluirLivro(id);
    livroView.mostrarMensagem(retorno);
  }

  private void buscarLivro() {
    livroView.mostrarMensagem("Digite o ID do livro a ser buscado:");
    int id = scanner.nextInt();
    Livro livro = livroController.buscarLivro(id);
    if (livro != null) {
      livroView.mostrarDetalhesLivro(livro);
    } else {
      livroView.mostrarMensagem("Livro não encontrado!");
    }
  }
  
  public void alugarLivro(){
    livroView.mostrarMensagem("Qual digite o ID do livro que deseja alugar: ");
    int id = scanner.nextInt();
    Livro livro = livroController.buscarLivro(id);
    if (livro != null) {
      livroView.mostrarMensagem("Livro encontrado. Deseja alugar? Digite 0- Sim ou 1- Não");
      aluguel = scanner.nextInt();
      if(aluguel == 0){
        livroView.mostrarMensagem("Livro alugado com sucesso.");
      }else{
        livroView.mostrarMensagem("Livro não alugado.");
      }
    } else {
      livroView.mostrarMensagem("Livro não encontrado!");
    }
  }
  private void devolverLivro(){
    livroView.mostrarMensagem("Qual digite o ID do livro que deseja devolver: ");
    int id = scanner.nextInt();
    Livro livro = livroController.buscarLivro(id);
    if (livro != null) {
        livroView.mostrarMensagem("Livrro encontrado.  Deseja devolver? Digite 1- Sim ou 2- Não");
        if(aluguel == 0){
          livroView.mostrarMensagem("Livro devolvido com sucesso.");
        }
        else{
          livroView.mostrarMensagem("Aluguel renovado com sucesso.");
        }
    } else {
      livroView.mostrarMensagem("Livro não foi alugado anteriormente.");
    }
  }
}
