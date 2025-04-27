import charity.model.Account;
import charity.repository.AccountRepository;
import java.util.ArrayList;
import java.util.List;

public class main {


    public static void main(String[] args) {
        List<Account> accounts= new ArrayList<>();
        
        AccountRepository re = new AccountRepository();
        accounts = re.getAllAccount();
        for (Account a: accounts){

            System.out.println(a.toString());
        }
    }
}
