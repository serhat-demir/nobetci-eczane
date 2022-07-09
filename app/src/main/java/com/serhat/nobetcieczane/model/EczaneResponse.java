package com.serhat.nobetcieczane.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EczaneResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("result")
    @Expose
    private List<Eczane> eczaneler = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Eczane> getEczaneler() {
        return eczaneler;
    }

    public void setEczaneler(List<Eczane> eczaneler) {
        this.eczaneler = eczaneler;
    }
}