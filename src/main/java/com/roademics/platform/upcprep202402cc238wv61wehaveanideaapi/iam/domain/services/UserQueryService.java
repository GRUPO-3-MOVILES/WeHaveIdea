package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.aggregates.User;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.queries.GetAllUsersQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.queries.GetUserByIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUsernameQuery query);
}