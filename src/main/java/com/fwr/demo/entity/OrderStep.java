package com.fwr.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fwr
 * @date 2020-12-17
 */
@AllArgsConstructor
@Data
public class OrderStep {

    private Long orderId;

    private String desc;

    public static List<OrderStep> getList() {
        OrderStep orderStep1 = new OrderStep(1L,"创建");
        OrderStep orderStep2 = new OrderStep(2L,"创建");
        OrderStep orderStep3 = new OrderStep(3L,"创建");
        OrderStep orderStep4 = new OrderStep(1L,"保存");
        OrderStep orderStep5 = new OrderStep(2L,"保存");
        OrderStep orderStep6 = new OrderStep(3L,"保存");
        OrderStep orderStep7 = new OrderStep(1L,"付款");
        OrderStep orderStep8 = new OrderStep(2L,"付款");
        OrderStep orderStep9 = new OrderStep(3L,"付款");
        OrderStep orderStep10 = new OrderStep(1L,"完成");
        OrderStep orderStep11 = new OrderStep(2L,"完成");
        OrderStep orderStep12 = new OrderStep(3L,"完成");

        List<OrderStep> list = new ArrayList<>();
        list.add(orderStep1);
        list.add(orderStep2);
        list.add(orderStep3);
        list.add(orderStep4);
        list.add(orderStep5);
        list.add(orderStep6);
        list.add(orderStep7);
        list.add(orderStep8);
        list.add(orderStep9);
        list.add(orderStep10);
        list.add(orderStep11);
        list.add(orderStep12);
        return list;
    }
}
