package com.berre.service;

import com.berre.entity.Person;
import com.berre.repository.PersonRepository;

public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(){
        this.personRepository=new PersonRepository();
    }
    public void register(Person person){

            personRepository.register(person);
    }

    public void findAll(){
        personRepository.findAll();
    }

    public void update(Person person){
personRepository.update(person);
    }

    public Person findById(int idargs){
        return personRepository.findById(idargs);
    }

    public void deletePersonById(Person person){
        personRepository.deletePersonById(person);
    }

    public void deleteAll(){
        personRepository.deleteAll();
    }
}
