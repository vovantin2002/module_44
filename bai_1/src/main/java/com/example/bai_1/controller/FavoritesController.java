package com.example.bai_1.controller;

import com.example.bai_1.model.Favorites;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;



@Controller
public class FavoritesController {
    @GetMapping("/favorites")
    public String showFavoriteList(@SessionAttribute(value = "favorites", required = false )Favorites favorites, Model model){
        model.addAttribute("favorites",favorites);
        return "favorites";
    }
}
