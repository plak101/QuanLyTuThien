
package charity.model;

import java.util.Date;

public class ChuongTrinh {
     private int id;
    private String tenChuongTrinh;
    private String moTa;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private double tongKinhPhi;
    private String trangThai;
    
    public ChuongTrinh() {}
    
    public ChuongTrinh(int id, String tenChuongTrinh, String moTa, Date ngayBatDau, 
                      Date ngayKetThuc, double tongKinhPhi, String trangThai) {
        this.id = id;
        this.tenChuongTrinh = tenChuongTrinh;
        this.moTa = moTa;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tongKinhPhi = tongKinhPhi;
        this.trangThai = trangThai;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTenChuongTrinh() { return tenChuongTrinh; }
    public void setTenChuongTrinh(String tenChuongTrinh) { this.tenChuongTrinh = tenChuongTrinh; }
    
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    
    public Date getNgayBatDau() { return ngayBatDau; }
    public void setNgayBatDau(Date ngayBatDau) { this.ngayBatDau = ngayBatDau; }
    
    public Date getNgayKetThuc() { return ngayKetThuc; }
    public void setNgayKetThuc(Date ngayKetThuc) { this.ngayKetThuc = ngayKetThuc; }
    
    public double getTongKinhPhi() { return tongKinhPhi; }
    public void setTongKinhPhi(double tongKinhPhi) { this.tongKinhPhi = tongKinhPhi; }
    
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
