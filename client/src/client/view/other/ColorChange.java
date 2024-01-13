/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.other;

import client.model.Problemlist;

/**
 *
 * @author ytxlo
 */
public interface ColorChange {
    final String LIME = "00FF00";
    final String RED = "FF0000";
    final String YELLOW = "FFFF00";
    final String BLUE = "0000FF";
    final String PINK = "FFC0CB";
    final String PURPLE  = "800080";
    final String CYAN = "00FFFF";
    final String GREEN = "008000";
    final String ORANGE = "FFA500";
    final String GRAY = "E0E0E0";
    void change(Problemlist pl,String color);
}
