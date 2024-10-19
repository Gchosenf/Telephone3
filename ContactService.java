package com.gml.telephone3.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public void addContact(Contact contact) {
        if (contact.getName() == null || contact.getPhone() == null || contact.getAddress() == null) {
            System.err.println("联系人信息不完整: " + contact);
            throw new IllegalArgumentException("联系人信息不完整");
        }

        // 检查联系人是否已经存在
        Contact existingContact = contactRepository.findByName(contact.getName());
        if (existingContact != null) {
            System.err.println("联系人已存在: " + contact);
            throw new IllegalArgumentException("联系人已存在");
        }

        contactRepository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public void deleteContact(String name) {
        contactRepository.delete(name);
    }

    public void updateContact(String name, Contact contact) {
        if (contact.getName() == null || contact.getPhone() == null || contact.getAddress() == null) {
            System.err.println("联系人信息不完整: " + contact);
            throw new IllegalArgumentException("联系人信息不完整");
        }

        // 检查联系人是否存在
        Contact existingContact = contactRepository.findByName(name);
        if (existingContact == null) {
            System.err.println("联系人不存在: " + name);
            throw new IllegalArgumentException("联系人不存在");
        }

        contactRepository.update(name, contact);
    }
}
