package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.internal.queryservices;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.internal.repositoriesimpl.UserRepositoryImpl;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.aggregates.User;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.queries.GetAllUsersQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.queries.GetUserByIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.queries.GetUserByUsernameQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.services.UserQueryService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.persistence.sdmdb.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepositoryImpl userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = (UserRepositoryImpl) userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}
