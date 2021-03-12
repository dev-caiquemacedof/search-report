package com.targetdatasmart.reportbatch.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.NumberFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistroConsultaDTO {

    @JsonIgnore
    public static final String SEPARATOR = ";";

    @JsonIgnore
    public static final String[] HEADER = new String[] { "ID","ID CLIENTE","ID USUARIO CLIENTE","METODO","TIPO DA CONSULTA","VALOR","EXCEDENTE ?","TIPO DO CONTRATO (POS-PAGO | PRE-PAGO)","CONSULTA TESTE ?","TEMPO DO RETORNO","VALOR DA CHAVE","DATA" };

    @JsonIgnore
    private String id;

    @JsonProperty( "cliente_id" )
    private Integer costumerId;

    @JsonProperty( "usuario_cliente_id" )
    private Integer userCostumerId;

    @JsonProperty( "metodo" )
    private String method;

    @JsonProperty( "tipo" )
    private String type;

    @JsonProperty( "valor" )
    private String value;

    @JsonProperty( "excedente" )
    private String exceeded;

    @JsonProperty( "tipo_contrato" )
    private String contractType;

    @JsonProperty( "teste" )
    private String test;

    @JsonProperty( "tempo_retorno" )
    private Float returnTime;

    @JsonProperty( "chave" )
    private String chave;

    @JsonProperty( "created_at" )
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.substring(id.length() - Math.min(10, id.length()));
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

    public String getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = NumberFormat.getCurrencyInstance().format(value);
    }

    public String getExceeded() {
        return exceeded;
    }

    public void setExceeded(String exceeded) {
        this.exceeded = ( exceeded.equals("true") ) ? "SIM" : "NAO";
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
        if ( test.equals("0") )
            this.test = "NAO";
        else if ( test.equals("1") )
            this.test = "SIM";
        else
            this.test = "";
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

    @Override
    public String toString() {
        return  id + SEPARATOR + costumerId + SEPARATOR + userCostumerId + SEPARATOR + method + SEPARATOR + type +
                SEPARATOR + value + SEPARATOR + exceeded + SEPARATOR + contractType + SEPARATOR + test + SEPARATOR +
                returnTime + SEPARATOR + chave + SEPARATOR + createdAt;
    }
}
