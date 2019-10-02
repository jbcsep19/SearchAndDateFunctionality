package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    MessageRespository messageRespository;

    @RequestMapping("/")
    public String messageList(Model model){
        model.addAttribute("messages",messageRespository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String addMessage(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }
    @PostMapping("/processsearch")
    public String searchResult(Model model,@RequestParam(name="search") String search) {
        model.addAttribute("messages", messageRespository.findByTitle(search));
        return "searchlist";
    }
    @PostMapping("/processmessage")
    public String processForm(@ModelAttribute Message message, @RequestParam(name = "date")
                              String date){
        String pattern = "yyyy-MM-dd";
        try {
            String formattedDate = date.substring(1,date.length()-1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date realDate = simpleDateFormat.parse(formattedDate);
            message.setDate(realDate);
        }
        catch (java.text.ParseException e){
            e.printStackTrace();
        }

        messageRespository.save(message);
                return "redirect:/";
    }

}
