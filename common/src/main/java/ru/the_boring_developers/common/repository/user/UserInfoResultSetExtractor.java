package ru.the_boring_developers.common.repository.user;

import ru.the_boring_developers.common.entity.user.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserInfoResultSetExtractor {

    UserInfo extractValue(ResultSet rs) throws SQLException;
}
