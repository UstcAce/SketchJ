package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * TinyURL是一种URL简化服务，
 * 比如：当你输入一个URL https://leetcode.com/problems/design-tinyurl 时，
 * 它将返回一个简化的URL http://tinyurl.com/4e9iAk. https://leetcode.com/xxxxxx
 *
 * 要求：设计一个 TinyURL
 * 的加密 encode 和解密 decode 的方法。
 * 你的加密和解密算法如何设计和运作是没有限制的，你只需要保证一个URL可以被加密成一个TinyURL，
 * 并且这个TinyURL可以用解密方法恢复成原本的URL。
 *
 */
public class Q535TinyUrl {
    // 实际域名 -> 简化域名
    private Map<String, String> long2TinyMap = new HashMap<>();
    private Map<String, String> tiny2LongMap = new HashMap<>();

    private String tinyPrefix = "http://tinyurl.com/";

    private int length = 6;

    private Random random = new Random();

    // 10个数字+26个小写字符+26个大写字母
    private String charRange = "0123456789abcdefghijknmlo";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (long2TinyMap.containsKey(longUrl)) {
            return long2TinyMap.get(longUrl);
        } else {
            String tinyURL = tinyPrefix + getATinyURL();
            long2TinyMap.put(longUrl, tinyURL);
            tiny2LongMap.put(tinyURL, longUrl);
            return tinyURL;
        }
    }

    private String getATinyURL() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomNum = random.nextInt(charRange.length());
            sb.append(charRange.charAt(randomNum));
        }
        if (tiny2LongMap.containsKey(sb.toString())) {
            return getATinyURL();
        } else {
            return sb.toString();
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (tiny2LongMap.containsKey(shortUrl)) {
            return tiny2LongMap.get(shortUrl);
        } else {
            throw new IllegalArgumentException("The input shortUrl is invalid, no original URL matches!");
        }
    }

    // Decodes a shortened URL to its original URL.
    public Optional<String> decodeIfPresent(String shortUrl) {
        if (tiny2LongMap.containsKey(shortUrl)) {
            return Optional.of(tiny2LongMap.get(shortUrl));
        } else {
            // log.warm("提示异常")
            return Optional.empty();
        }
    }
}
