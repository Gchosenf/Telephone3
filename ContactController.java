package com.gml.telephone3.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "http://localhost:63342")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<String> addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
        return ResponseEntity.ok("联系人添加成功");
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteContact(@PathVariable String name) {
        contactService.deleteContact(name);
        return ResponseEntity.ok("联系人删除成功");
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateContact(@PathVariable String name, @RequestBody Contact contact) {
        contactService.updateContact(name, contact);
        return ResponseEntity.ok("联系人修改成功");
    }
}
