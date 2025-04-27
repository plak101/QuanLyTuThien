import charity.model.Account;
import charity.repository.AccountRepository;
import java.util.ArrayList;
import java.util.List;

public class main {


    public static void main(String[] args) {
       Donate dialog = new Donate(null, true);
       dialog.setVisible(true);
    }
}
