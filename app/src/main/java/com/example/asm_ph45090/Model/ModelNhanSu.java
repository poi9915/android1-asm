package com.example.asm_ph45090.Model;

public class ModelNhanSu {
    private int id;
    private String MaNV;
    private String HoTen;
    private String PhongBan;

    public ModelNhanSu(int id, String maNV, String hoTen, String phongBan) {
        this.id = id;
        MaNV = maNV;
        HoTen = hoTen;
        PhongBan = phongBan;
    }

    public ModelNhanSu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getPhongBan() {
        return PhongBan;
    }

    public void setPhongBan(String phongBan) {
        PhongBan = phongBan;
    }

    @Override
    public String toString() {
        return "ModelNhanSu{" +
                "id=" + id +
                ", MaNV='" + MaNV + '\'' +
                ", HoTen='" + HoTen + '\'' +
                ", PhongBan='" + PhongBan + '\'' +
                '}';
    }
}
