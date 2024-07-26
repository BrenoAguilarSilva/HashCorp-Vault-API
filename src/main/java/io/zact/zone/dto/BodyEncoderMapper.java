package io.zact.zone.dto;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;

public class BodyEncoderMapper {
    public static String buildFormData(Map<String, String> data) {
        StringJoiner stringJoiner = new StringJoiner("&");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            stringJoiner.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return stringJoiner.toString();
    }
}
