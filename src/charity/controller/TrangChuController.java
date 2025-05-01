
package charity.controller;

import charity.service.AccountService;
import charity.service.CharityEventService;
import charity.service.DonationService;
import charity.service.OrganizationService;
import javax.swing.JLabel;

/**
 *
 * @author phaml
 */
public class TrangChuController {
        private JLabel jlbAccountCount;
    private JLabel jlbDonateCount;
    private JLabel jlbEventCount;
    private JLabel jlbOrganizationCount;
    
    private DonationService donationService = null;
    private AccountService accountService = null;
    private CharityEventService charityEventService= null;
    private OrganizationService organizationService = null;
    public TrangChuController(JLabel jlbAccountCount, JLabel jlbDonateCount, JLabel jlbEventCount, JLabel jlbOrganizationCount) {
        this.jlbAccountCount = jlbAccountCount;
        this.jlbDonateCount = jlbDonateCount;
        this.jlbEventCount = jlbEventCount;
        this.jlbOrganizationCount = jlbOrganizationCount;
        init();
    }
    
    public void init(){
        donationService = new DonationService();
        accountService = new AccountService();
        charityEventService = new CharityEventService();
        organizationService  = new OrganizationService();
        
        jlbAccountCount.setText(String.valueOf(accountService.getAccountCount()));
        jlbDonateCount.setText(String.valueOf(donationService.getDonationCount()));
        jlbEventCount.setText(String.valueOf(charityEventService.getEventCount()));
        jlbOrganizationCount.setText(String.valueOf(organizationService.getOrganizationCount()));
    }
}
