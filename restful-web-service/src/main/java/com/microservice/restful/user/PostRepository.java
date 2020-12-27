package com.microservice.restful.user;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

}
