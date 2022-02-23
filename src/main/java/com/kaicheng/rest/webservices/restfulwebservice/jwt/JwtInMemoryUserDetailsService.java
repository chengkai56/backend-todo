package com.kaicheng.rest.webservices.restfulwebservice.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "kaicheng",
        "$2a$10$A6pUUHzSNaA4L5cT3q09vOc41.RAELtSXOOWL1cKnqdOVlDHAu2RS", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(2L, "jasper",
            "$2a$10$k34T0SUtw/glxcCSSjuEiemoEnE/Fd7MocBjGM9Os7c1y0UZPp.Aq", "ROLE_USER_2"));

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


