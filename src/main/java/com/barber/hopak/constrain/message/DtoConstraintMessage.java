package com.barber.hopak.constrain.message;

public class DtoConstraintMessage {
    public static final String FIELD_SHOULD_NOT_CONSIST_ONLY_FROM_SPACE = "Поле має містити щось окрім пробілів";
    public static final String BARBER_FIRSTNAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER = "Довжина ім'я має бути між 1 і 30 символами";
    public static final String BARBER_INSTAGRAM_SHOULD_BE_CORRECT = "Нік в Instagram неправильний";
    public static final String HAIRCUT_NAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER = "Довжина ім'я має бути між 1 і 30 символами";
    public static final String HAIRCUT_AVATAR_SHOULD_BE_LESS_THEN_16MB = "Розмір файлу має бути меньшим за 16 мб";
    public static final String HAIRCUT_PRICE_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_2147483647 = "Ціна за послугу має бути в діапазоні між: 0 і 2147483647";
    public static final String HAIRCUT_DURATION_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_32767 = "Час послуги не може бути меньшою зо 1 хвилину і більшою за 32767 хвилин";
    public static final String HAIRCUT_ORDER_FIRSTNAME_CUSTOMER_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_64_CHARACTER = "Ім'я має бути в діапазоні між 0 i 64 символами";
    public static final String HAIRCUT_ORDER_SECOND_NAME_CUSTOMER_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_64_CHARACTER = "Побатькові має бути в діапазоні між 0 i 64 символами";
    public static final String HAIRCUT_ORDER_DESCRIPTION_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_255_CHARACTER = "Додаткова інформація має бути до 255 символів";
    public static final String HAIRCUT_ORDER_PHONE_NUMBER_CUSTOMER_ILLEGAL_TYPE = "Невірний номер телефону";
    public static final String HAIRCUT_ORDER_DATE_TIME_SHOULD_BE_IN_THE_FUTURE = "Дата запису не може бути зроблена на попередній час";



    public static final String IMAGE_NAME_SHOULD_BE_UNIQUE = "This image name is not unique";
    public static final String IMAGE_ORIGINAL_FILE_NAME_SHOULD_BE_CORRECT = "This file name isn't correct";
    public static final String IMAGE_TYPE_UNKNOWN = "This extension isn't support";
    public static final String IMAGE_FILE_IS_NOT_SELECTED = "Photo is not selected";
    public static final String IMAGE_FILE_SIZE_IS_NOT = "This file hadn't a good size. Should be less than 16 mb";



    public static final String PAYMENT_PRICE_SHOULD_BE_IN_RANGE_BETWEEN_0_AND_2147483647 = "Сума замовлення має бути в діапазоні між: 0 і 2147483647";
    public static final String PAYMENT_DISCOUNT_SHOULD_BE_POSITIVE_VALUE_UP_TO_100 = "Знижка не може бути меньшою за 0 і більшою за 100%";
    public static final String PAYMENT_TYPE_IS_UNKNOWN = "Невідомий спосіб оплати. Має бути картка, або готівка";
    public static final String USER_FIRSTNAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30 = "Довжина ім'я має бути між 1 і 30 символами";
    public static final String USER_SECOND_NAME_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30 = "Довжина прізвища має бути між 1 і 30 символами";
    public static final String USER_PASSWORD_SHOULD_BE_GREATER_THEN_4_CHARACTER = "Довжина паролю має бути більшою за 4 символа";
    public static final String USER_ROLES_SHOULD_NOT_BE_EMPTY = "У користувача мають бути ролі";
    public static final String USER_ROLES_SHOULD_CONSIST_ONLY_PREPARED_ROLES = "Якась роль не є дійсною";
}
