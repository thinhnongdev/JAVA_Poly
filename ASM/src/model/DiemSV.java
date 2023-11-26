/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class DiemSV {
    private String hoTen,maSV;
    private int tiengAnh,tinHoc,GDTC,diemTB;

    public DiemSV() {
    }

    public DiemSV(String hoTen, String maSV, int tiengAnh, int tinHoc, int GDTC, int diemTB) {
        this.hoTen = hoTen;
        this.maSV = maSV;
        this.tiengAnh = tiengAnh;
        this.tinHoc = tinHoc;
        this.GDTC = GDTC;
        this.diemTB = diemTB;
    }

    public DiemSV(int tiengAnh, int tinHoc, int GDTC) {
        this.tiengAnh = tiengAnh;
        this.tinHoc = tinHoc;
        this.GDTC = GDTC;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public int getTiengAnh() {
        return tiengAnh;
    }

    public void setTiengAnh(int tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public int getTinHoc() {
        return tinHoc;
    }

    public void setTinHoc(int tinHoc) {
        this.tinHoc = tinHoc;
    }

    public int getGDTC() {
        return GDTC;
    }

    public void setGDTC(int GDTC) {
        this.GDTC = GDTC;
    }

    public int getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(int diemTB) {
        this.diemTB = diemTB;
    }
    
    public Object[] toDataRow(){
        return new Object[]{this.getHoTen(),this.getMaSV(),this.getTiengAnh(),this.getTinHoc(),this.getGDTC(),this.getDiemTB()};
    }
    
    
}
