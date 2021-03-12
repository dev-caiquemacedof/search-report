package com.targetdatasmart.reportbatch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "registro_consulta", createIndex = false)
public class RegistroConsulta {

    @Id
    private String id;

    @Field( name = "cliente_id" )
    private Integer costumerId;

    @Field( name = "usuario_cliente_id" )
    private Integer userCostumerId;

    @Field( name = "metodo" )
    private String method;

    @Field( name = "tipo" )
    private String type;

    @Field( name = "valor" )
    private Integer value;

    @Field( name = "excedente", type = FieldType.Boolean )
    private Boolean exceeded;

    @Field( name = "tipo_contrato" )
    private String contractType;

    @Field( name = "teste" )
    private String test;

    @Field( name = "tempo_retorno", type = FieldType.Float )
    private Float returnTime;

    @Field( name = "chave" )
    private String chave;

    @Field( name = "created_at" )
    private String createdAt;

    @Field( name = "updated_at" )
    private String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(Integer costumerId) {
        this.costumerId = costumerId;
    }

    public Integer getUserCostumerId() {
        return userCostumerId;
    }

    public void setUserCostumerId(Integer userCostumerId) {
        this.userCostumerId = userCostumerId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getExceeded() {
        return exceeded;
    }

    public void setExceeded(Boolean exceeded) {
        this.exceeded = exceeded;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Float getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Float returnTime) {
        this.returnTime = returnTime;
    }

    public String getChave() {

        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
