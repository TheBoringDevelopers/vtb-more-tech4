package ru.the_boring_developers.common.repository.user;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.the_boring_developers.common.entity.user.UserInfo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class UserInfoRepositoryImpl implements UserInfoRepository {

    private final JdbcOperations jdbcOperations;
    private final UserInfoResultSetExtractor userInfoResultSetExtractor;

    @Override
    public UserInfo findById(Long id) {
        return jdbcOperations.query(
                "SELECT * FROM user_info WHERE id = ?",
                ps -> {
                    int idx = 0;
                    ps.setLong(++idx, id);
                },
                rs -> rs.next() ? userInfoResultSetExtractor.extractValue(rs) : null);
    }

    @Override
    public Long create(UserInfo userInfo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO user_info (phone_number, first_name, last_name, patronymic, email, age, city, description, genger)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            int idx = 0;
            ps.setString(++idx, userInfo.getPhoneNumber());
            ps.setString(++idx, userInfo.getFirstName());
            ps.setString(++idx, userInfo.getLastName());
            ps.setString(++idx, userInfo.getPatronymic());
            ps.setString(++idx, userInfo.getEmail());
            ps.setLong(++idx, userInfo.getAge());
            ps.setString(++idx, userInfo.getCity());
            ps.setString(++idx, userInfo.getDescription());
            ps.setBoolean(++idx, userInfo.isGender());
            return ps;
        }, keyHolder);
        return (Long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }
}
