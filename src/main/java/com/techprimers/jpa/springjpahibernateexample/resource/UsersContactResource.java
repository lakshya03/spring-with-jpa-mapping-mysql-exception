package com.techprimers.jpa.springjpahibernateexample.resource;


import com.techprimers.jpa.springjpahibernateexample.exception.RecordNotFoundException;
import com.techprimers.jpa.springjpahibernateexample.model.Users;
import com.techprimers.jpa.springjpahibernateexample.model.UsersContact;
import com.techprimers.jpa.springjpahibernateexample.repository.UsersContactRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rest/userscontact")
@RestController
public class UsersContactResource {

    private UsersContactRepository usersContactRepository;

    public UsersContactResource(UsersContactRepository usersContactRepository) {
        this.usersContactRepository = usersContactRepository;
    }

    @GetMapping(value = "/all")
    public List<UsersContact> getUsersContact() {
        return usersContactRepository.findAll();
    }

    @GetMapping(value = "/update/{name}")
    public List<UsersContact> update(@PathVariable final String name) {

        UsersContact usersContact = new UsersContact();

        Users users = new Users();
        users.setTeamName("Development")
                .setSalary(10000)
                .setName(name);


        usersContact.setPhoneNo(11111)
                .setUsers(users);

        usersContactRepository.save(usersContact);

        return usersContactRepository.findAll();


    }
    @PostMapping(value="/load")
	public ResponseEntity<UsersContact> persist(@RequestBody final UsersContact usersContact)
	{
		usersContactRepository.save(usersContact);
		//return usersContactRepository.findAll();
		return new ResponseEntity<UsersContact>(usersContact, HttpStatus.OK);
	}
    @RequestMapping("/delet/{id}")
	public String deleteUsersContact(@PathVariable Integer id) {
		usersContactRepository.delete(id);
		return "deleted";
	}
    @RequestMapping(value="/list/{id}")
    public ResponseEntity<UsersContact> finde(@PathVariable Integer id)
    {
    	UsersContact userCont =usersContactRepository.findOne(id);
    	if(userCont == null) {
            throw new RecordNotFoundException("Invalid employee id : " + id);
       }
    	return new ResponseEntity<UsersContact>(userCont, HttpStatus.OK);
    }
    @RequestMapping( value ="/updatedd/{id}")
    public List<UsersContact> updateContact(@PathVariable Integer id,@RequestBody UsersContact usersContact)
    {
    	UsersContact userCont =usersContactRepository.findOne(id);
    	userCont.setPhoneNo(usersContact.getPhoneNo());
    	UsersContact usersupdate= usersContactRepository.save(userCont);
    	return usersContactRepository.findAll();
    }
}
