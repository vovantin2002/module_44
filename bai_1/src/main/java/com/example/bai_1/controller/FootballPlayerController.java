package com.example.bai_1.controller;

import com.example.bai_1.model.Favorites;
import com.example.bai_1.model.FootballPlayer;
import com.example.bai_1.model.FootballPlayerDto;
import com.example.bai_1.service.IFootballPlayerService;
import com.example.bai_1.service.ITeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("favorites")
public class FootballPlayerController {
    @Autowired
    private IFootballPlayerService iFootballPlayerService;
    @Autowired
    private ITeamService iTeamService;

    @ModelAttribute("favorites")
    public Favorites initFavorites() {
        return new Favorites();
    }

    @GetMapping("")
    public String display(@RequestParam(value = "msg", required = false) String msg, @RequestParam("a") Optional<Integer> pageSize,
                          @PageableDefault(size = 4, sort = "name") Pageable pageable, Model model) {
        int size = pageSize.orElse(4);
        Pageable updatedPageable = PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());
        model.addAttribute("footballPlayerList", iFootballPlayerService.display(updatedPageable));
        model.addAttribute("msg", msg);
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

    @GetMapping("/favorites/{id}")
    public String addFavorites(@PathVariable int id, @SessionAttribute(value = "favorites") Favorites favorites) {
        FootballPlayer footballPlayer = iFootballPlayerService.showFootballPlayerEdit(id);
        if (footballPlayer != null) {
            favorites.addFootballPlayer(footballPlayer);
        }
        return "redirect:/";
    }

    @GetMapping("/favoritesList")
    public String favoritesList() {
        return "favorites";
    }

    @GetMapping("/register/{id}")
    public String register(@PathVariable int id) {
        FootballPlayer footballPlayer = iFootballPlayerService.showFootballPlayerEdit(id);
        footballPlayer.setStatus("Đã đăng ký");
        List<FootballPlayer> footballPlayers = iFootballPlayerService.list();
        int cout = 0;
        for (int i = 0; i < footballPlayers.size(); i++) {
            if (footballPlayers.get(i).getStatus().equals("Đã đăng ký")) {
                cout++;
            }
        }
        if (cout > 11) {
            throw new RuntimeException("cầu thủ tối đa là 11");
        }
        iFootballPlayerService.edit(footballPlayer);
        return "redirect:/";
    }

    @GetMapping("/cancel/{id}")
    public String cancelRegister(@PathVariable int id, RedirectAttributes redirectAttributes) {
        FootballPlayer footballPlayer = iFootballPlayerService.showFootballPlayerEdit(id);
        if (footballPlayer == null) {
            redirectAttributes.addAttribute("msg", "Không tìm thấy id. ");
            return "list";
        }
        footballPlayer.setStatus("Dự bị");
        iFootballPlayerService.edit(footballPlayer);
        return "redirect:/";
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
