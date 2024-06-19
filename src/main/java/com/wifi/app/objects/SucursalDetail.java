package com.wifi.app.objects;



import java.util.Date;

public class SucursalDetail {


    private Integer id;
    private String name;
    private String nameestablishment;
    private String product;
    private Boolean purpple;
    private Boolean status;
    private Date datesend;
    private Date dateoperative;
    private String lastmille;
    private Integer countap;
    private String modelap;
    private Integer countpower;
    private Integer countswitch;
    private String modelswitch;



    public SucursalDetail(Integer id, String name, String nameestablishment, String product, Boolean purpple, Boolean status, Date datesend, Date dateoperative, String lastmille, Integer countap, String modelap, Integer countpower, Integer countswitch, String modelswitch) {
        this.id = id;
        this.name = name;
        this.nameestablishment = nameestablishment;
        this.product = product;
        this.purpple = purpple;
        this.status = status;
        this.datesend = datesend;
        this.dateoperative = dateoperative;
        this.lastmille = lastmille;
        this.countap = countap;
        this.modelap = modelap;
        this.countpower = countpower;
        this.countswitch = countswitch;
        this.modelswitch = modelswitch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameestablishment() {
        return nameestablishment;
    }

    public void setNameestablishment(String nameestablishment) {
        this.nameestablishment = nameestablishment;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Boolean getPurpple() {
        return purpple;
    }

    public void setPurpple(Boolean purpple) {
        this.purpple = purpple;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDatesend() {
        return datesend;
    }

    public void setDatesend(Date datesend) {
        this.datesend = datesend;
    }

    public Date getDateoperative() {
        return dateoperative;
    }

    public void setDateoperative(Date dateoperative) {
        this.dateoperative = dateoperative;
    }

    public String getLastmille() {
        return lastmille;
    }

    public void setLastmille(String lastmille) {
        this.lastmille = lastmille;
    }

    public Integer getCountap() {
        return countap;
    }

    public void setCountap(Integer countap) {
        this.countap = countap;
    }

    public String getModelap() {
        return modelap;
    }

    public void setModelap(String modelap) {
        this.modelap = modelap;
    }

    public Integer getCountpower() {
        return countpower;
    }

    public void setCountpower(Integer countpower) {
        this.countpower = countpower;
    }

    public Integer getCountswitch() {
        return countswitch;
    }

    public void setCountswitch(Integer countswitch) {
        this.countswitch = countswitch;
    }

    public String getModelswitch() {
        return modelswitch;
    }

    public void setModelswitch(String modelswitch) {
        this.modelswitch = modelswitch;
    }
}
