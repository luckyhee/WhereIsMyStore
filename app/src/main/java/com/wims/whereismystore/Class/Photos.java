package com.wims.whereismystore.Class;


import java.io.Serializable;

public class Photos implements Serializable {
    private String photo_1;
    private String photo_2;
    private String photo_3;
    private String photo_4;
    private String photo_5;
    private String photo_6;
    private String photo_7;
    private String photo_8;
    private String photo_9;
    private String photo_10;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    private String count;

    public Photos(){
        photo_1="";photo_2="";
        photo_3="";photo_4="";
        photo_5="";photo_6="";
        photo_7="";photo_8="";
        photo_9="";photo_10="";
    }
    public String getPhoto_1() {
        return photo_1;
    }

    public void setPhoto_1(String photo_1) {
        this.photo_1 = photo_1;
    }

    public String getPhoto_2() {
        return photo_2;
    }

    public void setPhoto_2(String photo_2) {
        this.photo_2 = photo_2;
    }

    public String getPhoto_3() {
        return photo_3;
    }

    public void setPhoto_3(String photo_3) {
        this.photo_3 = photo_3;
    }

    public String getPhoto_4() {
        return photo_4;
    }

    public void setPhoto_4(String photo_4) {
        this.photo_4 = photo_4;
    }

    public String getPhoto_5() {
        return photo_5;
    }

    public void setPhoto_5(String photo_5) {
        this.photo_5 = photo_5;
    }

    public String getPhoto_6() {
        return photo_6;
    }

    public void setPhoto_6(String photo_6) {
        this.photo_6 = photo_6;
    }

    public String getPhoto_7() {
        return photo_7;
    }

    public void setPhoto_7(String photo_7) {
        this.photo_7 = photo_7;
    }

    public String getPhoto_8() {
        return photo_8;
    }

    public void setPhoto_8(String photo_8) {
        this.photo_8 = photo_8;
    }

    public String getPhoto_9() {
        return photo_9;
    }

    public void setPhoto_9(String photo_9) {
        this.photo_9 = photo_9;
    }

    public String getPhoto_10() {
        return photo_10;
    }

    public void setPhoto_10(String photo_10) {
        this.photo_10 = photo_10;
    }



    public void setPhotos(String image, int index){
        switch (index){
            case 0:
                setPhoto_1(image);
                break;
            case 1:
                setPhoto_2(image);
                break;
            case 2:
                setPhoto_3(image);
                break;
            case 3:
                setPhoto_4(image);
                break;
            case 4:
                setPhoto_5(image);
                break;
            case 5:
                setPhoto_6(image);
                break;
            case 6:
                setPhoto_7(image);
                break;
            case 7:
                setPhoto_8(image);
                break;
            case 8:
                setPhoto_9(image);
                break;
            case 9:
                setPhoto_10(image);
                break;
            default:
                break;
        }
    }

    public String getPhotos(int index){
        String photo = null;
        switch (index){
            case 0:
                photo=getPhoto_1();
                break;
            case 1:
                photo=getPhoto_2();
                break;
            case 2:
                photo=getPhoto_3();
                break;
            case 3:
                photo=getPhoto_4();
                break;
            case 4:
                photo=getPhoto_5();
                break;
            case 5:
                photo=getPhoto_6();
                break;
            case 6:
                photo=getPhoto_7();
                break;
            case 7:
                photo=getPhoto_8();
                break;
            case 8:
                photo=getPhoto_9();
                break;
            case 9:
                photo=getPhoto_10();
                break;
            default:
                break;
        }
        return photo;
    }

}
