package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MessageRespository extends CrudRepository <Message,Long> {

    ArrayList<Message> findByTitle(String title);
}
