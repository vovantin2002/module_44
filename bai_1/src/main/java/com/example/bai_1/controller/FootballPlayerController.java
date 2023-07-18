package com.example.bai_1.controller;

import com.example.bai_1.service.IFootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FootballPlayerController {
    @Autowired
    private IFootballPlayerService iFootballPlayerService;

    @GetMapping("")
    public String display(Model model) {
        model.addAttribute("footballPlayerList", iFootballPlayerService.display());
        return "list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        if (iFootballPlayerService.showFootballPlayerEdit(id) == null) {
            model.addAttribute("msg", "Không tìm thấy id. ");
            return "list";
        }
        model.addAttribute("footballPlayerList", iFootballPlayerService.showFootballPlayerEdit(id));
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        if (iFootballPlayerService.showFootballPlayerEdit(id) == null) {
            model.addAttribute("msg", "Không tìm thấy id. ");
            return "list";
        }
        iFootballPlayerService.delete(id);
        model.addAttribute("msg", "Xóa thành công");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable int id, Model model) {
        if (iFootballPlayerService.showFootballPlayerEdit(id) == null) {
            model.addAttribute("msg", "Không tìm thấy id. ");
            return "list";
        }
        model.addAttribute("footballPlayerList", iFootballPlayerService.showFootballPlayerEdit(id));
        return "edit";
    }
}
