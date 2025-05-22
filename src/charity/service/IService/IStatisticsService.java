
package charity.service.IService;

import charity.model.otherModel.DonationStatistics;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author phaml
 */
public interface IStatisticsService {
    List<Map<String, Object>> getStatisticsByDateRange(Date startDate, Date endDate);
    List<Map<String, Object>> getStatisticsByCategory();
    List<Map<String, Object>> getCategoryStatistics();
    List<Map<String, Object>> getTopDonors(int limit);
    Map<String, Object> getOverallStatistics();
    List<DonationStatistics> getStatistics(Date fromDate, Date toDate);
}
