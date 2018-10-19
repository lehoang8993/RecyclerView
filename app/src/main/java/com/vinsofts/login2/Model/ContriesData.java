package com.vinsofts.login2.Model;

public class ContriesData {
    private int mImageContry;
    private String mNameContryVN;
    private String mNameContryEN;
    private String mNameCapitalVN;
    private String mNameCapitalEN;

    public ContriesData(int imageContry, String nameContryVN, String nameContryEN, String nameCapitalVN, String nameCapitalEN) {
        mImageContry = imageContry;
        mNameContryVN = nameContryVN;
        mNameContryEN = nameContryEN;
        mNameCapitalVN = nameCapitalVN;
        mNameCapitalEN = nameCapitalEN;
    }

    public int getImageContry() {
        return mImageContry;
    }

    public void setImageContry(int imageContry) {
        mImageContry = imageContry;
    }

    public String getNameContryVN() {
        return mNameContryVN;
    }

    public void setNameContryVN(String nameContryVN) {
        mNameContryVN = nameContryVN;
    }

    public String getNameContryEN() {
        return mNameContryEN;
    }

    public void setNameContryEN(String nameContryEN) {
        mNameContryEN = nameContryEN;
    }

    public String getNameCapitalVN() {
        return mNameCapitalVN;
    }

    public void setNameCapitalVN(String nameCapitalVN) {
        mNameCapitalVN = nameCapitalVN;
    }

    public String getNameCapitalEN() {
        return mNameCapitalEN;
    }

    public void setNameCapitalEN(String nameCapitalEN) {
        mNameCapitalEN = nameCapitalEN;
    }
}
