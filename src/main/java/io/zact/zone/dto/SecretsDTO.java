package io.zact.zone.dto;

import java.util.Map;

public class SecretsDTO {
    private OptionsDTO options;
    private Map<String, String> data;

    public OptionsDTO getOptions() {
        return options;
    }

    public void setOptions(OptionsDTO options) {
        this.options = options;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}

class OptionsDTO {
    private int cas;

    public int getCas() {
        return cas;
    }

    public void setCas(int cas) {
        this.cas = cas;
    }
}
