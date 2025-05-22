package charity.service;

import charity.model.otherModel.DonationStatistics;
import charity.repository.StatisticsRepository;
import charity.service.IService.IStatisticsService;
import charity.utils.MessageDialog;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import jdk.jfr.Percentage;

/**
 *
 * @author phaml
 */
public class StatisticsService implements IStatisticsService {

    private StatisticsRepository statisticRepository = new StatisticsRepository();

    @Override
    public List<Map<String, Object>> getStatisticsByDateRange(Date startDate, Date endDate) {
        return statisticRepository.getStatisticsByDateRange(startDate, endDate);
    }

    @Override
    public List<Map<String, Object>> getStatisticsByCategory() {
        return statisticRepository.getStatisticsByCategory();
    }

    @Override
    public List<Map<String, Object>> getTopDonors(int limit) {
        if (limit<=0){
            MessageDialog.showError("Limit phải lớn hơn 0");
            return null;
        }
        return statisticRepository.getTopDonors(limit);
    }

    @Override
    public Map<String, Object> getOverallStatistics() {
        return statisticRepository.getOverallStatistics();
    }

    @Override
    public List<DonationStatistics> getStatistics(Date fromDate, Date toDate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //thong ke theo loai
    @Override
    public List<Map<String, Object>> getCategoryStatistics() {
        List<Map<String, Object>> stats = statisticRepository.getStatisticsByCategory();

        //tinh tong tien
        BigDecimal total = (BigDecimal) stats.stream()
                .map(stat -> (BigDecimal) stat.get("totalAmount"))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        //tinh phan tram
        if (total.compareTo(BigDecimal.ZERO) > 0) {
            for (Map<String, Object> stat : stats) {
                BigDecimal amount = (BigDecimal) stat.get("totalAmount");
                double percentage = amount.multiply(new BigDecimal(100))
                        .divide(total, 2, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
                stat.put("percentage", percentage);
            }
        }
        return stats;
    }

}
