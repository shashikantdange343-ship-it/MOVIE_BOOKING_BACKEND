package com.shashi.bookmovie.Services.User;


import com.shashi.bookmovie.Entity.User.Roles;
import com.shashi.bookmovie.Exceptons.CustomException;
import com.shashi.bookmovie.Repository.User.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService{

    private final RolesRepository rolesRepository;

    @Override
    public Roles getRole(String role) {
        Optional<Roles> optionalUserRole = rolesRepository.findByRoleName(role);

        if (optionalUserRole.isEmpty()){
            throw new CustomException("Role Not Found !");
        }

         return optionalUserRole.get();

    }
}
