package charity.model;

import java.util.Date;

public class QuyenGop {
    private int id;
    private int nhaTaiTroId;
    private int chuongTrinhId;
    private double soTien;
    private Date ngayQuyenGop;
    private String phuongThucThanhToan;
    private String ghiChu;
    
    public QuyenGop() {}
    
    public QuyenGop(int id, int nhaTaiTroId, int chuongTrinhId, double soTien, 
                   Date ngayQuyenGop, String phuongThucThanhToan, String ghiChu) {
        this.id = id;
        this.nhaTaiTroId = nhaTaiTroId;
        this.chuongTrinhId = chuongTrinhId;
        this.soTien = soTien;
        this.ngayQuyenGop = ngayQuyenGop;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.ghiChu = ghiChu;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getNhaTaiTroId() { return nhaTaiTroId; }
    public void setNhaTaiTroId(int nhaTaiTroId) { this.nhaTaiTroId = nhaTaiTroId; }
    
    public int getChuongTrinhId() { return chuongTrinhId; }
    public void setChuongTrinhId(int chuongTrinhId) { this.chuongTrinhId = chuongTrinhId; }
    
    public double getSoTien() { return soTien; }
    public void setSoTien(double soTien) { this.soTien = soTien; }
    
    public Date getNgayQuyenGop() { return ngayQuyenGop; }
    public void setNgayQuyenGop(Date ngayQuyenGop) { this.ngayQuyenGop = ngayQuyenGop; }
    
    public String getPhuongThucThanhToan() { return phuongThucThanhToan; }
    public void setPhuongThucThanhToan(String phuongThucThanhToan) { this.phuongThucThanhToan = phuongThucThanhToan; }
    
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}
