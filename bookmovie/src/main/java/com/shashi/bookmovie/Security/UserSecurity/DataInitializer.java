package com.shashi.bookmovie.Security.UserSecurity;

//package com.shashi.ecommerce.config; // Apna package name set kar lena



import com.shashi.bookmovie.Entity.User.Roles;
import com.shashi.bookmovie.Repository.User.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

// @Component likhna bohot zaroori hai, isse Spring ko pata chalta hai ki ye file chalani hai
@Component
public class DataInitializer  {

//    @Autowired
//    private RolesRepository rolesRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        // Pehle check karenge ki kya table already khali hai?
//        // (Ye safe approach hai, taaki kal ko jab tum 'update' use karo toh duplicate error na aaye)
//        if (rolesRepository.count() == 0) {
//
//            // 1. Customer Role Banao
//            Roles customerRole = new Roles();
//            customerRole.setRoleName("ROLE_CUSTOMER");
//
//            // 2. Admin Role Banao
//            Roles adminRole = new Roles();
//            adminRole.setRoleName("ROLE_ADMIN");
//
//            Roles managerRole = new Roles();
//            managerRole.setRoleName("ROLE_MANAGER");
//
//            // 3. Dono ko database me save kar do
//            rolesRepository.saveAll(List.of(customerRole, adminRole , managerRole));
//
//            System.out.println("✅ MUBARAK HO! Default Roles (CUSTOMER aur ADMIN) database me automatically save ho gaye hain!");
//        }
//    }
}