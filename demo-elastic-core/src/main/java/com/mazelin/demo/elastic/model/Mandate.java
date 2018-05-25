package com.mazelin.demo.elastic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "gp", type = "mandate")
public class Mandate {

    @Id
    private String id;

    private String offer;
    private String structure;
    private String riskProfile;
    private Double assets;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Client client;

    public Mandate() {
    }

    public Mandate(String id, String offer, String structure, String riskProfile, Double assets, Client client) {
        this.id = id;
        this.offer = offer;
        this.structure = structure;
        this.riskProfile = riskProfile;
        this.assets = assets;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getRiskProfile() {
        return riskProfile;
    }

    public void setRiskProfile(String riskProfile) {
        this.riskProfile = riskProfile;
    }

    public Double getAssets() {
        return assets;
    }

    public void setAssets(Double assets) {
        this.assets = assets;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Mandate{" +
                "id='" + id + '\'' +
                ", offer='" + offer + '\'' +
                ", structure='" + structure + '\'' +
                ", riskProfile='" + riskProfile + '\'' +
                ", assets=" + assets +
                '}';
    }
}
