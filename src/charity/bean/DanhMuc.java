package charity.bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhMuc {
    private String kind;
    private JPanel jpn;
    private JLabel jib;
    
    public DanhMuc(){
        
    }

    public DanhMuc(String kind, JPanel jpn, JLabel jib) {
        this.kind = kind;
        this.jpn = jpn;
        this.jib = jib;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getJpn() {
        return jpn;
    }

    public void setJpn(JPanel jpn) {
        this.jpn = jpn;
    }

    public JLabel getJib() {
        return jib;
    }

    public void setJib(JLabel jib) {
        this.jib = jib;
    }
    
}
