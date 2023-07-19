package com.example.bai_1.controller;

import com.example.bai_1.model.FootballPlayer;
import com.example.bai_1.model.FootballPlayerDto;
import com.example.bai_1.service.IFootballPlayerService;
import com.example.bai_1.service.ITeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class FootballPlayerController {
    @Autowired
    private IFootballPlayerService iFootballPlayerService;
    @Autowired
    private ITeamService iTeamService;

    @GetMapping("")
    public String display(@PageableDefault(size = 2, sort = "name") Pageable pageable, Model model) {
//        Sort sort = pageable.getSort().and(Sort.by("birthday").descending());
        model.addAttribute("footballPlayerList", iFootballPlayerService.display(pageable));
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

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        FootballPlayerDto footballPlayerDto = new FootballPlayerDto();
        model.addAttribute("teams", iTeamService.display());
        model.addAttribute("footballPlayer", footballPlayerDto);
        return "create";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        if (iFootballPlayerService.showFootballPlayerEdit(id) == null) {
            redirectAttributes.addAttribute("msg", "Không tìm thấy id. ");
            return "list";
        }
        iFootballPlayerService.delete(id);
        redirectAttributes.addAttribute("msg", "Xóa thành công");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable int id, Model model) {
        if (iFootballPlayerService.showFootballPlayerEdit(id) == null) {
            model.addAttribute("msg", "Không tìm thấy id. ");
            return "list";
        }
        model.addAttribute("teams", iTeamService.display());
        FootballPlayer footballPlayer = iFootballPlayerService.showFootballPlayerEdit(id);
        model.addAttribute("footballPlayer", footballPlayer);
        return "edit";
    }

    @PostMapping("create")
    public String create(@Valid @ModelAttribute("footballPlayer") FootballPlayerDto footballPlayerDto,
                         BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        new FootballPlayerDto().validate(footballPlayerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", iTeamService.display());
            return "create";
        }
        if (footballPlayerDto == null) {
            redirectAttributes.addAttribute("msg", "Đối tượng không tồn tại. ");
            return "create";
        }
        FootballPlayer footballPlayer = new FootballPlayer();
        BeanUtils.copyProperties(footballPlayerDto, footballPlayer);
        iFootballPlayerService.add(footballPlayer);
        redirectAttributes.addAttribute("msg", "Thêm mới thành công ");
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute FootballPlayer footballPlayer, RedirectAttributes redirectAttributes) {
        if (footballPlayer == null) {
            redirectAttributes.addAttribute("msg", "Đối tượng không tồn tại. ");
            return "edit";
        }
        iFootballPlayerService.edit(footballPlayer);
        redirectAttributes.addAttribute("msg", "Sửa thành công ");
        return "redirect:/";
    }

    @PostMapping("/search")
    public String search(Pageable pageable, @RequestParam String name, @RequestParam String minDob, @RequestParam String maxDob, Model model) {
        model.addAttribute("footballPlayerList", iFootballPlayerService.search(pageable, name, minDob, maxDob));
        return "list";
    }
}
