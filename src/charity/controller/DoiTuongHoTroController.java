package charity.controller;
import charity.model.DoiTuongHoTro;
import java.util.ArrayList;
import java.util.List;

public class DoiTuongHoTroController {
    private List<DoiTuongHoTro> doiTuongHoTroList;
    
    public DoiTuongHoTroController() {
        doiTuongHoTroList = new ArrayList<>();
        // In actual implementation, initialize from database
    }
    
    public List<DoiTuongHoTro> getAllDoiTuongHoTro() {
        return doiTuongHoTroList;
    }
    
    public void addDoiTuongHoTro(DoiTuongHoTro doiTuongHoTro) {
        // Add logic to validate and save to database
        doiTuongHoTroList.add(doiTuongHoTro);
    }
    
    public void updateDoiTuongHoTro(DoiTuongHoTro doiTuongHoTro) {
        // Update logic
        for (int i = 0; i < doiTuongHoTroList.size(); i++) {
            if (doiTuongHoTroList.get(i).getId() == doiTuongHoTro.getId()) {
                doiTuongHoTroList.set(i, doiTuongHoTro);
                break;
            }
        }
    }
    
    public void deleteDoiTuongHoTro(int id) {
        // Delete logic
        doiTuongHoTroList.removeIf(dt -> dt.getId() == id);
    }
    
    public DoiTuongHoTro findById(int id) {
        return doiTuongHoTroList.stream()
                .filter(dt -> dt.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public List<DoiTuongHoTro> findByChuongTrinhId(int chuongTrinhId) {
        List<DoiTuongHoTro> result = new ArrayList<>();
        for (DoiTuongHoTro dt : doiTuongHoTroList) {
            if (dt.getChuongTrinhId() == chuongTrinhId) {
                result.add(dt);
            }
        }
        return result;
    }
    
    public int countDoiTuongHoTro() {
        return doiTuongHoTroList.size();
    }
}
