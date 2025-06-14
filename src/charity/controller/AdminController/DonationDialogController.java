package charity.controller.AdminController;

import charity.component.MapHelper;
import charity.model.Donation;
import charity.service.DonationService;
import charity.view.Admin.DonationDialog;
import charity.utils.MessageDialog;
import charity.utils.ScannerUtils;
import charity.view.Admin.SelectEventDialog;
import charity.view.Admin.SelectUserDialog;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DonationDialogController {

    private final DonationDialog dialog;
    private Donation donation;
    private String type;
    private DonationService service;

    public DonationDialogController(DonationDialog dialog, String type) {
        service = new DonationService();
        this.type = type;
        this.dialog = dialog;
        if (type.equals("ADD")) {
            donation = new Donation();
        } else {
            this.donation = this.dialog.getDonation();
            setForm();
        }
        initEvent();
    }

    private void setForm() {
        dialog.getTxtDonor().setText(MapHelper.getUserName(donation.getUserId()));
        dialog.getTxtEvent().setText(MapHelper.getEventName(donation.getEventId()));
        dialog.getTxtAmount().setText(String.valueOf(donation.getAmount()));
        dialog.getTxtContent().setText(donation.getDescription());
        dialog.getDateTimePicker().setDateTimeStrict(donation.getDonationDate().toLocalDateTime());
    }

    private void initEvent() {
        dialog.getBtnSave().addActionListener(e -> onSave());
        dialog.getBtnCancel().addActionListener(e -> onCancel());
        ScannerUtils.setOnlyInputNumber(dialog.getTxtAmount());
        dialog.getBtnUserChoice().addActionListener(e -> onSelectUser());
        dialog.getBtnEventChoice().addActionListener(e -> onSelectEvent());
    }

    private void onSave() {
        String donor = dialog.getTxtDonor().getText().trim();
        String event = dialog.getTxtEvent().getText().trim();
        String amountStr = dialog.getTxtAmount().getText().trim();
        String content = dialog.getTxtContent().getText().trim();
        System.out.println("");
        LocalDateTime dateTime = dialog.getDateTimePicker().getDateTimeStrict();

        long amount = 0;
        if (!amountStr.isEmpty()) {
            amount = Long.parseLong(amountStr.replaceAll("[^\\d]", ""));
        }

        if (!valid(donor, event, amountStr, amount, content, dateTime)) {
            return;
        }

        Timestamp timestamp = Timestamp.valueOf(dateTime);
        donation.setAmount(amount);
        donation.setDonationDate(timestamp);
        donation.setDescription(content);

        //thêm
        if (type.equals("ADD")) {
            if (service.addDonation(donation)) {
                MessageDialog.showSuccess("Thêm quyên góp thành công!");
                dialog.dispose();
            } else {
                MessageDialog.showSuccess("Thêm quyên góp thất bại!");
            }
        } else {//sua
            if (service.updateDonation(donation)) {
                MessageDialog.showSuccess("Sửa quyên góp thành công!");
                dialog.dispose();
            } else {
                MessageDialog.showSuccess("Sửa quyên góp thất bại!");
            }
        }
    }

    private boolean valid(String donor, String event, String amountStr, long amount, String content, LocalDateTime dateTime) {

        if (donor.isEmpty() || event.isEmpty() || amountStr.isEmpty() || dateTime == null) {
            MessageDialog.showError("Vui lòng nhập đầy đủ thông tin!");
            return false;
        }

        if (amount < 2000) {
            MessageDialog.showError("Số tiền không được nhỏ hơn 2,000 !");
            return false;
        }
        return true;
    }

    private void onCancel() {
        dialog.dispose();
    }

    private void onSelectUser() {
        dialog.setVisible(false);
        SelectUserDialog selectUserDialog = new SelectUserDialog(this.dialog, "Chọn người quyên góp", true, donation);
        selectUserDialog.setVisible(true);
    }
    private void onSelectEvent() {
        dialog.setVisible(false);
        SelectEventDialog selectEventDialog = new SelectEventDialog(this.dialog, "Chọn sự kiện", true, donation);
        selectEventDialog.setVisible(true);
    }
    
//    private 
}
