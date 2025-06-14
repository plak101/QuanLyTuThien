/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package charity.component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author phaml
 */
public interface IFormatData {
    DecimalFormat moneyFormat = new DecimalFormat("#,###");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
}
