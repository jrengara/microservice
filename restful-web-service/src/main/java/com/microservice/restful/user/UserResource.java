package com.microservice.restful.user;

import org.springframework.beans.factory.annotation.*;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import javax.validation.*;
import java.net.*;
import java.util.*;

@RestController
@RequestMapping(path="/users")
public class UserResource {

    @Autowired
    UserDaoService userDaoService;

    @GetMapping(path="/retrieveAllUsers")
    public List<User> getAllUsers(){
        return userDaoService.findAllUser();
    }

    @GetMapping(path="/{id}")
    public EntityModel getUser(@PathVariable Integer id){
        User user = userDaoService.getUser(id);

        if(user==null){
            throw new UserNotFound("id-"+id);
        }

        //all-users
        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);
        //CREATED
        //
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();
        //return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userDaoService.deleteUser(id);

        if(user==null) {
            throw new UserNotFound("id-"+id);
        }
    }

}
