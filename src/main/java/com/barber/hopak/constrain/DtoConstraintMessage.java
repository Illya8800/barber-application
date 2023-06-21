package com.barber.hopak.constrain;

public class DtoConstraintMessage {

    public static final String LONG_MAX_VALUE_CONSTRAIN_TEXT = "This field should be less then 9,223,372,036,854,775,808";
    public static final String INTEGER_MAX_VALUE_CONSTRAIN_TEXT = "This field should be less then 2,147,483,648";
    public static final String MIN_VALUE_SHOULD_BE_MORE_THEN_0 = "This field should be greater then 0";

    public static final String STRING_SHOULD_NOT_BE_NULL_OR_EMPTY_OR_HAVE_ONLY_WHITESPACE = "This field shouldn't be null, or empty, or have only whitespace";
    public static final String FIELD_SHOULD_NOT_BE_NULL = "This shouldn't be null";

    public static final String STRING_SHOULD_BE_IN_RANGE_BETWEEN_1_AND_30_CHARACTER = "This string length should be between 1 and 30 characters";
    public static final String STRING_SHOULD_BE_LESS_THEN_255_CHARACTER = "This string length should be less then 255 characters";


    /*BARBER*/
    public static final String BARBER_INSTAGRAM_SHOULD_BE_CORRECT = "The Instagram's nickname isn't exist";
    public static final String BARBER_RANK_SHOULD_BE_CORRECT = "This rank is not exist";
    /*END*/

    /*CHECK*/
    public static final String CHECK_LOCAL_DATE_TIME_SHOULD_BE_IN_THE_FUTURE = "Your appointment should be in the future";
    /*END*/

    /*CLIENT*/
    public static final String CLIENT_PHONE_NUMBER_SHOULD_BE_MATCH_WITH_REGEX = "This phone number should be like '+(00)000-000-00-00'";
    public static final String CLIENT_PHONE_NUMBER_SHOULD_BE_UNIQUE = "This phone number should be unique";
    /*END*/

    /*HAIRCUT*/
    public static final String HAIRCUT_NAME_SHOULD_BE_UNIQUE = "This haircut name should be unique";
    public static final String HAIRCUT_DURATION_SHOULD_BE_LESS_THEN_32767 = "The haircut duration should be less then 32767 minutes";
    /*END*/

    /*HAIRCUT_ORDER*/
    public static final String HAIRCUT_ORDER_DATE_TIME_SHOULD_BE_IN_THE_FUTURE = "The haircut order should be in the future";
    /*END*/

    /*IMAGE*/
    public static final String IMAGE_NAME_SHOULD_BE_UNIQUE = "This image name is not unique";
    public static final String IMAGE_SHOULD_NOT_BE_NULL = "Image can't be null";
    public static final String IMAGE_FILE_IS_NOT_SELECTED = "Photo is not selected";
    public static final String IMAGE_ORIGINAL_FILE_NAME_SHOULD_BE_CORRECT = "This file name isn't correct";
    public static final String IMAGE_TYPE_UNKNOWN = "The image's extension isn't support";
    public static final String IMAGE_FILE_SIZE_SHOULD_BE_BETWEEN_1KB_AND_16MB = "This file hadn't a good size. Should be less than 16 mb";
    /*END*/

    /*PAYMENT*/
    public static final String CALCULATION_DOES_NOT_CONVERGE = "The calculation does not converge";
    public static final String PAYMENT_TYPE_IS_UNKNOWN = "This payment type isn't exist. It should be CARD or CASH";
    /*END*/

    /*ROLE*/
    public static final String ROLE_NAME_IS_UNKNOWN = "The role type isn't exist";
    /*END*/

    /*USER*/
    public static final String USER_PASSWORD_SHOULD_BE_GREATER_THEN_4_CHARACTER = "The password length should be more then 4 characters";
    public static final String USER_ROLES_SHOULD_NOT_BE_EMPTY = "The user should have role(s)";
    public static final String USER_ROLES_SHOULD_CONSIST_ONLY_PREPARED_ROLES = "One or more of user roles is wrong";
    /*END*/
}
