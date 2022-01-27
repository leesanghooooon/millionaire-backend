package com.home.millionairebackend.model.finlife;

import lombok.Data;

import java.util.List;

@Data
public class SavingProductsSearchMeta {
    private String prdt_div;
    private String total_count;
    private String max_page_no;
    private String now_page_no;
    private String err_cd;
    private String err_msg;
    private List<SavingProductsSearch> baseList;
    private List<SavingProductsSearchOption> optionList;
}
