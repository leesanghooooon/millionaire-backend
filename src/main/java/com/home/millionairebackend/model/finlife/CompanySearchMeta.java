package com.home.millionairebackend.model.finlife;

import lombok.Data;

import java.util.List;

@Data
public class CompanySearchMeta {
    private String prdt_div;
    private String total_count;
    private String max_page_no;
    private String now_page_no;
    private String err_cd;
    private String err_msg;
    private List<CompanySearch> baseList;
    private List<CompanySearchOption> optionList;
}
