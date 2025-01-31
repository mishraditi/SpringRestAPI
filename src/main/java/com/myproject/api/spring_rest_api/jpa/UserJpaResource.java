package com.myproject.api.spring_rest_api.jpa;

import com.myproject.api.spring_rest_api.user.User;
import com.myproject.api.spring_rest_api.user.UserDaoService;
import com.myproject.api.spring_rest_api.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private UserDaoService service;
   private UserRepository repository;
    public UserJpaResource(UserDaoService service , UserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    // GET /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    //POST /users
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid  @RequestBody User user) {
        User savedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();    }
     @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        repository.deleteById(id);
     }

}