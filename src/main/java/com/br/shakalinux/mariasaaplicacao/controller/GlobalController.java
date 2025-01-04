package com.br.shakalinux.mariasaaplicacao.controller;

import com.br.shakalinux.mariasaaplicacao.dao.ClienteDao;
import com.br.shakalinux.mariasaaplicacao.dao.FuncionarioDao;
import com.br.shakalinux.mariasaaplicacao.dao.ProdutoDao;
import com.br.shakalinux.mariasaaplicacao.model.Area;
import com.br.shakalinux.mariasaaplicacao.model.Cliente;
import com.br.shakalinux.mariasaaplicacao.model.Funcionario;
import com.br.shakalinux.mariasaaplicacao.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GlobalController {
    private final ClienteDao clienteDao;
    private final FuncionarioDao funcionarioDao;
    private final ProdutoDao produtoDao;


    public GlobalController(ClienteDao clienteDao, FuncionarioDao funcionarioDao, ProdutoDao produtoDao) {
        this.clienteDao = clienteDao;
        this.funcionarioDao = funcionarioDao;
        this.produtoDao = produtoDao;
    }


    @GetMapping("/")
    public String listar(Model model) {
        return "home";
    }

    @GetMapping("/cadastrar-cliente")
    public String cadastroCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastrarCliente";
    }

    @PostMapping("/cadastrar-cliente")
    public String cadastrarCliente(Cliente cliente) {
        clienteDao.cadastrarCliente(cliente);
        return "redirect:/";
    }
    

    @GetMapping("/cadastrar-funcionario")
    public String cadastrorFuncionario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "cadastrarFuncionario";
    }

    @PostMapping("/cadastrar-funcionario")
    public String cadastrarFuncionario(Funcionario funcionario, @RequestParam Long idArea) {
        Area area = new Area();
        area.setIdArea(idArea);
        funcionario.setArea(area);
        funcionarioDao.cadastrarFuncionario(funcionario);
        return "redirect:/";
    }

    @GetMapping("/cadastrar-produto")
    public String salvarProduto(Model model) {
        return "cadastrarProduto";
    }

    @PostMapping("/cadastrar-produto")
    public String salvarProduto(Produto produto) {
        produtoDao.cadastrarProduto(produto);
        return "redirect:/";
    }


    @GetMapping("/mostrar-dados")
    public String mostrarDados(Model model){

        try {
            List<Cliente> clientes = clienteDao.listarClientes();
            List<Funcionario> funcionarios = funcionarioDao.listarFuncionarios();
            List<Produto> produtos = produtoDao.listarProdutos();

            model.addAttribute("clientes", clientes);
            model.addAttribute("funcionarios", funcionarios);
            model.addAttribute("produtos", produtos);

        }catch (Exception e){
            e.printStackTrace();

        }
        return "dados";
    }
    @GetMapping("/cliente-editar/{id}")
    public String editarCliente(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteDao.buscarClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "cliente-editar";
    }


    @PostMapping("/cliente-editar/{id}")
    public String editarCliente(@PathVariable("id") Long id ,Cliente cliente) {
        clienteDao.atualizarCliente(id, cliente);
        return "redirect:/mostrar-dados";
    }


    @GetMapping("/cliente-excluir/{id}")
    public String excluirCliente(@PathVariable("id") Long id) {
        clienteDao.excluirCliente(id);
        return "redirect:/mostrar-dados";
    }


    @GetMapping("/funcionario-editar/{id}")
    public String editarFuncionario(@PathVariable("id") Long id, Model model) {
        Funcionario funcionario = funcionarioDao.buscarFuncionarioId(id);
        model.addAttribute("funcionario", funcionario);
        return "funcionario-editar";
    }

    @PostMapping("/funcionario-editar/{id}")
    public String editarFuncionario(@PathVariable("id") Long id, Funcionario funcionario) {
        funcionarioDao.atualizarFuncionario(id, funcionario);
        return "redirect:/mostrar-dados";
    }


    @GetMapping("/funcionario-excluir/{id}")
    public String excluirFuncionario(@PathVariable("id") Long id) {
        funcionarioDao.excluirFuncionario(id);
        return "redirect:/mostrar-dados";
    }


    @GetMapping("/produto-editar/{id}")
    public String editarProduto(@PathVariable("id") Long id, Model model) {
        Produto produto = produtoDao.buscarProdutoId(id);
        if (produto == null) {
            return "redirect:/erro";
        }
        model.addAttribute("produto", produto);
        return "produto-editar";
    }


    @PostMapping("/produto-editar/{id}")
    public String editarProduto(@PathVariable("id") Long id , Produto produto) {
        produtoDao.atualizarProduto(id, produto);
        return "redirect:/mostrar-dados";
    }

    @GetMapping("/produto-excluir/{id}")
    public String excluirProduto(@PathVariable("id") Long id) {
        produtoDao.excluirProduto(id);
        return "redirect:/mostrar-dados";
    }


}
