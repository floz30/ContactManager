package fr.floz.contact;

import fr.floz.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactService.getAll());
        return "contacts";
    }

    @GetMapping("/contact")
    public String emptyContact(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-edit";
    }

    @GetMapping("/contact/{id}")
    public String getOne(@PathVariable UUID id, @RequestParam(required = false) boolean edit, Model model) {
        var contact = contactService.get(id).orElseThrow(() -> new ContactNotFoundException(id));
        model.addAttribute("contact", contact);
        if (edit) {
            return "contact-edit";
        }
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@ModelAttribute Contact contact, Model model) {
        contactService.create(contact);
        return "redirect:/";
    }

    @DeleteMapping("/contact/{id}")
    public String deleteContact() {
        return "redirect:/";
    }

}
