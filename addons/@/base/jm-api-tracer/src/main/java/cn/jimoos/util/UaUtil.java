package cn.jimoos.util;

import ua_parser.Client;
import ua_parser.Parser;

/**
 * UA 解析
 *
 * @author :keepcleargas
 * @date :2021-01-26 16:09.
 * @see @{link https://github.com/ua-parser/uap-java }
 */
public class UaUtil {
    private static UaUtil instance;
    private Parser uaParser;

    public static UaUtil getInstance() {
        if (instance == null) {
            synchronized (UaUtil.class) {
                if (instance == null) {
                    instance = new UaUtil();
                }
            }
        }
        return instance;
    }

    private UaUtil() {
        this.uaParser = new Parser();
    }

    public Client parse(String uaString) {
        return uaParser.parse(uaString);
    }

    public String getOsFamily(String uaString) {
        return uaParser.parse(uaString).os.family;
    }
}