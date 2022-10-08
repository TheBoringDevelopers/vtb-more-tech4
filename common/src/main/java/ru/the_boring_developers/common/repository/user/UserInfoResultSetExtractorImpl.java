package ru.the_boring_developers.common.repository.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.the_boring_developers.common.entity.user.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class UserInfoResultSetExtractorImpl implements UserInfoResultSetExtractor {

    @Override
    public UserInfo extractValue(ResultSet rs) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(rs.getLong("id"));
        userInfo.setFirstName(rs.getString("first_name"));
        userInfo.setLastName(rs.getString("last_name"));
        userInfo.setPatronymic(rs.getString("patronymic"));
        userInfo.setPhoneNumber(rs.getString("phone_number"));
        userInfo.setEmail(rs.getString("email"));
        userInfo.setGender(rs.getBoolean("gender"));
        userInfo.setDescription(rs.getString("description"));
        userInfo.setAge(rs.getLong("age"));
        return userInfo;
    }
}
