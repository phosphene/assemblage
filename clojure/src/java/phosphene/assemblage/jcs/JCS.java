package phosphene.assemblage.jcs;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import org.erdtman.jcs.JsonCanonicalizer;
import org.erdtman.jcs.NumberToJSON;

/**
 * RFC 8785 JSON Canonicalization Scheme (JCS) serializer and hasher.
 * Works directly on Java/Clojure data structures (Map, List, Set, String, Number, Boolean, null)
 * or parses/canonicalizes raw JSON text.
 */
public class JCS {

    /**
     * Comparator for UTF-16 code units (as mandated by RFC 8785 Section 3.2.3).
     */
    public static final Comparator<String> UTF16_CODE_UNIT_COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            int len1 = s1.length();
            int len2 = s2.length();
            int lim = Math.min(len1, len2);
            for (int k = 0; k < lim; k++) {
                char c1 = s1.charAt(k);
                char c2 = s2.charAt(k);
                if (c1 != c2) {
                    return Character.compare(c1, c2);
                }
            }
            return len1 - len2;
        }
    };

    /**
     * Canonicalizes raw JSON string according to RFC 8785.
     */
    public static String canonicalizeJson(String rawJson) throws IOException {
        JsonCanonicalizer jc = new JsonCanonicalizer(rawJson);
        return jc.getEncodedString();
    }

    /**
     * Canonicalizes raw JSON UTF-8 bytes according to RFC 8785.
     */
    public static byte[] canonicalizeJsonUtf8(byte[] rawJsonBytes) throws IOException {
        JsonCanonicalizer jc = new JsonCanonicalizer(rawJsonBytes);
        return jc.getEncodedUTF8();
    }

    /**
     * Canonicalizes an in-memory Java or Clojure data structure directly.
     */
    public static String canonicalize(Object obj) throws IOException {
        StringBuilder sb = new StringBuilder();
        serialize(obj, sb);
        return sb.toString();
    }

    public static byte[] canonicalizeUtf8(Object obj) throws IOException {
        return canonicalize(obj).getBytes(StandardCharsets.UTF_8);
    }

    public static String sha256Hex(Object obj) throws IOException {
        byte[] bytes = canonicalizeUtf8(obj);
        return digestSha256(bytes);
    }

    public static String sha256HexJson(String rawJson) throws IOException {
        byte[] bytes = canonicalizeJson(rawJson).getBytes(StandardCharsets.UTF_8);
        return digestSha256(bytes);
    }

    private static String digestSha256(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(bytes);
            StringBuilder hex = new StringBuilder();
            for (byte b : digest) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static void serialize(Object o, StringBuilder sb) throws IOException {
        if (o == null) {
            sb.append("null");
        } else if (o instanceof Boolean) {
            sb.append(o.toString());
        } else if (o instanceof String) {
            serializeString((String) o, sb);
        } else if (o instanceof Character) {
            serializeString(o.toString(), sb);
        } else if (o instanceof clojure.lang.Named) {
            clojure.lang.Named named = (clojure.lang.Named) o;
            String ns = named.getNamespace();
            String name = named.getName();
            String val = (ns != null && !ns.isEmpty()) ? (ns + "/" + name) : name;
            serializeString(val, sb);
        } else if (o instanceof Number) {
            serializeNumber((Number) o, sb);
        } else if (o instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) o;
            sb.append('{');
            List<String> keys = new ArrayList<String>(map.size());
            Map<String, Object> strMap = new HashMap<String, Object>(map.size());
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                String kStr;
                Object kObj = entry.getKey();
                if (kObj instanceof clojure.lang.Named) {
                    clojure.lang.Named n = (clojure.lang.Named) kObj;
                    String ns = n.getNamespace();
                    String name = n.getName();
                    kStr = (ns != null && !ns.isEmpty()) ? (ns + "/" + name) : name;
                } else if (kObj == null) {
                    throw new IOException("JSON object keys cannot be null");
                } else {
                    kStr = kObj.toString();
                }
                if (strMap.containsKey(kStr)) {
                    throw new IOException("Duplicate object property key: " + kStr);
                }
                strMap.put(kStr, entry.getValue());
                keys.add(kStr);
            }
            Collections.sort(keys, UTF16_CODE_UNIT_COMPARATOR);
            boolean first = true;
            for (String k : keys) {
                if (!first) {
                    sb.append(',');
                }
                first = false;
                serializeString(k, sb);
                sb.append(':');
                serialize(strMap.get(k), sb);
            }
            sb.append('}');
        } else if (o instanceof Set) {
            // For Sets in open-world data, sort elements canonically
            Set<?> set = (Set<?>) o;
            List<String> elements = new ArrayList<String>(set.size());
            for (Object item : set) {
                elements.add(canonicalize(item));
            }
            Collections.sort(elements, UTF16_CODE_UNIT_COMPARATOR);
            sb.append('[');
            boolean first = true;
            for (String el : elements) {
                if (!first) {
                    sb.append(',');
                }
                first = false;
                sb.append(el);
            }
            sb.append(']');
        } else if (o instanceof Iterable) {
            sb.append('[');
            boolean first = true;
            for (Object item : (Iterable<?>) o) {
                if (!first) {
                    sb.append(',');
                }
                first = false;
                serialize(item, sb);
            }
            sb.append(']');
        } else if (o instanceof clojure.lang.ISeq) {
            sb.append('[');
            boolean first = true;
            for (clojure.lang.ISeq s = (clojure.lang.ISeq) o; s != null; s = s.next()) {
                if (!first) {
                    sb.append(',');
                }
                first = false;
                serialize(s.first(), sb);
            }
            sb.append(']');
        } else {
            throw new IOException("Unsupported data type for JSON canonicalization: " + o.getClass().getName());
        }
    }

    private static void serializeNumber(Number n, StringBuilder sb) throws IOException {
        if (n instanceof Integer || n instanceof Long || n instanceof Short || n instanceof Byte) {
            sb.append(n.toString());
        } else if (n instanceof java.math.BigInteger) {
            sb.append(n.toString());
        } else {
            double d = n.doubleValue();
            sb.append(NumberToJSON.serializeNumber(d));
        }
    }

    private static void serializeString(String value, StringBuilder sb) {
        sb.append('"');
        int len = value.length();
        for (int i = 0; i < len; i++) {
            char c = value.charAt(i);
            switch (c) {
                case '\n':
                    sb.append("\\n");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                    break;
            }
        }
        sb.append('"');
    }
}
