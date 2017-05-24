package check.grammar;

/**
 * Created by DongND on 10/30/2016.
 */
public class Data {
    public String[] phuamdau = {"b", "c", "ch", "d", "đ", "g", "gh", "h", "k", "kh", "l", "m", "n", "ng",
            "ngh", "nh", "p", "ph", "q", "r", "s", "t", "th", "tr", "v", "x", ""};
    public static String[] nguyenam = {"a", "ă", "â", "e", "ê", "i", "o", "ô", "ơ", "u", "ư", "y"};


    public static String[] nguyenamA = {"a", "á", "à", "ả", "ã", "ạ", "ă", "ắ", "ằ", "ẳ", "ẵ", "ặ",
            "â", "ấ", "ầ", "ẫ", "ậ","ẩ"};
    public static String[] nguyenamE = {"e", "é", "è", "ẹ", "ẽ", "ẻ", "ê","ế", "ề", "ệ", "ễ","ể"};
    public static String[] nguyenamI = {"i", "ì", "ỉ", "ị", "ĩ", "í"};
    public static String[] nguyenamO = {"o", "ó", "ọ", "ò", "õ","ỏ", "ô", "ỗ", "ồ", "ố", "ộ","ổ", "ơ",
            "ỡ", "ớ", "ờ", "ợ","ở"};
    public static String[] nguyenamU = {"u", "ú", "ụ", "ù", "ũ","ủ", "ư", "ứ", "ự", "ừ", "ữ","ử"};
    public static String[] nguyenamY = {"y", "ý", "ỷ", "ỳ", "ỵ","ỹ"};

    public static String[] nguyenamDau={"á", "à", "ả", "ã", "ạ", "ắ", "ằ", "ẳ", "ẵ", "ặ"
            , "ấ", "ầ", "ẫ", "ậ", "ẩ", "é", "è", "ẹ", "ẽ", "ẻ", "ế", "ề", "ệ", "ễ","ể",
            "ì", "ỉ", "ị", "ĩ","í", "ó", "ọ", "ò", "õ","ỏ", "ỗ", "ồ", "ố", "ộ","ổ",
            "ỡ", "ớ", "ờ", "ợ","ở", "ú", "ụ", "ù", "ũ","ủ", "ứ", "ự", "ừ", "ữ","ử", "ý", "ỷ", "ỳ", "ỵ", "ỹ"};

    public static String[][] arr_nguyenam = {nguyenamA, nguyenamE, nguyenamI, nguyenamO, nguyenamU, nguyenamY};

    public static String[] vanA = {"a", "ac", "ach", "ai", "am", "an", "ang", "anh", "ao", "ap", "at", "au", "ay"};
    public static String[] vanAW = {"ă", "ăc", "ăm", "ăn", "ăng", "up", "ăt"};
    public static String[] vanAA = {"â", "ấc", "âm", "ân", "âng", "âp", "ât", "âu", "ây"};
    public static String[] vanE = {"e", "em", "en", "eng", "eo", "ep", "et"};
    public static String[] vanEE = {"ê", "ếch", "êm", "ên", "ênh", "êp", "êt", "êu"};
    public static String[] vanI = {"i", "ia", "ich", "im", "in", "inh", "ip", "it", "iu", "iêc", "iêm",
            "iên", "iêng", "iêp", "iêt", "iêu"};
    public static String[] vanO = {"o", "oa", "oc", "oe", "oi", "om", "on", "ong", "op", "ot",
            "oach", "oai", "oan", "oang", "oanh", "oat", "oay",
            "oăn", "oăng", "oăt"};
    public static String[] vanOO = {"ô", "ôc", "ôi", "ôm", "ôn", "ông", "ôp", "ôt"};
    public static String[] vanOW = {"ơ", "ơi", "ơm", "ơn", "ơp", "ơt"};
    public static String[] vanU = {"u", "ua", "uc", "uê", "ui", "um", "un", "ung", "uơ", "up", "ut", "uy",
            "uân", "uât",
            "uôc", "uôi", "uôm", "uôn", "uôm", "uông", "uốt",
            "uya", "uych", "uynh", "uyt",
            "uyên", "uyết",
            "ươc", "ươi", "ươm", "ươn", "ương", "ươp", "ươt", "ươu"};
    public static String[] vanUW = {"ư", "ưa", "ưc", "ưi", "ưng", "ưt", "ưu"};
    public static String[] vanY = {"yên", "yêm", "yêu"};

    public static String[][] bangvan={vanA, vanAA, vanAW, vanE, vanEE, vanI, vanO, vanOO, vanOW, vanU, vanUW, vanY};

    public static String[] phuamcuoi = {"p", "t", "c", "ch", "m", "n", "ng", "nh"};
}