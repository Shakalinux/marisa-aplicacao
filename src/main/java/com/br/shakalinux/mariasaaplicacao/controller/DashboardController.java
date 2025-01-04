package com.br.shakalinux.mariasaaplicacao.controller;

import com.br.shakalinux.mariasaaplicacao.dao.DashboardDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class DashboardController {

    private final DashboardDAO dashboardDAO;

    public DashboardController(DashboardDAO dashboardDAO) {
        this.dashboardDAO = dashboardDAO;
    }

    @GetMapping("/relatorios")
    public String showDashboard(Model model) {
        int totalFuncionarios = dashboardDAO.getTotalFuncionarios();
        int totalProdutos = dashboardDAO.getTotalProdutos();
        int totalVendas = dashboardDAO.getTotalVendas();
        Map<String, Object> topFuncionario = dashboardDAO.getTopFuncionario();

        model.addAttribute("totalFuncionarios", totalFuncionarios);
        model.addAttribute("totalProdutos", totalProdutos);
        model.addAttribute("totalVendas", totalVendas);
        model.addAttribute("topFuncionarioNome", topFuncionario.get("nome"));
        model.addAttribute("topFuncionarioVendas", topFuncionario.get("totalVendas"));

        return "relatorio";
    }
}
