package fr.floz.contact;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @GetMapping("contact")
    public String createContact(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@ModelAttribute Contact contact, Model model) {
        model.addAttribute("contact", contact);
        System.out.println(contact.toVCF());
        return "contact";
    }

}
