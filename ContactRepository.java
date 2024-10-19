package com.gml.telephone3.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Contact contact) {
        String sql = "INSERT INTO contacts (name, phone, address) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, contact.getName(), contact.getPhone(), contact.getAddress());
    }

    public Contact findByName(String name) {
        String sql = "SELECT * FROM contacts WHERE name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) -> new Contact(
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("address")
            ));
        } catch (Exception e) {
            return null; // 如果没有找到，返回null
        }
    }

    public List<Contact> findAll() {
        String sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Contact(
                rs.getString("name"),
                rs.getString("phone"),
                rs.getString("address")
        ));
    }

    public void delete(String name) {
        String sql = "DELETE FROM contacts WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public void update(String name, Contact contact) {
        String sql = "UPDATE contacts SET phone = ?, address = ? WHERE name = ?";
        jdbcTemplate.update(sql, contact.getPhone(), contact.getAddress(), name);
    }
}
