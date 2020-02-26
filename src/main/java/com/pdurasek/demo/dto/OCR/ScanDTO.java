package com.pdurasek.demo.dto.OCR;

public class ScanDTO {

    private String imageBase64;

    private String recognizerType;

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getRecognizerType() {
        return recognizerType;
    }

    public void setRecognizerType(String recognizerType) {
        this.recognizerType = recognizerType;
    }
}