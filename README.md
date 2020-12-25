# SPRING BOOT AND SPRING SECURITY

## Description

This repository is a Software of Application with JAVA.

## Installation

Using Spring Boot, JPA, JWT, Spring Security, Lombok, etc preferably.

## Software and Automatization

Apply Gradle 6.7.1

## Tools

Java version JDK-11

## IDE

IntelliJ IDEA

## Database

Using H2Databse, MySQL,etc

## Client Rest

Postman, Insomnia, Talend API Tester,etc

## Usage

```html
$ git clone https://github.com/DanielArturoAlejoAlvarez/Spring-Boot-Gradle-Spring-Security.git
[NAME APP]

```

Follow the following steps and you're good to go! Important:

![alt text](https://cdn.einnovator.org/ei-home/docs/quickguide/quickguide-12-spring-security/spring-security-arch.png)
## Coding

### Config
```java

```

### Config
```java
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

```

### Controllers
```java
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException e) {
            throw new Exception("Username/Password incorrect!", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                authenticationRequest.getUsername()
        );

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
```

### Models
```java
@Data
@AllArgsConstructor @NoArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;
}

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private final String jwt;
}
```

### Services
```java
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("admin","password",new ArrayList<>());
    }
}
```

## Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/DanielArturoAlejoAlvarez/Spring-Boot-Gradle-Spring-Security. This project is intended to be a safe, welcoming space for collaboration, and contributors are expected to adhere to the [Contributor Covenant](http://contributor-covenant.org) code of conduct.

## License

The gem is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).
````