package charity.model;

public class DoiTuongHoTro {
    private int id;
    private String ten;
    private String diaChi;
    private String moTa;
    private int chuongTrinhId;
    private String trangThai;
    
    public DoiTuongHoTro() {}
    
    public DoiTuongHoTro(int id, String ten, String diaChi, String moTa, int chuongTrinhId, String trangThai) {
        this.id = id;
        this.ten = ten;
        this.diaChi = diaChi;
        this.moTa = moTa;
        this.chuongTrinhId = chuongTrinhId;
        this.trangThai = trangThai;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    
    public int getChuongTrinhId() { return chuongTrinhId; }
    public void setChuongTrinhId(int chuongTrinhId) { this.chuongTrinhId = chuongTrinhId; }
    
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
