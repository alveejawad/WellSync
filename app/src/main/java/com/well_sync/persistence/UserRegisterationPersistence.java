package com.well_sync.persistence;

import com.well_sync.objects.UserCredentials;
import com.well_sync.objects.UserDetails;

import java.util.*;

public interface UserRegisterationPersistence {

    boolean validateUser(UserCredentials user);

    UserDetails getUser(UserCredentials user);

    void createUser(UserDetails user);

    void updateUser(UserDetails user);

    void deleteUser(UserDetails user);
}
