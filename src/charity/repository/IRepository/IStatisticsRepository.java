/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package charity.repository.IRepository;

import charity.model.otherModel.DonationStatistics;
import java.sql.Date;
import java.util.List;
import java.util.Map;


public interface IStatisticsRepository {
    List<Map<String, Object>> getStatisticsByDateRange(Date startDate, Date endDate);
    List<Map<String, Object>> getStatisticsByCategory();
    List<Map<String, Object>> getTopDonors(int limit);
    Map<String, Object> getOverallStatistics();
    List<DonationStatistics> getStatistics(Date fromDate, Date toDate0);
}
