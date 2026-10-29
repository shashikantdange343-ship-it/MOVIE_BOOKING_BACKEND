package com.shashi.bookmovie.Services.User;


import com.shashi.bookmovie.DTOs.ResponseMessageDTO;
import com.shashi.bookmovie.DTOs.UserDTOs.AuthDTOs;
import com.shashi.bookmovie.Entity.User.Roles;
import com.shashi.bookmovie.Entity.User.User;
import com.shashi.bookmovie.Exceptons.CustomException;
import com.shashi.bookmovie.Exceptons.ResourceNotFoundException;
import com.shashi.bookmovie.Repository.User.UserRepository;
import com.shashi.bookmovie.Security.UserSecurity.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RolesService rolesService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public ResponseMessageDTO registerUser(AuthDTOs.RegisterRequestDTO request) {

        User user = new User();
        user.setUsername(request.userName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setFirst_name(request.firstName());
        user.setLast_name(request.lastName());
        user.setPhone_no(request.PhoneNo());

        Roles userRole = rolesService.getRole("ROLE_CUSTOMER");

        user.setRoles(List.of(userRole));

        userRepository.save(user);

        return new ResponseMessageDTO("Registered Successfully !");
    }

    @Override
    public AuthDTOs.LoginResponseDTO loginUser(AuthDTOs.LoginRequestDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.userName(),request.password()));
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.userName());
            final String jwtToken = jwtUtil.generateToken(userDetails);
            return new AuthDTOs.LoginResponseDTO(jwtToken, "LOGGED IN SUCCESSFULLY !");
        }
        catch (BadCredentialsException e){
            throw new CustomException("Wrong User Name Or Password !");
        }
    }

    @Override
    public User getUser(String userName) {
       Optional<User> optionalUser = userRepository.findByUsername(userName);
       if (optionalUser.isEmpty()){
           throw new ResourceNotFoundException("User Not Found !");
       }
        return optionalUser.get();
    }
}
