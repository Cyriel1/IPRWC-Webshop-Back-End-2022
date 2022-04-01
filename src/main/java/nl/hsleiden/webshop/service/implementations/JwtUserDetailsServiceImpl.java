package nl.hsleiden.webshop.service.implementations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersDAO.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("Users not found with username: " + username);
        }

        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public void saveUser(Users user) {
        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        usersDAO.save(newUser);
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException disabledException) {
            throw new Exception("The User is disabled", disabledException);
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("The Credentials are invalid", badCredentialsException);
        }
    }
}