package com.trig.trigapp.CommonFiles;

public class Constants {


    private static Constants constants;

    public static Constants getInstance() {
        if (constants == null) {
            constants = new Constants();
        }
        return constants;
    }

    public static final String IS_LOGGED_IN = "is_logged_in";
    public static final String SCHEDULED_DATE_FORMAT = "dd MMM, yyyy,HH:mm";

    public static String internetCheck = "http://13.127.232.32/speed_test/download/";
    public final Integer NoInternetCase = 900 ;

    public static String APP_COLOUR = "app_colour";
    public static String My_PREFERENCE = "my_preference";

    public static String left = "left";
    public static String right = "right";
    public static String front = "front";
    public static String back = "back";
    public static String stop = "stop";
    public static String start_call = "start_call";
    public static String end_call = "end_call";
    public static String command = "command";
    public static String Ok = "Ok";
    public static String No = "No";
    public static String Yes = "Yes";
    public static String Cancel = "Cancel";
    public static String rightClick = "rightClick";
    public static String leftClick = "leftClick";
    public static String rightContinous = "rightContinous";
    public static String leftContinous = "leftContinous";
    public static String frontContinous = "frontContinous";
    public static String STOPCALL = "STOPCALL";

    public static int timeTimeForCallToBot = 10000;
    public static int timeTimoutForStartCall = 60000;
    public static int ParentTimoutSlowInternet = 3000;
    public static int BotTimoutSlowInternet = 10000;


    //    API CALL
    public static String responseSuccess = "success";
    public static String responseError = "error";
    public static String Occupied = "Occupied";


    //    DIALOG MESSAGES

    public static String LOGOUT = "LOGOUT";
    public static String EXIT = "EXIT";
    public static String RETRY = "RETRY";
    public static String DISMISS = "DISMISS";


    //    Function Numbers Parentall App
    // Function Numbers Parentall App
    public static final Integer Parentall1 = 101;
    public static final Integer Parentall2 = 102;
    public static final Integer ParentlalUnlink = 103;
    public static final Integer ParentalLogout = 104;

    //    Function Numbers VideoCall
    public static final Integer VideoCall1Retry = 501;
    public static final Integer VideoCall2 = 502;
    public static final Integer VideoCall3ExitCall = 503;

    public static final Integer VideoCall4 = 504;

    public static final Integer State1 = 551;
    public static final Integer State2 = 552;
    public static final Integer StateLogOut = 553;
    public static final Integer StateUnlink = 554;
    public static final Integer State5 = 555;



    // For Login Screen Dialog
    public static final Integer editMobile = 700; //a
    public static final Integer editEmail = 701; //a


    public static final Integer registok = 751;

    public static final String OnInternet = "OnInternet";
    public static final String NoInternet = "NoInternet";
    public static final String WeakInternetParent = "WeakInternetParent";
    public static final String WeakInternetBot = "WeakInternetBot";
    public static String Login = "Login";
    public static String Exit = "Exit";
    public static String OK = "OK";


