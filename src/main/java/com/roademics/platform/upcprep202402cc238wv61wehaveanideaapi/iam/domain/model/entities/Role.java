package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.entities;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.valueobjects.Roles;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Document(collection = "roles")
public class Role {

    @Id
    private Long id;

    private Roles name;

    public Role(Roles name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static Role getDefaultRole() {
        return new Role(Roles.ROLE_USER);
    }

    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty()) return List.of(getDefaultRole());
        return roles;
    }
}
