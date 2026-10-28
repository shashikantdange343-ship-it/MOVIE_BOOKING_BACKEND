package com.shashi.bookmovie.Services.User;


import com.shashi.bookmovie.DTOs.ResponseMessageDTO;
import com.shashi.bookmovie.DTOs.UserDTOs.AuthDTOs;
import com.shashi.bookmovie.Entity.User.User;

public interface UserService {

    ResponseMessageDTO registerUser(AuthDTOs.RegisterRequestDTO request);

    AuthDTOs.LoginResponseDTO loginUser(AuthDTOs.LoginRequestDTO request);

    User getUser(String userName);

}
