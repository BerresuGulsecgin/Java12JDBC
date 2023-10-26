package com.berre.controller;

import com.berre.entity.Person;
import com.berre.service.PersonService;

public class PersonController {

    private final PersonService personService;


    public PersonController() {

        this.personService = new PersonService();
    }

    public void register(Person person) {

        personService.register(person);
    }

    public void findAll() {
        personService.findAll();
    }

    public void update(Person person) {
        personService.update(person);
    }

    public Person findById(int idargs) {
        return personService.findById(idargs);
    }

    public void deletePersonById(Person person){
        personService.deletePersonById(person);
    }

    public void deleteAll(){
        personService.deleteAll();
    }
}

