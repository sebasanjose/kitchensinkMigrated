package com.example.kitchensink.controller;

import com.example.kitchensink.data.MemberRepository;
import com.example.kitchensink.model.Member;
import com.example.kitchensink.service.MemberRegistration;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

@Controller
public class MemberController {

    @Autowired
    private MemberRegistration memberRegistration;

    @Autowired
    private MemberRepository memberRepository;

    @ModelAttribute("newMember")
    public Member initNewMember() {
        return new Member();
    }

    @PostMapping("/register")
    public String registerMember(@Valid @ModelAttribute("newMember") Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("members", memberRepository.findAll());
            return "index";
        }
        
        try {
            memberRegistration.register(member);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during registration. Please try again.");
            model.addAttribute("members", memberRepository.findAll());
            return "index";
        }
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newMember", new Member());
        model.addAttribute("members", memberRepository.findAll());
        return "index";
    }
}
