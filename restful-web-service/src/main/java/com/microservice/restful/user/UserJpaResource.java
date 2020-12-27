package com.microservice.restful.user;

import org.springframework.beans.factory.annotation.*;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import javax.validation.*;
import java.net.*;
import java.util.*;

@RestController
@RequestMapping(path="/jpa/users")
public class UserJpaResource {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path="/retrieveAllUsers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public EntityModel getUser(@PathVariable Integer id){
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new UserNotFound("id-"+id);
        }

        //all-users
        EntityModel<User> resource = EntityModel.of(userOptional.get());
        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody User user) {

        User savedUser = userRepository.save(user);

        //CREATED
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();
        //return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }


    @GetMapping("/{id}/posts")
    public List<Post> getAllPostsForUserId(@PathVariable int id){
        Optional<User> userOptional =userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFound("id-"+id);
        }
        User user = userOptional.get();
        return  user.getPostList();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity createPost(@PathVariable int id,@RequestBody Post post){

        Optional<User> userOptional =userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFound("id-"+id);
        }

        User user = userOptional.get();

        post.setUser(user);

        postRepository.save(post);

        //CREATED
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").
                buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }



}