    // Analytics TAGS
    public static String profile_id = "DEFAULT";
    public static String event_source = "PARENTAL_APP";
    public static String LOGIN_SUCCESS = "LOGIN_SUCCESS";
    public static String LOGIN_FAILED = "LOGIN_FAILED";
    public static String SIGNUP_SUCCESS = "SIGNUP_SUCCESS";
    public static String SIGNUP_FAILED = "SIGNUP_FAILED";
    public static String AUTHENTICATED = "AUTHENTICATED";
    public static String AUTHENTICATION_FAILED = "AUTHENTICATION_FAILED";
    public static String OTP_RESEND_MOBILE = "OTP_RESEND_MOBILE";
    public static String OTP_RESEND_EMAIL = "OTP_RESEND_EMAIL";
    public static String ASSOCAIATION_SUCCESS = "ASSOCIATION_SUCCESS";
    public static String ALREADY_ASSOCAIATION = "ALREADY_ASSOCIATED";
    public static String ASSOCAIATION_FAILED = "ASSOCIATION_FAILED";
    public static String RESCAN_BOT_LIST = "RESCAN_BOT_LIST";
    public static String WIFI_LIST_REFRESH = "WIFI_LIST_REFRESH";
    public static String WIFI_LIST_LOADING_START_EVENT = "WIFI_LIST_LOADING_START_EVENT";
    public static String WIFI_LIST_LOADING_END_EVENT = "WIFI_LIST_LOADING_END_EVENT";
    public static String CONFIGURED_WIFI_SELECTED = "CONFIGURED_WIFI_SELECTED";
    public static String AVAILABLE_WIFI_SELECTED = "AVAILABLE_WIFI_SELECTED";
    public static String WIFI_PASSWORD_CONFIGURED = "WIFI_PASSWORD_CONFIGURED";
    public static String PHONE_DETAILS = "PHONE_DETAILS";
    public static String LOCATION_DETAILS = "LOCATION_DETAILS";
    public static String HELP_AND_SUPPORT = "HELP_AND_SUPPORT_SUCCESS";
    public static String UNLINK_SUCCESS = "UNLINK_SUCCESS";
    public static String UNLINK_FAILED = "UNLINK_FAILED";
    public static String LOGOUT_SUCCESS = "LOGOUT_SUCCESS";
    public static String TELECONNECT_START_EVENT = "TELECONNECT_START_EVENT";
    public static String CALL_CONNECTED_SUCCESS = "CALL_CONNECTED_SUCCESS";
    public static String CALL_DISCONNECTED_BYBOT_FAILED = "CALL_DISCONNECTED_BYBOT_FAILED";
    public static String TELECONNECT_END_EVENT = "TELECONNECT_END_EVENT";
    public static String TELECONNECT_UNANSWERED_FAILED = "TELECONNECT_UNANSWERED_FAILED";
    public static String TELECONNECT_BOT_NOT_ACTIVE_FAILED = "TELECONNECT_BOT_NOT_ACTIVE_FAILED";
    public static String EXCEPTION_OCCOURED = "EXCEPTION_OCCOURED";
    public static String SUCCESS = "SUCCESS";
    public static String START = "START";
    public static String DURATION = "DURATION";
    public static String Count = "COUNT";
    public static String VALUE = "VALUE";
    public static String REGEX_ALPHABETS_SPACE="^[A-Za-z\\s]+$";

    //Receiving messege from bot Type
    public static String others = "others";
    public static String isActive = "isActive";

    public static String buildType_debug ="debug";

    //otp page message
    public static String OTPMEASSAGE = "";

    public static String getOTPMEASSAGE() {
        return OTPMEASSAGE;
    }

    public static void setOTPMEASSAGE(String OTPMEASSAGE) {
        Constants.OTPMEASSAGE = OTPMEASSAGE;
    }

    public static boolean parentWeak = false;
    public static boolean botWeak = false;

    public static boolean isParentWeak() {
        return parentWeak;
    }

    public static void setParentWeak(boolean parentWeak) {
        Constants.parentWeak = parentWeak;
    }

    public static boolean isBotWeak() {
        return botWeak;
    }

    public static void setBotWeak(boolean botWeak) {
        Constants.botWeak = botWeak;
    }

    //Success page message
    public static String SUCCESSLOGIN = "";

    public static String getSUCCESSLOGIN() {
        return SUCCESSLOGIN;
    }

    public static void setSUCCESSLOGIN(String SUCCESSLOGIN) {
        Constants.SUCCESSLOGIN = SUCCESSLOGIN;
    }


    //Success page message on Bot Associated
    public static String SUCCESSLOGINONBOTASSOCIATION = "";

    public static String getSUCCESSLOGINONBOTASSOCIATION() {
        return SUCCESSLOGINONBOTASSOCIATION;
    }

    public static void setSUCCESSLOGINONBOTASSOCIATION(String SUCCESSLOGINONBOTASSOCIATION) {
        Constants.SUCCESSLOGINONBOTASSOCIATION = SUCCESSLOGINONBOTASSOCIATION;
    }

    public static int CHILD_NAME_MAX_LENGHT=50;



    // TRIG App Constants

    public static final Integer ExitConfirm = 10;

    public final Integer exitApp = 800;
    public final Integer logout = 810;

    public final Integer loginScreen = 100;
    public final Integer feedback = 101;


    // API Params
    public String getbranch = "getbranch";
    public String user = "user";
    public String trainer = "trainer";
    public String contactUsNumber = "022 66783333";
    public String defaultEmail = "info@triggroup.in";
    public String ATTEMPT = "ATTEMPT";
    public String REVIEW = "REVIEW";

}
