package ru.the_boring_developers.common.repository.user;

import ru.the_boring_developers.common.entity.user.UserInfo;
import ru.the_boring_developers.common.repository.Repository;

public interface UserInfoRepository extends Repository {
    UserInfo findById(Long id);
    Long create(UserInfo userInfo);
}
