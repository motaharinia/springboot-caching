package com.motaharinia.caching.business;

import com.motaharinia.caching.presentation.model.UserModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private HashMap<Integer, UserModel> userDatabaseMap = new HashMap<>();

    /**
     * کاربر جدید را در سامانه ثبت میکند و مدل آن را برای کلاینت ارسال میکند
     * کش همه کاربران را حذف میکند که در فراخوانی بعدی findAll اطلاعات این کاربر جدید نیز در کش قرار بگیرد
     *
     * @param userModel مدل کاربر
     * @return خروجی: مدل ثبت شده کاربر با شناسه ثبت شده
     */
    @CacheEvict(value = "userFindAll", allEntries = true)
    @Override
    public UserModel create(UserModel userModel) {
        Optional<Integer> pk = this.userDatabaseMap.keySet().stream().reduce(Integer::max);
        if (pk.isPresent()) {
            userModel.setId(pk.get() + 1);
        } else {
            userModel.setId(1);
        }
        this.userDatabaseMap.put(userModel.getId(), userModel);
        return this.userDatabaseMap.get(userModel.getId());
    }

    /**
     * کاربری با شناسه مشخص جستجو میکند و مدل آن را برای کلاینت ارسال میکند
     * اگر کاربر مورد نظر بیشتر از 12هزارنفر فالوور داشته باشد اطلاعاتش را از کش میخواند و در غیر این صورت از دیتابیس میخواند
     *
     * @param id شناسه
     * @return خروجی: مدل کاربر
     */
    @Cacheable(value = "userFindOne", key = "#id", unless = "#result.followerCount < 12000")
    @Override
    public UserModel findOne(Integer id) {
        if (this.userDatabaseMap.containsKey(id)) {
            UserModel userModel = this.userDatabaseMap.get(id);
            return userModel;
        } else {
            return null;
        }
    }


    /**
     *  اطلاعات تمام کاربران را جستجو میکند و مدل آنها را برای کلاینت ارسال میکند
     * کش برای اطلاعات تمام کاربران با کلید all در مقدار user ایجاد میکند
     *
     * @return خروجی: اطلاعات تمام کاربران سامانه
     */
    @Cacheable(value = "userFindAll")
    @Override
    public List<UserModel> findAll() {
        return this.userDatabaseMap.values().stream().collect(Collectors.toList());
    }

    /**
     *  اطلاعات کاربر را ویرایش میکند و مدل آن را برای کلاینت ارسال میکند
     * کش تک کاربر با شناسه مورد نظر را ویرایش میکند تا در فراخوانی بعدی findOne اطلاعات ویرایش شده این کاربر از کش خوانده شود
     * کش همه کاربران را حذف میکند که در فراخوانی بعدی findAll اطلاعات ویرایش شده این کاربر جدید نیز در کش قرار بگیرد
     *
     * @param userModel مدل کاربر
     * @return خروجی: مدل ویرایش شده کاربر با اطلاعات ویرایش شده
     */
    @CachePut(value = "userFindOne", key = "#userModel.id")
    @CacheEvict(value = "userFindAll", allEntries = true)
    @Override
    public UserModel update(UserModel userModel) {
        if (this.userDatabaseMap.containsKey(userModel.getId())) {
            this.userDatabaseMap.remove(userModel.getId());
            this.userDatabaseMap.put(userModel.getId(), userModel);
            return this.userDatabaseMap.get(userModel.getId());
        } else {
            return null;
        }
    }

    /**
     * اطلاعات یک کاربر را طبق شناسه ورودی حذف میکند و مدل آن را برای کلاینت ارسال میکند
     * کش تک کاربر با شناسه مورد نظر را حذف میکند
     * کش همه کاربران را حذف میکند که در فراخوانی بعدی اطلاعات حذف شده این کاربر در کش نباشد
     *
     * @param id شناسه
     * @return خروجی: مدل کاربر
     */
    @Caching(evict = {@CacheEvict(value = "userFindOne", key = "#id"),@CacheEvict(value = "userFindAll", allEntries = true)})
    @Override
    public UserModel delete(Integer id) {
        if (this.userDatabaseMap.containsKey(id)) {
            UserModel userModel = this.userDatabaseMap.get(id);
            this.userDatabaseMap.remove(id);
            return userModel;
        } else {
            return null;
        }
    }
}
