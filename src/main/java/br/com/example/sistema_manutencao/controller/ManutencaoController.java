package br.com.example.sistema_manutencao.controller;


import br.com.example.sistema_manutencao.model.Manutencao;
import br.com.example.sistema_manutencao.repository.ManutencaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/manutencao")
public class ManutencaoController {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @GetMapping
    public ModelAndView list() {
        return new ModelAndView("list", Map.of("manutencao", manutencaoRepository.findAll()));
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("form", Map.of("manutencao", new Manutencao()));
    }

    @PostMapping("/create")
    public String create(@Valid Manutencao manutencao, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        manutencaoRepository.save(manutencao);
        return "redirect:/manutencao";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        var manutencao = manutencaoRepository.findById(id);
        if (manutencao.isPresent() && manutencao.get().getFinishedAt() == null)
            return new ModelAndView("form", Map.of("manutencao", manutencao.get()));
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid Manutencao manutencao, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        manutencaoRepository.save(manutencao);
        return "redirect:/manutencao";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        var manutencao = manutencaoRepository.findById(id);
        if (manutencao.isPresent())
            return new ModelAndView("delete", Map.of("manutencao", manutencao.get()));
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete/{id}")
    public String delete(Manutencao manutencao) {
        manutencaoRepository.delete(manutencao);
        return "redirect:/manutencao";
    }

    @PostMapping("/finish/{id}")
    public String finish(@PathVariable Long id) {
        var optionalManutencao = manutencaoRepository.findById(id);
        if (optionalManutencao.isPresent() && optionalManutencao.get().getFinishedAt() == null) {
            var manutencao = optionalManutencao.get();
            manutencao.setFinishedAt(LocalDate.now());
            manutencaoRepository.save(manutencao);
            return "redirect:/manutencao";
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
