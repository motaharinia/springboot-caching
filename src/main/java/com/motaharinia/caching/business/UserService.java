package com.motaharinia.caching.business;

import com.motaharinia.caching.presentation.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public interface UserService {

    /**
     * کاربر جدید را در سامانه ثبت میکند و مدل آن را برای کلاینت ارسال میکند
     * کش همه کاربران را حذف میکند که در فراخوانی بعدی findAll اطلاعات این کاربر جدید نیز در کش قرار بگیرد
     *
     * @param userModel مدل کاربر
     * @return خروجی: مدل ثبت شده کاربر با شناسه ثبت شده
     */
    UserModel create(UserModel userModel);

    /**
     * کاربری با شناسه مشخص جستجو میکند و مدل آن را برای کلاینت ارسال میکند
     * اگر کاربر مورد نظر بیشتر از 12هزارنفر فالوور داشته باشد اطلاعاتش را از کش میخواند و در غیر این صورت از دیتابیس میخواند
     *
     * @param id شناسه
     * @return خروجی: مدل کاربر
     */
    UserModel findOne(Integer id);

    /**
     * اطلاعات تمام کاربران را جستجو میکند و مدل آنها را برای کلاینت ارسال میکند
     * کش برای اطلاعات تمام کاربران با کلید all در مقدار user ایجاد میکند
     *
     * @return خروجی: اطلاعات تمام کاربران سامانه
     */
    List<UserModel> findAll();

    /**
     * اطلاعات کاربر را ویرایش میکند و مدل آن را برای کلاینت ارسال میکند
     * کش تک کاربر با شناسه مورد نظر را ویرایش میکند تا در فراخوانی بعدی findOne اطلاعات ویرایش شده این کاربر از کش خوانده شود
     * کش همه کاربران را حذف میکند که در فراخوانی بعدی findAll اطلاعات ویرایش شده این کاربر جدید نیز در کش قرار بگیرد
     *
     * @param userModel مدل کاربر
     * @return خروجی: مدل ویرایش شده کاربر با اطلاعات ویرایش شده
     */
    UserModel update(UserModel userModel);

    /**
     * اطلاعات یک کاربر را طبق شناسه ورودی حذف میکند و مدل آن را برای کلاینت ارسال میکند
     * کش تک کاربر با شناسه مورد نظر را حذف میکند
     * کش همه کاربران را حذف میکند که در فراخوانی بعدی اطلاعات حذف شده این کاربر در کش نباشد
     *
     * @param id شناسه
     * @return خروجی: مدل کاربر
     */
    UserModel delete(Integer id);
}
